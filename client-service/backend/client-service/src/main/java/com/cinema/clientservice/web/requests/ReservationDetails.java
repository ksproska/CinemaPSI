package com.cinema.clientservice.web.requests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReservationDetails(
        String movieTitle,
        String languageVersionName,
        LocalDate date,
        LocalTime time,
        List<TicketReservationDetailsDto> tickets,
        double total
) {
}
