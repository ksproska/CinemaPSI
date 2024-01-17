package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.requests.HallSetupForRepertoire;
import com.cinema.clientservice.web.services.HallSetupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HallSetupController {
    private final HallSetupService hallSetupService;

    public HallSetupController(HallSetupService hallSetupService) {
        this.hallSetupService = hallSetupService;
    }

    @GetMapping("/hall-setup/{repertoireId}")
    public ResponseEntity<HallSetupForRepertoire> getRepertoireDetails(@PathVariable Long repertoireId) {
        return ResponseEntity.ok(hallSetupService.getHallSetup(repertoireId));
    }
}
