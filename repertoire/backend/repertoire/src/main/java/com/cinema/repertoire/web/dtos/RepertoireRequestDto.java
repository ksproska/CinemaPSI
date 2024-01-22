package com.cinema.repertoire.web.dtos;

public record RepertoireRequestDto(
        Integer hoursInterval,
        Long cinemaId
) {
}
