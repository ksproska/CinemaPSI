package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {
    List<Repertoire> getRepertoireByStartingAfterOrderByStarting(LocalDateTime localDateTime);
    List<Repertoire> getRepertoireByStartingAfterAndMovieVersionIdIsInOrderByStarting(LocalDateTime localDateTime, List<Long> movieVersionIds);
    @Query(value = "SELECT distinct s.id FROM Repertoire r LEFT JOIN Seat s ON r.hallId = s.hallId WHERE r.id = :repertoireId")
    List<Long> getSeatIdsForRepertoireId(Long repertoireId);
}
