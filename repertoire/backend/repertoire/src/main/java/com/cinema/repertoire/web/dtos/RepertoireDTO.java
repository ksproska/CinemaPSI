package com.cinema.repertoire.web.dtos;

import java.time.LocalDateTime;

public class RepertoireDTO {
    private LocalDateTime starting;
    private LocalDateTime ending;
    private Integer hallNumber;
    private Long movieVersionId;

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

    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public Long getMovieVersionId() {
        return movieVersionId;
    }

    public void setMovieVersionId(Long movieVersionId) {
        this.movieVersionId = movieVersionId;
    }

    public RepertoireDTO(LocalDateTime starting, LocalDateTime ending, Integer hallNumber, Long versionId) {
        this.starting = starting;
        this.ending = ending;
        this.hallNumber = hallNumber;
        this.movieVersionId = versionId;
    }
}
