package com.cinema.definelocal.web.controllers;

import com.cinema.definelocal.db.instance.models.Hall;
import com.cinema.definelocal.db.instance.repositories.HallRepository;
import com.cinema.definelocal.web.requests.*;
import com.cinema.definelocal.web.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ShowDefinitionController {
    private final CinemaService cinemaService;
    private final MovieService movieService;
    private final GenreService genreService;
    private final RepertoireService repertoireService;

    private final HallService hallService;

    public ShowDefinitionController(CinemaService cinemaService, MovieService movieService, GenreService genreService, RepertoireService repertoireService, HallService hallService) {
        this.cinemaService = cinemaService;
        this.movieService = movieService;
        this.genreService = genreService;
        this.repertoireService = repertoireService;
        this.hallService = hallService;
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
    public ResponseEntity<DefineRepertoireInfoResponse> getDefineRepertoireInfo(MovieAndCinemaSelectionRequest movieAndCinemaSelectionRequest) {
        var cinema = cinemaService.findById(movieAndCinemaSelectionRequest.cinemaId()).orElseThrow();
        var movie = movieService.findMovieDataById(movieAndCinemaSelectionRequest.movieId()).orElseThrow();
        var genres = genreService.findAllForMovieId(movie.id());
        var movieOffersVersion = movieService.findMovieOffersVersionForMovieId(movie.id());
        var halls = hallService.findAllHalls();
        return ResponseEntity.ok(
                new DefineRepertoireInfoResponse(
                        cinema,
                        halls,
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

    @PostMapping("/add-repertoire-for-movie")
    public ResponseEntity<String> addRepertoire(@RequestBody RepertoiresRequest repertoireRequest) {
        // TODO this endpoint creates required Repertoires but does not save it to db
        var responseMessage = repertoireService.addRepertoires(repertoireRequest);
        return responseMessage
                .map(ResponseEntity.badRequest()::body)
                .orElseGet(() -> ResponseEntity.ok("Repertoires were added successfully."));
    }
}
