package com.cinema.clientservice.db.instance.repositories;

import com.cinema.clientservice.db.instance.models.TicketReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketReservationRepository extends JpaRepository<TicketReservation, Long> {
    List<TicketReservation> findAllByReservationId(Long reservationId);
    List<TicketReservation> findAllByPaymentId(Long paymentId);

    @Query(value = "SELECT tr.price FROM TicketReservation tr WHERE tr.reservationId = :reservationId")
    List<Double> getAllPricesForReservationId(Long reservationId);

    @Query(value = "SELECT DISTINCT tr.reservationId FROM TicketReservation tr")
    List<Long> getAllReservationIds();

    @Query(value = "SELECT t.seatId FROM TicketReservation t WHERE t.repertoireId = :repertoireId")
    List<Long> findAllSeatIdsForRepertoireId(Long repertoireId);
}
