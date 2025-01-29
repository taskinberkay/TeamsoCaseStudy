package com.btaskin.teamsocase.rest;

import com.btaskin.teamsocase.domain.Flight;
import com.btaskin.teamsocase.repositories.FlightRepository;
import com.btaskin.teamsocase.services.FlightService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController extends BaseRestController<Flight, Long, FlightRepository, FlightService> {
}