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

    List<Repertoire> getRepertoireByStartingAfterAndStartingBefore(LocalDateTime startingAfter, LocalDateTime startingBefore);


    @Query(value = "SELECT movie_version_id FROM REPERTOIRE WHERE starting > :startingAfter AND starting < :startingBefore", nativeQuery = true)
    List<Long> getMovieVersionIdByStartingAfterAndStartingBeforeOrderByStarting(LocalDateTime startingAfter, LocalDateTime startingBefore);

}
