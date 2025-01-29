package com.btaskin.ticketmanagementsystem.repositories;

import com.btaskin.ticketmanagementsystem.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
