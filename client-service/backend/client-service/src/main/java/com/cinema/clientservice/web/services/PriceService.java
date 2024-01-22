package com.cinema.clientservice.web.services;

import com.cinema.clientservice.db.common.models.Price;
import com.cinema.clientservice.db.common.repositories.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getCurrentPrice() {
        var dateNow = LocalDateTime.now();
        return priceRepository.getPriceByDateSinceBeforeAndDateUntilAfter(dateNow, dateNow).orElseThrow();
    }
}
