package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.Plane;
import com.btaskin.ticketmanagementsystem.repositories.PlaneRepository;
import com.btaskin.ticketmanagementsystem.services.PlaneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plane")
public class PlaneController extends BaseRestController<Plane, Long, PlaneRepository, PlaneService> {
}