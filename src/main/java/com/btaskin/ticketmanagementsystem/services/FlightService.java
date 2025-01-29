package com.btaskin.ticketmanagementsystem.services;

import com.btaskin.ticketmanagementsystem.domain.Flight;
import com.btaskin.ticketmanagementsystem.enums.EnumFlightStatus;
import com.btaskin.ticketmanagementsystem.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;

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
        // todo: There needs to be a control to check if the selected plane is in any other flight between the flight hours.
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

    @Override
    protected Class<Flight> getEntityClass() {
        return Flight.class;
    }
}
