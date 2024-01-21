package com.cinema.repertoire.web.dtos;

public record MovieDto(
        String title,
        String description,
        String image,
        Long versionId
) {
}
