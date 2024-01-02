package com.cinema.clientservice.web.dtos;

public record MovieVersionWithLanguageDto(
        Long movieVersionId,
        Long movieId,
        Long languageVersionId,
        String languageVersionName
) {
}
