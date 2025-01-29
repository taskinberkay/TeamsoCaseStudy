package com.btaskin.ticketmanagementsystem.repositories;

import com.btaskin.ticketmanagementsystem.domain.SeatPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatPriceRepository extends JpaRepository<SeatPrice, Long> {
}
