package com.cinema.clientservice.web.requests;

import java.util.List;

public record Reservation(
        Long repertoireId,
        List<Long> seatIds,
        int numberOfStudentTickets
) {
}
