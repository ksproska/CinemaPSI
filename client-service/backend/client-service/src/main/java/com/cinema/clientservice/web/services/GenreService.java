package com.cinema.clientservice.web.services;

import com.cinema.clientservice.db.common.models.Genre;
import com.cinema.clientservice.db.common.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<String> getGenres() {
        return genreRepository.findAll().stream().map(Genre::getName).collect(Collectors.toList());
    }
}
