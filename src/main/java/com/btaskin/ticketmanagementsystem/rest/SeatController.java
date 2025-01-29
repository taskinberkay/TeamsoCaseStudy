package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.Seat;
import com.btaskin.ticketmanagementsystem.repositories.SeatRepository;
import com.btaskin.ticketmanagementsystem.services.SeatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController extends BaseRestController<Seat, Long, SeatRepository, SeatService> {
}