package com.cinema.clientservice.web.requests;

public record TicketReservationDetailsDto(
        int seatRow,
        int seatNumber,
        boolean isStudent,
        double price
) {
}
