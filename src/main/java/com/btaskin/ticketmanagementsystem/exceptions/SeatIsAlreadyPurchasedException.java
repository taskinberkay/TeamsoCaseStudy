package com.btaskin.ticketmanagementsystem.exceptions;

public class SeatIsAlreadyPurchasedException extends RuntimeException {
    public SeatIsAlreadyPurchasedException(String message) {
        super(message);
    }
}
