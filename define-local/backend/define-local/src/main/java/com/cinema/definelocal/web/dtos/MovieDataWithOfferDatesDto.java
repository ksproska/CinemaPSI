package com.cinema.definelocal.web.dtos;

import java.time.LocalDate;

public record MovieDataWithOfferDatesDto(
        Long id,
        String title,
        String description,
        int lengthMinutes,
        String imageUrl,
        LocalDate dateSince,
        LocalDate dateUntil
) {
}
