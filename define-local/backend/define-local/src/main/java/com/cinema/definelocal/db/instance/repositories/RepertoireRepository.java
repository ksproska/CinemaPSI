package com.cinema.definelocal.db.instance.repositories;

import com.cinema.definelocal.db.instance.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {
    List<Repertoire> findAllByEndingGreaterThanEqualAndStartingLessThanEqualAndHallIdIsInOrderByStarting(LocalDateTime start, LocalDateTime end, List<Long> hallIds);
}
