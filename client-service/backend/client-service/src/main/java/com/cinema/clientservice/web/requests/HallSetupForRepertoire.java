package com.cinema.clientservice.web.requests;

import com.cinema.clientservice.web.dtos.SeatInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public record HallSetupForRepertoire(
        String movieTitle,
        String languageVersion,
        Long repertoireId,
        Long hallId,
        LocalDate date,
        LocalDateTime startingTime,

        Integer numbOfRows,
        Integer numbOfSeatsInRow,
        ArrayList<ArrayList<SeatInfo>> seats
) {}
