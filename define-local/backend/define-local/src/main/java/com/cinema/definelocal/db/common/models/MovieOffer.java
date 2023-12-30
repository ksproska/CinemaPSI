package com.cinema.definelocal.db.common.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MOVIE_OFFER")
public class MovieOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id")
    private Long id;

    @Column(name = "date_since")
    private LocalDate dateSince;

    @Column(name = "date_untill")
    private LocalDate dateUntil;

    @Column(name = "movie_id")
    private Long movieId;
}
