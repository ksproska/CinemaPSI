package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Seat;
import com.cinema.clientservice.web.dtos.SeatReservationDto;
import com.cinema.clientservice.web.dtos.SeatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT new com.cinema.clientservice.web.dtos.SeatsDto(s.id, s.number, s.row, t) FROM Seat s" +
            " LEFT JOIN Ticket t ON s.id = t.seatId WHERE s.hallId = :hallId AND (t IS NULL OR t.repertoireId = :repertoireId)")
    List<SeatsDto> findAllSeatsForRepertoireId(Long repertoireId, Long hallId);

    @Query(value = "SELECT new com.cinema.clientservice.web.dtos.SeatReservationDto(s.id, s.number, s.row) FROM Seat s" +
            " LEFT JOIN TicketReservation tr ON s.id = tr.seatId WHERE s.hallId = :hallId AND tr.repertoireId = :repertoireId")
    List<SeatReservationDto> findAllSeatReservedForRepertoireId(Long repertoireId, Long hallId);
}
