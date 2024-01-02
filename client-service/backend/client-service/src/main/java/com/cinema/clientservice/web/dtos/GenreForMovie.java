package com.cinema.clientservice.web.dtos;

public record GenreForMovie(
        Long movieId,
        Long genreId,
        String genreName
) {
}
