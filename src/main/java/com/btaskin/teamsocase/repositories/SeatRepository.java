package com.btaskin.teamsocase.repositories;

import com.btaskin.teamsocase.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
