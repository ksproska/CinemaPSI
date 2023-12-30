package com.cinema.definelocal.db.common.repositories;

import com.cinema.definelocal.db.common.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {}
