package com.cinema.clientservice.db.common.models;

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

    public Cinema() {}

    public Cinema(Long id, String localization) {
        this.id = id;
        this.localization = localization;
    }

    public Long getId() {
        return id;
    }

    public String getLocalization() {
        return localization;
    }
}
