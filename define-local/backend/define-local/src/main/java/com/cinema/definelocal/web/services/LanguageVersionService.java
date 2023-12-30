package com.cinema.definelocal.web.services;

import com.cinema.definelocal.db.common.repositories.LanguageVersionRepository;
import com.cinema.definelocal.web.dtos.LanguageVersionNameDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageVersionService {
    private final LanguageVersionRepository languageVersionRepository;

    public LanguageVersionService(LanguageVersionRepository languageVersionRepository) {
        this.languageVersionRepository = languageVersionRepository;
    }

    public List<LanguageVersionNameDto> findAllLanguageVersionNamesForMovieId(Long movieId) {
        return languageVersionRepository.findAllLanguageVersionNamesForMovieId(movieId);
    }
}
