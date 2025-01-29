package com.btaskin.ticketmanagementsystem.repositories;

import com.btaskin.ticketmanagementsystem.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
