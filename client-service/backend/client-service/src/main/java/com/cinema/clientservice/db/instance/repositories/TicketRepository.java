package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT t.seatId FROM Ticket t WHERE t.repertoireId = :repertoireId")
    List<Long> findAllSeatIdsForRepertoireId(Long repertoireId);
}
