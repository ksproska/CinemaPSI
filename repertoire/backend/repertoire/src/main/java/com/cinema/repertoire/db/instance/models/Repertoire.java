package com.cinema.repertoire.db.instance.models;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStarting() {
        return starting;
    }

    public void setStarting(LocalDateTime starting) {
        this.starting = starting;
    }

    public LocalDateTime getEnding() {
        return ending;
    }

    public void setEnding(LocalDateTime ending) {
        this.ending = ending;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public Long getMovieVersionId() {
        return movieVersionId;
    }

    public void setMovieVersionId(Long versionId) {
        this.movieVersionId = versionId;
    }
}
