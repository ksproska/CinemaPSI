package com.cinema.clientservice.web.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record ReservationDetails(
        String movieTitle,
        String languageVersionName,
        LocalDate date,
        LocalDateTime time,
        List<TicketReservationDetailsDto> tickets,
        double total
) {
}
