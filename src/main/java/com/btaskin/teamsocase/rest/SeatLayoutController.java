package com.btaskin.teamsocase.rest;

import com.btaskin.teamsocase.domain.SeatLayout;
import com.btaskin.teamsocase.repositories.SeatLayoutRepository;
import com.btaskin.teamsocase.services.SeatLayoutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seatLayout")
public class SeatLayoutController extends BaseRestController<SeatLayout, Long, SeatLayoutRepository, SeatLayoutService> {
}