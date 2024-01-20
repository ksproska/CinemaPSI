package com.cinema.clientservice.web.exceptions;

public class SeatTakenException extends Exception {
    public SeatTakenException(String message) {
        super(message);
    }
}
