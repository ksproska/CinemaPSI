package com.cinema.repertoire.web.controllers;

import com.cinema.repertoire.web.request.Movie;
import com.cinema.repertoire.web.request.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class RepertoireController {
    private static final Logger LOG = LoggerFactory.getLogger(RepertoireController.class);

    @GetMapping("/get-repertoire/{cinemaId}")
    public ResponseEntity<List<Movie>> getRepertoireForCinema(@PathVariable("cinemaId") Integer cinemaId) {
        LOG.info("Requested repertoire for cinemaId {}", cinemaId);
        // TODO: Connect to database
        var timeNow = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
        var tempResponse = List.of(
                new Movie(
                        "MARVELS",
                        "Moce Carol Danvers splatają się ze zdolnościami Kamali Khan i Moniki Rambeau, zmuszając je do współpracy, aby ocalić wszechświat.",
                        "https://fwcdn.pl/fpo/16/29/851629/8095510.6.jpg",
                        List.of(
                                new Show(
                                        1,
                                        timeNow.plusHours(1),
                                        timeNow.plusHours(3)
                                ),
                                new Show(
                                        2,
                                        timeNow.plusHours(1).plusMinutes(15),
                                        timeNow.plusHours(3).plusMinutes(15)
                                )
                        )
                ),
                new Movie(
                        "Chłopi",
                        "Na tle zmieniających się pór roku i sezonowych prac polowych rozgrywają się losy rodziny Borynów i pięknej, tajemniczej Jagny.",
                        "https://fwcdn.pl/fpo/79/62/857962/8095499.6.jpg",
                        List.of(
                                new Show(
                                        3,
                                        timeNow.plusMinutes(15),
                                        timeNow.plusHours(2)
                                ),
                                new Show(
                                        2,
                                        timeNow.plusHours(1).plusMinutes(15).plusMinutes(15),
                                        timeNow.plusHours(3).plusMinutes(15).plusHours(2)
                                )
                        )
                ),
                new Movie(
                        "Czas krwawego księżyca",
                        "Gdy na terenie zamieszkiwanym przez Osagów odkryta zostaje ropa naftowa, członkowie plemienia zaczynają ginąć lub umierać z nieznanych przyczyn. Sprawa ta przykuwa uwagę FBI i J. Edgara Hoovera.",
                        "https://fwcdn.pl/fpo/52/04/805204/8092241.6.jpg",
                        List.of(
                                new Show(
                                        5,
                                        timeNow.plusMinutes(30),
                                        timeNow.plusHours(3)
                                ),
                                new Show(
                                        3,
                                        timeNow.plusMinutes(15).plusMinutes(30),
                                        timeNow.plusHours(2).plusHours(3)
                                )
                        )
                )
        );
        return ResponseEntity.ok(tempResponse);
    }
}
