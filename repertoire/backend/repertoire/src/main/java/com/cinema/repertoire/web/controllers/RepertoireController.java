package com.cinema.repertoire.web.controllers;

import com.cinema.repertoire.web.dtos.RepertoireMovieResponseDto;
import com.cinema.repertoire.web.dtos.RepertoireRequestDto;
import com.cinema.repertoire.web.services.RepertoireService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RepertoireController {
    private final RepertoireService repertoireService;

    public RepertoireController(RepertoireService repertoireService) {
        this.repertoireService = repertoireService;
    }

    @GetMapping("/get-repertoire2")
    public ResponseEntity<List<RepertoireMovieResponseDto>> getRepertoireForCinema(RepertoireRequestDto request) {
        return ResponseEntity.ok(repertoireService.createRepertoireResponsesForRequest(request));
    }
}
