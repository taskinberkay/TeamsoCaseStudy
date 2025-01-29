package com.btaskin.teamsocase.rest;

import com.btaskin.teamsocase.domain.Plane;
import com.btaskin.teamsocase.repositories.PlaneRepository;
import com.btaskin.teamsocase.services.PlaneService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plane")
public class PlaneController extends BaseRestController<Plane, Long, PlaneRepository, PlaneService> {
}