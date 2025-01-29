package com.btaskin.teamsocase.services;

import com.btaskin.teamsocase.domain.Flight;
import com.btaskin.teamsocase.enums.EnumFlightStatus;
import com.btaskin.teamsocase.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        Flight savedFlight = super.save(flight);
        flight.setFlightStatus(EnumFlightStatus.SCHEDULED);
        seatService.createSeatsForFlightBySeatLayoutAndFlightId(savedFlight.getPlane().getSeatLayout().getLayout(), savedFlight);
        return savedFlight;
    }

    @Override
    protected Class<Flight> getEntityClass() {
        return Flight.class;
    }
}
