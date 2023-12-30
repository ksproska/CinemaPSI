package com.cinema.definelocal.web.services;

import com.cinema.definelocal.db.common.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<String> findAllForMovieId(Long movieId) {
        return genreRepository.findAllGenresForMovieId(movieId);
    }
}
