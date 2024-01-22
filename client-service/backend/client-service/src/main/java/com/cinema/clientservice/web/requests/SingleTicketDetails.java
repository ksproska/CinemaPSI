package com.cinema.clientservice.web.requests;

import java.time.LocalDate;
import java.time.LocalTime;

public record SingleTicketDetails(
        String movieTitle,
        boolean isStudent,
        double price,
        LocalDate repertoireDate,
        LocalTime repertoireTime,
        int seatNumber,
        int seatRow,
        Long ticketId,
        boolean isValidated
) {
}
