package com.cinema.clientservice.web.dtos;


public record MovieDetailsDto(
        Long movieId,
        String movieTitle,
        String movieDescription,
        Integer movieLengthMinutes,
        String movieImageUrl
) {
}
