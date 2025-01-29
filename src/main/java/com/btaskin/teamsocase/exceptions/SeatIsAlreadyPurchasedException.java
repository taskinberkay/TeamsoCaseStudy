package com.btaskin.teamsocase.exceptions;

public class SeatIsAlreadyPurchasedException extends RuntimeException {
    public SeatIsAlreadyPurchasedException(String message) {
        super(message);
    }
}
