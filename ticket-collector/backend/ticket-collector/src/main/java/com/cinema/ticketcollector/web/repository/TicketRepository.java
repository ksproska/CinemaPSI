package com.cinema.ticketcollector.web.repository;

import com.cinema.ticketcollector.web.models.Ticket;
import com.cinema.ticketcollector.web.dtos.TicketDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT new com.cinema.ticketcollector.web.dtos.TicketDto(t.id, t.isValidated, t.isStudent, h.hallNumber, s.row, s.number) " +
            "FROM Ticket t LEFT JOIN Hall h ON t.hallId = h.id LEFT JOIN Seat s ON t.seatId = s.id WHERE t.id = :ticketId")
    List<TicketDto> findTicketDtos(Long ticketId);
}
