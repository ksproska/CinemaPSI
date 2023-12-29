package com.cinema.repertoire.web.controllers;

import com.cinema.repertoire.web.dtos.RepertoireMovieResponseDto;
import com.cinema.repertoire.web.dtos.RepertoireRequestDto;
import com.cinema.repertoire.web.services.RepertoireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class RepertoireController {
    private static final Logger LOG = LoggerFactory.getLogger(RepertoireController.class);

    @Autowired
    RepertoireService repertoireService;

    @GetMapping("/get-repertoire2")
    public ResponseEntity<List<RepertoireMovieResponseDto>> getRepertoire2ForCinema(@RequestBody RepertoireRequestDto request) {
        LocalDateTime end = LocalDateTime.now().plusHours(request.getHoursInterval());
        return ResponseEntity.ok(repertoireService.createRepertoireResponses(request.getCinemaId(), end));
    }
}
