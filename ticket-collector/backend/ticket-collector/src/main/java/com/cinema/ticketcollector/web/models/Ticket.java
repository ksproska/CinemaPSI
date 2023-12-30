package com.cinema.ticketcollector.web.models;

import jakarta.persistence.*;

@Entity
@Table(name="TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "is_validated")
    private boolean isValidated;

    @Column(name = "is_student")
    private boolean isStudent;

    @Column(name = "price")
    private double price;

    @Column(name = "hall_id")
    private Long hallId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "price_id")
    private Long priceId;

    public boolean setValidated() {
        if (isValidated) return false;
        isValidated = true;
        return true;
    }
}
