package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.requests.MovieWithRepertoiresAndDateResponse;
import com.cinema.clientservice.web.requests.RepertoiresForSingleMovie;
import com.cinema.clientservice.web.services.RepertoireService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RepertoireController {
    private final RepertoireService repertoireService;

    public RepertoireController(RepertoireService repertoireService) {
        this.repertoireService = repertoireService;
    }

    @GetMapping("/get-future-repertoire")
    public ResponseEntity<List<MovieWithRepertoiresAndDateResponse>> getRepertoireDetails() {
        return ResponseEntity.ok(repertoireService.getFutureRepertoiresWithMovieDetails());
    }

    @GetMapping("/get-future-repertoire/{movieId}")
    public ResponseEntity<RepertoiresForSingleMovie> getRepertoireDetailsForMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(repertoireService.getFutureRepertoireForMovie(movieId));
    }
}
