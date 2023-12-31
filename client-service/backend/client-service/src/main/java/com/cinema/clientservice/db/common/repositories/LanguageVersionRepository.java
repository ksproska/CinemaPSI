package com.cinema.clientservice.db.common.repositories;

import com.cinema.clientservice.db.common.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageVersionRepository extends JpaRepository<Cinema, Long> {}
