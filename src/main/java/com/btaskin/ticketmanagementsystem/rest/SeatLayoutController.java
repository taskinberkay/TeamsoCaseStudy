package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.SeatLayout;
import com.btaskin.ticketmanagementsystem.repositories.SeatLayoutRepository;
import com.btaskin.ticketmanagementsystem.services.SeatLayoutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seatLayout")
public class SeatLayoutController extends BaseRestController<SeatLayout, Long, SeatLayoutRepository, SeatLayoutService> {
}