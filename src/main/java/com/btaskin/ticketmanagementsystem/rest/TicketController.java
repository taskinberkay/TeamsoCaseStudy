package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.Ticket;
import com.btaskin.ticketmanagementsystem.repositories.TicketRepository;
import com.btaskin.ticketmanagementsystem.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController extends BaseRestController<Ticket, Long, TicketRepository, TicketService> {
    @PostMapping("/purchase")
    public ResponseEntity<Object> handlePurchase(@RequestBody Ticket ticket) {
        try {
            return new ResponseEntity<>(service.handlePurchase(ticket), HttpStatus.OK);
        } catch (InterruptedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}