package com.cinema.ticketcollector.web.models;

import jakarta.persistence.*;

@Entity
@Table(name="SEATS")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private Long id;

    @Column(name = "row_num")
    private int row;

    @Column(name = "num")
    private int number;

    @Column(name = "hall_id")
    private Long hallId;
}
