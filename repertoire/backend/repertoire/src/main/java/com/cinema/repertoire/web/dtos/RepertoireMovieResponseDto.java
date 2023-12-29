package com.cinema.repertoire.web.dtos;

import java.time.LocalDateTime;

public class RepertoireMovieResponseDto {
    private LocalDateTime starting;
    private LocalDateTime ending;
    private String title;
    private Integer hallNumber;
    private String description;
    private String image;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
