package com.ani.bookingSystem.exception;

public class BookingSlotNotFoundException extends RuntimeException {
    public BookingSlotNotFoundException(String msg) {
        super(msg);
    }    
}