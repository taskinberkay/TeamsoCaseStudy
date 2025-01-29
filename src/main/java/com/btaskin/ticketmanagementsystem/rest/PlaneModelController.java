package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.PlaneModel;
import com.btaskin.ticketmanagementsystem.repositories.PlaneModelRepository;
import com.btaskin.ticketmanagementsystem.services.PlaneModelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planeModel")
public class PlaneModelController extends BaseRestController<PlaneModel, Long, PlaneModelRepository, PlaneModelService> {
}