package com.cinema.definelocal.db.common.repositories;

import com.cinema.definelocal.db.common.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {}
