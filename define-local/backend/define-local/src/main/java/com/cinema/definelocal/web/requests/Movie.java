package com.cinema.definelocal.web.requests;

import java.time.LocalDate;
import java.util.List;

public record Movie(
        Long id,
        String title,
        String description,
        String imageUrl,
        List<String> genres,
        LocalDate availabilityStart,
        LocalDate availabilityEnd
) {
}
