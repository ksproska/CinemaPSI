package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {
    List<Repertoire> getRepertoireByStartingAfterOrderByStarting(LocalDateTime localDateTime);
    List<Repertoire> getRepertoireByStartingAfterAndMovieVersionIdIsInOrderByStarting(LocalDateTime localDateTime, List<Long> movieVersionIds);
    @Query(value = "SELECT distinct s.id FROM Repertoire r LEFT JOIN Seat s ON r.hallId = s.hallId WHERE r.id = :repertoireId")
    List<Long> getSeatIdsForRepertoireId(Long repertoireId);

    @Query(value = "SELECT DISTINCT r FROM TicketReservation tr LEFT JOIN Repertoire r ON tr.repertoireId = r.id WHERE tr.reservationId = :reservationId")
    Optional<Repertoire> findRepertoireByReservationId(Long reservationId);

    List<Repertoire> getRepertoireByStartingAfterAndStartingBefore(LocalDateTime startingAfter, LocalDateTime startingBefore);


    @Query(value = "SELECT movie_version_id FROM REPERTOIRE WHERE starting > :startingAfter AND starting < :startingBefore", nativeQuery = true)
    List<Long> getMovieVersionIdByStartingAfterAndStartingBeforeOrderByStarting(LocalDateTime startingAfter, LocalDateTime startingBefore);

}
