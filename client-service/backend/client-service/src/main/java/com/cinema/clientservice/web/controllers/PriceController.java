package com.cinema.clientservice.web.controllers;


import com.cinema.clientservice.db.common.models.Price;
import com.cinema.clientservice.db.common.repositories.PriceRepository;
import com.cinema.clientservice.web.requests.MovieWithRepertoiresAndDateResponse;
import com.cinema.clientservice.web.services.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PriceController {

    private PriceService priceService;
    public PriceController(PriceService priceService){
        this.priceService = priceService;
    }

    @GetMapping("/get-current-price")
    public ResponseEntity<Price> getRepertoireDetails() {
        return ResponseEntity.ok(priceService.getCurrentPrice());
    }
}
