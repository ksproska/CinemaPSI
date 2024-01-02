package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.requests.MovieWithRepertoiresAndDateResponse;
import com.cinema.clientservice.web.services.RepertoireService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MovieController {
    private final RepertoireService repertoireService;

    public MovieController(RepertoireService repertoireService) {
        this.repertoireService = repertoireService;
    }

    @GetMapping("/get-movies-details")
    public ResponseEntity<List<MovieWithRepertoiresAndDateResponse>> getMoviesDetails() {
        return ResponseEntity.ok(
                repertoireService.getMovieWithRepertoireDetails()
        );
    }
}
