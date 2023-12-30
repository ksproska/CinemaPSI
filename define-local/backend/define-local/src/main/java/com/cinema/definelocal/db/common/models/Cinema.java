package com.cinema.definelocal.db.common.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CINEMAS")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cinema_id")
    private Long id;

    @Column(name = "localization")
    private String localization;
}
