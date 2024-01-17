package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {}
