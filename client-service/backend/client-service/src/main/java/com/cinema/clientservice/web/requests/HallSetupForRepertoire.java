package com.cinema.clientservice.web.requests;

import com.cinema.clientservice.web.dtos.SeatInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record HallSetupForRepertoire(
        String movieTitle,
        String languageVersion,
        Long repertoireId,
        Long hallId,
        LocalDate date,
        LocalTime startingTime,

        Integer numbOfRows,
        Integer numbOfSeatsInRow,
        List<List<SeatInfo>> seats
) {}
