package com.cinema.repertoire.web.dtos;

import java.time.LocalDateTime;

public class RepertoireDTO {
    private final LocalDateTime starting;
    private final LocalDateTime ending;
    private final Integer hallNumber;
    private final Long movieVersionId;

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
