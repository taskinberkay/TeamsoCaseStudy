package com.btaskin.ticketmanagementsystem.repositories;

import com.btaskin.ticketmanagementsystem.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
