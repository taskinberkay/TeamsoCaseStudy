package com.btaskin.ticketmanagementsystem.rest;

import com.btaskin.ticketmanagementsystem.domain.SeatPrice;
import com.btaskin.ticketmanagementsystem.repositories.SeatPriceRepository;
import com.btaskin.ticketmanagementsystem.services.SeatPriceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seatPrice")
public class SeatPriceController extends BaseRestController<SeatPrice, Long, SeatPriceRepository, SeatPriceService> {
}