package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Ticket;
import com.cinema.clientservice.web.dtos.SingleTicketDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT t.seatId FROM Ticket t WHERE t.repertoireId = :repertoireId")
    List<Long> findAllSeatIdsForRepertoireId(Long repertoireId);

    @Query(value = "SELECT new com.cinema.clientservice.web.dtos.SingleTicketDetailsDto(r.movieVersionId, t.isStudent, t.price, r.starting, s.number, s.row, t.id, t.isValidated) FROM Ticket t LEFT JOIN Repertoire r ON t.repertoireId = r.id LEFT JOIN Seat s ON t.seatId = s.id")
    List<SingleTicketDetailsDto> findAllTicketDetails();
}
