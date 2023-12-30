package com.cinema.definelocal.web.requests;

import com.cinema.definelocal.web.dtos.LanguageVersionNameDto;

import java.time.LocalDate;
import java.util.List;

public record MovieData(
        Long id,
        String title,
        String description,
        int lengthMinutes,
        String imageUrl,
        LocalDate dateSince,
        LocalDate dateUntil,
        List<String> genres,
        List<LanguageVersionNameDto> languageVersionNames
) {}
