package com.btaskin.ticketmanagementsystem.exceptions;

class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}