package com.cinema.ticketcollector.web.controllers;

import com.cinema.ticketcollector.web.request.Ticket;
import com.cinema.ticketcollector.web.request.TicketRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Controller
public class StatusController {
    private static final Logger LOG = LoggerFactory.getLogger(StatusController.class);
    private static final List<Ticket> tempTicketsDb = List.of(
            new Ticket(1L, true, false, 1, 10, 5),
            new Ticket(2L, true, true, 1, 11, 6),
            new Ticket(3L, false, false, 1, 12, 1)
    );

    @GetMapping("/get-ticket")
    public ResponseEntity getTicketForId(@RequestBody TicketRequest ticketRequest) {
        LOG.info("Requested ticket with id {}", ticketRequest.ticketId());
        // TODO: Connect to database
        var tempResponse = tempTicketsDb.stream().filter(t -> Objects.equals(t.getId(), ticketRequest.ticketId())).findFirst();
        if (tempResponse.isPresent()) {
            return ResponseEntity.ok(tempResponse.get());
        }
        return ResponseEntity.badRequest().body(String.format("Ticket with requested id %d not found.", ticketRequest.ticketId()));
    }

    @PostMapping("/collect")
    public ResponseEntity collectTicketWithId(@RequestBody TicketRequest ticketRequest) {
        LOG.info("Requested ticket with id {}", ticketRequest.ticketId());
        // TODO: Connect to database
        var tempResponse = tempTicketsDb
                .stream()
                .filter(t -> Objects.equals(t.getId(), ticketRequest.ticketId()))
                .findFirst();
        if (tempResponse.isEmpty()) {
            return ResponseEntity.badRequest().body(String.format("Ticket with requested id %d not found.", ticketRequest.ticketId()));
        }
        if (!tempResponse.get().isValid()) {
            return ResponseEntity.badRequest().body(String.format("Ticket with requested id %d is not valid.", ticketRequest.ticketId()));
        }
        tempResponse.get().setNotValid();
        return ResponseEntity.ok(String.format("Ticket with requested id %d is collected successfully.", ticketRequest.ticketId()));
    }
}
