package com.cinema.definelocal.db.instance.models;

import jakarta.persistence.*;

@Entity
@Table(name="HALLS")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hall_id")
    private Long id;

    @Column(name="hall_number")
    private Integer hallNumber;

    @Column(name="rows_num")
    private Integer rowsNum;

    @Column(name="seats_in_row")
    private Integer seatsInRow;
    @Column(name="cinema_id")
    private Integer cinemaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public Integer getRowsNum() {
        return rowsNum;
    }

    public void setRowsNum(Integer rowsNum) {
        this.rowsNum = rowsNum;
    }

    public Integer getSeatsInRow() {
        return seatsInRow;
    }

    public void setSeatsInRow(Integer seatsInRow) {
        this.seatsInRow = seatsInRow;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }
}
