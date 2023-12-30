package com.cinema.definelocal.web.services;

import com.cinema.definelocal.db.common.repositories.MovieRepository;
import com.cinema.definelocal.web.dtos.MovieDataWithOfferDatesDto;
import com.cinema.definelocal.web.dtos.MovieTitleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieTitleDto> findAllMovieTitles() {
        return movieRepository.findAllMovieTitles();
    }

    public Optional<MovieDataWithOfferDatesDto> findMovieDataById(Long movieId) {
        return movieRepository.findMovieDataForMovieId(movieId).stream().findFirst();
    }
}
