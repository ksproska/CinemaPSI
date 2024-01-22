package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/get-all-genres")
    public ResponseEntity<List<String>> getAllGenres() {
        return ResponseEntity.ok(genreService.getGenres());
    }
}
