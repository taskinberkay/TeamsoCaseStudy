package com.btaskin.teamsocase.rest;

import com.btaskin.teamsocase.domain.Seat;
import com.btaskin.teamsocase.repositories.SeatRepository;
import com.btaskin.teamsocase.services.SeatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController extends BaseRestController<Seat, Long, SeatRepository, SeatService> {
}