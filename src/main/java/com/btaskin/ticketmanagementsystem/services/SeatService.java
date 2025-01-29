package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.domain.Flight;
import com.btaskin.ticketmanagementsystem.domain.Seat;
import com.btaskin.ticketmanagementsystem.enums.EnumSeatClass;
import com.btaskin.ticketmanagementsystem.exceptions.LayoutValidationException;
import com.btaskin.ticketmanagementsystem.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class SeatService extends BaseService<Seat, Long, SeatRepository> {
    @Autowired
    private SeatPriceService seatPriceService;

    @Autowired
    public SeatService(SeatRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    protected Class<Seat> getEntityClass() {
        return Seat.class;
    }

    public void createSeatsForFlightBySeatLayoutAndFlightId(String layout, Flight flight) {
        List<SeatGroup> seatGroups = getSeatGroupListByLayoutString(layout);

        for (int groupIdx = 0; groupIdx < seatGroups.size(); groupIdx++) {
            SeatGroup group = seatGroups.get(groupIdx);
            String groupLetter = String.valueOf((char) ('A' + groupIdx));

            // Process each cluster independently
            for (int clusterIdx = 0; clusterIdx < group.clusterCount; clusterIdx++) {
                String clusterCode = determineClusterCode(clusterIdx, group.clusterCount);
                int seatsPerRow = group.seatsPerCluster.get(clusterIdx);

                // Calculate total seats in this cluster
                int totalSeats = seatsPerRow * group.rowCount;

                // Generate seat numbers for this cluster
                for (int seatNum = 1; seatNum <= totalSeats; seatNum++) {
                    String seatNumber = String.format("%s-%s-%d",
                            groupLetter,
                            clusterCode,
                            seatNum);

                    Seat seat = new Seat();
                    seat.setSeatNumber(seatNumber);
                    seat.setFlight(flight);
                    seat.setSeatClass(EnumSeatClass.valueOf(groupLetter));
                    try {
                        seat.setPrice(seatPriceService.findPricePerUnitOfDistanceByGroupLetter(EnumSeatClass.valueOf(groupLetter)).multiply(flight.getDistance()));
                    } catch (NoResultException e) {
                        throw new RuntimeException("Price definition for seat class " + groupLetter + " hasn't been made!");
                    }
                    save(seat);
                }
            }
        }
    }

    private String determineClusterCode(int clusterIndex, int totalClusters) {
        if (totalClusters == 1) return "C";
        if (clusterIndex == 0) return "L";
        if (clusterIndex == totalClusters - 1) return "R";
        return "M" + (clusterIndex);
    }


    private static List<SeatGroup> getSeatGroupListByLayoutString(String layout) {
        List<Integer> digits = layout.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());

        int index = 0;
        int groupCount = digits.get(index++);
        List<SeatGroup> seatGroups = new ArrayList<>();

        for (int i = 0; i < groupCount; i++) {
            int clusterCount = digits.get(index++);
            List<Integer> seatsPerCluster = new ArrayList<>();
            for (int j = 0; j < clusterCount; j++) {
                seatsPerCluster.add(digits.get(index++));
            }
            int rowDigitCount = digits.get(index++);
            int rowCount = 0;
            for (int j = 0; j < rowDigitCount; j++) {
                rowCount = rowCount * 10 + digits.get(index++);
            }
            seatGroups.add(new SeatGroup(clusterCount, seatsPerCluster, rowCount));
        }
        return seatGroups;
    }

    private void validateLayoutFormat(String layout) {
        if (layout == null || layout.isEmpty()) {
            throw new LayoutValidationException("Layout cannot be null or empty");
        }

        if (!layout.matches("^\\d+$")) {
            throw new LayoutValidationException("Layout contains non-digit characters");
        }

        List<Integer> digits = layout.chars()
                .map(c -> Character.getNumericValue(c))
                .boxed()
                .collect(Collectors.toList());

        int index = 0;
        try {
            int groupCount = digits.get(index++);
            if (groupCount < 1) {
                throw new LayoutValidationException("Invalid group count: " + groupCount);
            }

            for (int g = 0; g < groupCount; g++) {
                int clusterCount = digits.get(index++);
                if (clusterCount < 1) {
                    throw new LayoutValidationException("Invalid cluster count in group " + (g + 1));
                }

                for (int c = 0; c < clusterCount; c++) {
                    int seats = digits.get(index++);
                    if (seats < 1) {
                        throw new LayoutValidationException("Invalid seat count in group " + (g + 1) + " cluster " + (c + 1));
                    }
                }

                int rowDigitCount = digits.get(index++);
                if (rowDigitCount < 1 || rowDigitCount > 3) {
                    throw new LayoutValidationException("Invalid row digit count in group " + (g + 1));
                }

                if (index + rowDigitCount > digits.size()) {
                    throw new LayoutValidationException("Insufficient digits for row count in group " + (g + 1));
                }

                int rowCount = 0;
                for (int r = 0; r < rowDigitCount; r++) {
                    rowCount = rowCount * 10 + digits.get(index++);
                }
                if (rowCount < 1) {
                    throw new LayoutValidationException("Invalid row count in group " + (g + 1));
                }
            }

            if (index != digits.size()) {
                throw new LayoutValidationException("Extra digits detected in layout string");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new LayoutValidationException("Insufficient digits in layout string");
        }
    }

    public void updatePricesForDistanceChange(Flight flight) {
        List<Seat> seatList = findAllByParams(Map.of("flight", flight)).getItems();
        for (Seat seat : seatList) {
            seat.setPrice(seatPriceService.findPricePerUnitOfDistanceByGroupLetter(seat.getSeatClass()).multiply(flight.getDistance()));
        }
    }

    static class SeatGroup {
        int clusterCount;
        List<Integer> seatsPerCluster;
        int rowCount;

        public SeatGroup(int clusterCount, List<Integer> seatsPerCluster, int rowCount) {
            this.clusterCount = clusterCount;
            this.seatsPerCluster = seatsPerCluster;
            this.rowCount = rowCount;
        }
    }
}
