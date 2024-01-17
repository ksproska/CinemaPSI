package com.cinema.clientservice.web.dtos;

import com.cinema.clientservice.db.instance.models.Ticket;

public record SeatsDto(Long seatId, int number, int row, Ticket ticket) {}
