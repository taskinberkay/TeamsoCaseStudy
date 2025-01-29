package com.btaskin.teamsocase.repositories;

import com.btaskin.teamsocase.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
