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

    public LocalDateTime getEnding() {
        return ending;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }

    public Long getMovieVersionId() {
        return movieVersionId;
    }

    public RepertoireDTO(LocalDateTime starting, LocalDateTime ending, Integer hallNumber, Long versionId) {
        this.starting = starting;
        this.ending = ending;
        this.hallNumber = hallNumber;
        this.movieVersionId = versionId;
    }
}
