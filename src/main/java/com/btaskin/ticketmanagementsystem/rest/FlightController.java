package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.Flight;
import com.btaskin.ticketmanagementsystem.repositories.FlightRepository;
import com.btaskin.ticketmanagementsystem.services.FlightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController extends BaseRestController<Flight, Long, FlightRepository, FlightService> {
}