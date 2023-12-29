package com.cinema.repertoire.web.dtos;

public class RepertoireRequestDto {
    private Integer hoursInterval;
    private Long cinemaId;

    public Integer getHoursInterval() {
        return hoursInterval;
    }

    public void setHoursInterval(Integer hoursInterval) {
        this.hoursInterval = hoursInterval;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }
}
