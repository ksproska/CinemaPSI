package com.cinema.definelocal.web.controllers;

import com.cinema.definelocal.web.requests.Cinema;
import com.cinema.definelocal.web.requests.MovieTitle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShowDefinitionController {
    private static final List<Cinema> cinemas = List.of(
            new Cinema(1L, "Wrocław"),
            new Cinema(2L, "Warszawa"),
            new Cinema(3L, "Kraków")
    );

    private static final List<MovieTitle> movieTitles = List.of(
            new MovieTitle(1L, "MARVELS"),
            new MovieTitle(2L, "Chłopi"),
            new MovieTitle(3L, "Czas krwawego księżyca")
    );

    @GetMapping("/get-cinemas")
    public ResponseEntity<List<Cinema>> getCinemas() {
        return ResponseEntity.ok(cinemas);
    }

    @GetMapping("/get-movies")
    public ResponseEntity<List<MovieTitle>> getMovies() {
        return ResponseEntity.ok(movieTitles);
    }
}
