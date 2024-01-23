package com.cinema.clientservice.web.controllers;

import com.cinema.clientservice.web.exceptions.SeatTakenException;
import com.cinema.clientservice.web.requests.AllBoughtTicketsDetails;
import com.cinema.clientservice.web.requests.Reservation;
import com.cinema.clientservice.web.requests.ReservationDetails;
import com.cinema.clientservice.web.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public record ReservationId(Long reservationId){}

    @PostMapping("/reserve-tickets/{city}")
    public ResponseEntity getRepertoireDetailsForMovie(@RequestBody Reservation reservation, @PathVariable String city) {
        try {
            var reservationId = ticketService.addMultipleTicketReservations(reservation);
            return ResponseEntity.ok(new ReservationId(reservationId));
        } catch (SeatTakenException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-reservation-details/{city}/{reservationId}")
    public ResponseEntity<ReservationDetails> getReservationDetails(@PathVariable Long reservationId, @PathVariable String city) {
        return ResponseEntity.ok(ticketService.getReservationDetails(reservationId));
    }

    @GetMapping("/get-all-tickets")
    public ResponseEntity<AllBoughtTicketsDetails> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllBoughtTicketDetails());
    }
}
