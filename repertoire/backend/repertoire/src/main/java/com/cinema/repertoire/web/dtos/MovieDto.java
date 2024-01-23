package com.cinema.repertoire.web.dtos;

public record MovieDto(
        Long movieId,
        String title,
        String description,
        String image,
        Long versionId
) {
}
