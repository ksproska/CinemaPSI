package com.cinema.ticketcollector.web.services;

import com.cinema.ticketcollector.web.dtos.TicketDto;
import com.cinema.ticketcollector.web.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<TicketDto> getTicketDtoForId(Long ticketId) {
        return ticketRepository.findTicketDtos(ticketId).stream().findFirst();
    }

    public Optional<String> validateTicketForId(Long ticketId) {
        var ticketOptional = ticketRepository.findById(ticketId);
        if (ticketOptional.isEmpty()) return Optional.of("Ticket not found");
        var ticket = ticketOptional.get();
        var wasValidationSuccessful = ticket.setValidated();
        if (!wasValidationSuccessful) return Optional.of("Ticket cannot be validated");
        ticketRepository.save(ticket);
        return Optional.empty();
    }
}
