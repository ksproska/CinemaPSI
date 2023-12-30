package com.cinema.definelocal.web.dtos;

public record MovieDataDto(
        Long id,
        String title,
        String description,
        int lengthMinutes,
        String imageUrl
) {
}
