package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {
    List<Repertoire> getRepertoireByStartingAfterOrderByStarting(LocalDateTime localDateTime);
}
