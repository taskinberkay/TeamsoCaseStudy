package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.domain.Flight;
import com.btaskin.ticketmanagementsystem.domain.Plane;
import com.btaskin.ticketmanagementsystem.enums.EnumFlightStatus;
import com.btaskin.ticketmanagementsystem.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class FlightService extends BaseService<Flight, Long, FlightRepository> {
    @Autowired
    private SeatService seatService;

    @Autowired
    public FlightService(FlightRepository repository, EntityManager entityManager) {
        super(repository, entityManager);
    }

    @Override
    public Flight save(Flight flight) {
        Flight savedFlight;
        if (!planeAvailableBetweenDates(Optional.of(flight.getId()), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPlane())) {
            throw new RuntimeException("Plane is unavailable between flight times!");
        }
        if (flight.getId() == null) {
            savedFlight = super.save(flight);
            flight.setFlightStatus(EnumFlightStatus.SCHEDULED);
            seatService.createSeatsForFlightBySeatLayoutAndFlightId(savedFlight.getPlane().getSeatLayout().getLayout(), savedFlight);
        } else {
            BigDecimal preChangeDistance = super.findById(flight.getId()).getDistance();
            savedFlight = super.save(flight);
            if (!preChangeDistance.equals(flight.getDistance())) {
                seatService.updatePricesForDistanceChange(flight);
            }
        }
        return savedFlight;
    }

    private boolean planeAvailableBetweenDates(Optional<Long> id, LocalDateTime departureTime, LocalDateTime arrivalTime, Plane plane) {
        String hql = "SELECT flight FROM Flight flight WHERE flight.plane = :plane " +
                     "AND (flight.departureTime < :arrivalTime AND flight.arrivalTime > :departureTime)";

        List<Flight> conflictingFlights = entityManager.createQuery(hql, Flight.class)
                .setParameter("plane", plane)
                .setParameter("departureTime", departureTime)
                .setParameter("arrivalTime", arrivalTime)
                .getResultList();

        return conflictingFlights.isEmpty() ||
               (conflictingFlights.size() == 1 && conflictingFlights.get(0).getId().equals(id.orElse(null)));
    }

    @Override
    protected Class<Flight> getEntityClass() {
        return Flight.class;
    }
}
