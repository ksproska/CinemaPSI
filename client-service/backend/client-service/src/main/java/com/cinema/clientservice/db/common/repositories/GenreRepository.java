package com.cinema.clientservice.db.common.repositories;

import com.cinema.clientservice.db.common.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {}
