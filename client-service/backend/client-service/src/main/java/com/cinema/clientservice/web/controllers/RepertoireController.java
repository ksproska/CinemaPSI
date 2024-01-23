package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.requests.MovieWithRepertoires;
import com.cinema.clientservice.web.requests.MovieWithRepertoiresAndDateResponse;
import com.cinema.clientservice.web.requests.RepertoiresForSingleMovie;
import com.cinema.clientservice.web.services.RepertoireService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @GetMapping("/get-future-repertoire-by-date")
    public ResponseEntity<List<MovieWithRepertoires>> getRepertoireDetailsByDate(@RequestParam LocalDate afterDate, @RequestParam String city) {
        return ResponseEntity.ok(repertoireService.getRepertoireDetailsByDate(afterDate));
    }

    @GetMapping("/get-future-repertoire/{city}/{movieId}")
    public ResponseEntity<RepertoiresForSingleMovie> getRepertoireDetailsForMovie(@PathVariable Long movieId, @PathVariable String city) {
        return ResponseEntity.ok(repertoireService.getFutureRepertoireForMovie(movieId));
    }
}
