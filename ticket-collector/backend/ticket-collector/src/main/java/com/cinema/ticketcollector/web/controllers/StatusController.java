package com.cinema.ticketcollector.web.controllers;

import com.cinema.ticketcollector.web.request.TicketRequest;
import com.cinema.ticketcollector.web.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StatusController {
    private static final Logger LOG = LoggerFactory.getLogger(StatusController.class);

    private final TicketService ticketService;

    public StatusController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/get-ticket")
    public ResponseEntity getTicketForId(@RequestBody TicketRequest ticketRequest) {
        LOG.info("Requested ticket with id {}", ticketRequest.ticketId());
        var response = ticketService
                .getTicketDtoForId(ticketRequest.ticketId())
                .map(ResponseEntity::ok);
        if (response.isEmpty()) {
            return ResponseEntity.badRequest().body("Ticket not found");
        }
        return response.get();
    }

    @PostMapping("/collect")
    public ResponseEntity<String> collectTicketWithId(@RequestBody TicketRequest ticketRequest) {
        LOG.info("Requested ticket with id {}", ticketRequest.ticketId());
        var validationResponse = ticketService.validateTicketForId(ticketRequest.ticketId());
        return validationResponse
                .map(
                        s -> ResponseEntity.badRequest().body(s)
                )
                .orElseGet(
                        () -> ResponseEntity.ok(
                                String.format("Ticket with requested id %d is collected successfully.", ticketRequest.ticketId())
                        )
                );
    }
}
