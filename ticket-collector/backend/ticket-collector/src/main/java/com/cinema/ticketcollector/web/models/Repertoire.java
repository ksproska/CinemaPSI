package com.cinema.ticketcollector.web.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="REPERTOIRE")
public class Repertoire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "repertoire_id")
    private Long id;
    private LocalDateTime starting;
    private LocalDateTime ending;
    @Column(name="HALL_ID")
    private Long hallId;
    @Column(name="MOVIE_VERSION_ID")
    private Long movieVersionId;
}
