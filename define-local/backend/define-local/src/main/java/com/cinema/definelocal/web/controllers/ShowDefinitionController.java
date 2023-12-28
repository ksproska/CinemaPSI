package com.cinema.definelocal.web.controllers;

import com.cinema.definelocal.web.requests.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
public class ShowDefinitionController {
    private static final List<Cinema> cinemas = List.of(
            new Cinema(1L, "Wrocław"),
            new Cinema(2L, "Warszawa"),
            new Cinema(3L, "Kraków")
    );

    private static final List<Movie> movies = List.of(
            new Movie(
                    1L,
                    "MARVELS",
                    "Moce Carol Danvers splatają się ze zdolnościami Kamali Khan i Moniki Rambeau, zmuszając je do współpracy, aby ocalić wszechświat.",
                    "https://fwcdn.pl/fpo/16/29/851629/8095510.6.jpg",
                    List.of("Action", "Adventure", "Fantasy"),
                    LocalDate.now().minusDays(20),
                    LocalDate.now().plusDays(30),
                    List.of(
                            new LanguageVersion(1L, "NAPISY PL"),
                            new LanguageVersion(3L, "DUBBING PL")
                    )
            ),
            new Movie(
                    2L,
                    "Chłopi",
                    "Na tle zmieniających się pór roku i sezonowych prac polowych rozgrywają się losy rodziny Borynów i pięknej, tajemniczej Jagny.",
                    "https://fwcdn.pl/fpo/79/62/857962/8095499.6.jpg",
                    List.of("Animation", "Drama", "History"),
                    LocalDate.now().minusDays(10),
                    LocalDate.now().plusDays(50),
                    List.of(
                            new LanguageVersion(2L, "NAPISY EN"),
                            new LanguageVersion(4L, "NAPISY UK")
                    )
            ),
            new Movie(
                    3L,
                    "Czas krwawego księżyca",
                    "Gdy na terenie zamieszkiwanym przez Osagów odkryta zostaje ropa naftowa, członkowie plemienia zaczynają ginąć lub umierać z nieznanych przyczyn. Sprawa ta przykuwa uwagę FBI i J. Edgara Hoovera.",
                    "https://fwcdn.pl/fpo/52/04/805204/8092241.6.jpg",
                    List.of("Crime", "Drama", "History", "Mystery", "Romance", "Western"),
                    LocalDate.now().minusDays(5),
                    LocalDate.now().plusDays(25),
                    List.of(
                            new LanguageVersion(1L, "NAPISY PL")
                    )
            )
    );

    @GetMapping("/get-cinemas-and-movies")
    public ResponseEntity<MovieAndCinemaOptionsResponse> getCinemasAndMovies() {
        var movieTitles = movies.stream().map(movie -> new MovieTitle(movie.id(), movie.title())).toList();
        // TODO: Connect to database
        return ResponseEntity.ok(new MovieAndCinemaOptionsResponse(movieTitles, cinemas));
    }

    @GetMapping("/get-define-repertoire-info")
    public ResponseEntity<DefineRepertoireInfoResponse> getDefineRepertoireInfo(@RequestBody MovieAndCinemaSelectionRequest movieAndCinemaSelectionRequest) {
        var cinema = cinemas.stream().filter(c -> Objects.equals(c.id(), movieAndCinemaSelectionRequest.cinemaId())).findFirst().orElseThrow();
        var movie = movies.stream().filter(m -> Objects.equals(m.id(), movieAndCinemaSelectionRequest.movieId())).findFirst().orElseThrow();
        // TODO: Connect to database
        return ResponseEntity.ok(new DefineRepertoireInfoResponse(cinema, movie));
    }
}
