package com.cinema.definelocal.web.controllers;

import com.cinema.definelocal.web.requests.DefineRepertoireInfoResponse;
import com.cinema.definelocal.web.requests.MovieAndCinemaOptionsResponse;
import com.cinema.definelocal.web.requests.MovieAndCinemaSelectionRequest;
import com.cinema.definelocal.web.requests.MovieData;
import com.cinema.definelocal.web.services.CinemaService;
import com.cinema.definelocal.web.services.GenreService;
import com.cinema.definelocal.web.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ShowDefinitionController {
    private final CinemaService cinemaService;
    private final MovieService movieService;
    private final GenreService genreService;

    public ShowDefinitionController(CinemaService cinemaService, MovieService movieService, GenreService genreService) {
        this.cinemaService = cinemaService;
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @GetMapping("/get-cinemas-and-movies")
    public ResponseEntity<MovieAndCinemaOptionsResponse> getCinemasAndMovies() {
        return ResponseEntity.ok(
                new MovieAndCinemaOptionsResponse(
                        movieService.findAllMovieTitles(),
                        cinemaService.getCinemas()
                )
        );
    }

    @GetMapping("/get-define-repertoire-info")
    public ResponseEntity<DefineRepertoireInfoResponse> getDefineRepertoireInfo(@RequestBody MovieAndCinemaSelectionRequest movieAndCinemaSelectionRequest) {
        var cinema = cinemaService.findById(movieAndCinemaSelectionRequest.cinemaId()).orElseThrow();
        var movie = movieService.findMovieDataById(movieAndCinemaSelectionRequest.movieId()).orElseThrow();
        var genres = genreService.findAllForMovieId(movie.id());
        var movieOffersVersion = movieService.findMovieOffersVersionForMovieId(movie.id());
        return ResponseEntity.ok(
                new DefineRepertoireInfoResponse(
                        cinema,
                        new MovieData(
                                movie.id(),
                                movie.title(),
                                movie.description(),
                                movie.lengthMinutes(),
                                movie.imageUrl(),
                                genres,
                                movieOffersVersion
                        )
                )
        );
    }
}
