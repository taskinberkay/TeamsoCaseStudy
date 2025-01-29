package com.btaskin.teamsocase.rest;

import com.btaskin.teamsocase.domain.PlaneModel;
import com.btaskin.teamsocase.repositories.PlaneModelRepository;
import com.btaskin.teamsocase.services.PlaneModelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planeModel")
public class PlaneModelController extends BaseRestController<PlaneModel, Long, PlaneModelRepository, PlaneModelService> {
}