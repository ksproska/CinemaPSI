package com.cinema.definelocal.web.services;

import com.cinema.definelocal.db.instance.models.Hall;
import com.cinema.definelocal.db.instance.repositories.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> findAllHalls() {
        return hallRepository.findAll();
    }
}
