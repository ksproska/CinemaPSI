package com.cinema.ticketcollector.web.request;

public record Ticket(
        Long id,
        boolean isValid,
        boolean isStudent,
        int room,
        int seat,
        int row
) {}
