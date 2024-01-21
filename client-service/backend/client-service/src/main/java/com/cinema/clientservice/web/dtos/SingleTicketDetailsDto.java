package com.cinema.clientservice.web.dtos;

import java.time.LocalDateTime;

public record SingleTicketDetailsDto(
        Long movieVersionId,
        boolean isStudent,
        double price,
        LocalDateTime repertoireStarting,
        int seatNumber,
        int seatRow,
        Long ticketId,
        boolean isValidated
) {
}
