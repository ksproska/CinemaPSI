package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.exceptions.SeatTakenException;
import com.cinema.clientservice.web.requests.Reservation;
import com.cinema.clientservice.web.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public record ReservationId(Long reservationId){}

    @PostMapping("/reserve-tickets")
    public ResponseEntity getRepertoireDetailsForMovie(@RequestBody Reservation reservation) {
        try {
            var reservationId = ticketService.addMultipleTicketReservations(reservation);
            return ResponseEntity.ok(new ReservationId(reservationId));
        } catch (SeatTakenException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
