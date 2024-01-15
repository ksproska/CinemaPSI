package com.cinema.ticketcollector.web.controllers;

import com.cinema.ticketcollector.web.dtos.TicketCollectionResponseDto;
import com.cinema.ticketcollector.web.dtos.TicketDto;
import com.cinema.ticketcollector.web.request.TicketRequest;
import com.cinema.ticketcollector.web.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StatusController {
    private static final Logger LOG = LoggerFactory.getLogger(StatusController.class);

    private final TicketService ticketService;

    public StatusController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/get-ticket/{ticketId}")
    public ResponseEntity<TicketDto> getTicketForId(@PathVariable Long ticketId) {
        LOG.info("Requested ticket with id {}", ticketId);
        var response = ticketService
                .getTicketDtoForId(ticketId)
                .map(ResponseEntity::ok);
        return response.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/collect")
    public ResponseEntity<TicketCollectionResponseDto> collectTicketWithId(@RequestBody TicketRequest ticketRequest) {
        LOG.info("Requested ticket with id {}", ticketRequest.ticketId());
        var validationResponse = ticketService.validateTicketForId(ticketRequest.ticketId());
        return validationResponse
                .map(
                        s -> ResponseEntity.badRequest().body(s)
                )
                .orElseGet(
                        () -> ResponseEntity.ok(
                                new TicketCollectionResponseDto(String.format("Bilet z id %d skasowany", ticketRequest.ticketId()))
                        )
                );
    }
}
