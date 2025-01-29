package com.btaskin.teamsocase.repositories;

import com.btaskin.teamsocase.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
