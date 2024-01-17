package com.cinema.clientservice.web.dtos;

public record SeatInfo(
        Long seatId,
        boolean isTaken
) {
}
