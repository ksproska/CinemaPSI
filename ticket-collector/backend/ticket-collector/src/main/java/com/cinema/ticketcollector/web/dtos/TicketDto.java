package com.cinema.ticketcollector.web.dtos;

public record TicketDto(
        Long id,
        boolean isValidated,
        boolean isStudent,
        int room,
        int seat,
        int row
) {}
