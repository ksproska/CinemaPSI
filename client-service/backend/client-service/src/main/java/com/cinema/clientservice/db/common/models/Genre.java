package com.cinema.clientservice.db.common.models;

import jakarta.persistence.*;

@Entity
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private Long id;

    @Column(name = "genre_name")
    private String name;
}
