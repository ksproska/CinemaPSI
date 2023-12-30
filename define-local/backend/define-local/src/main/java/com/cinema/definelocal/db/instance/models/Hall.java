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

}
