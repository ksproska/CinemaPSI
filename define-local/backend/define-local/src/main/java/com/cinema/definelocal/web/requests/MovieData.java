package com.cinema.definelocal.web.requests;

import com.cinema.definelocal.web.dtos.MovieOfferVersionDto;

import java.util.List;

public record MovieData(
        Long id,
        String title,
        String description,
        int lengthMinutes,
        String imageUrl,
        List<String> genres,
        List<MovieOfferVersionDto> movieOffers
) {}
