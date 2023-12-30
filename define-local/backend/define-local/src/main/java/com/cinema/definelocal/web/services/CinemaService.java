package com.cinema.definelocal.web.services;

import com.cinema.definelocal.db.common.models.Cinema;
import com.cinema.definelocal.db.common.repositories.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> getCinemas() {
        return cinemaRepository.findAll();
    }

    public Optional<Cinema> findById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId);
    }
}
