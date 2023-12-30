package com.cinema.definelocal.web.dtos;

import java.time.LocalDate;

public record MovieOfferVersionDto(
        Long versionOfferMovieId,
        Long offerId,
        LocalDate offerDateSince,
        LocalDate offerDateUntil,
        Long languageVersionId,
        String languageVersionName
) {
}
