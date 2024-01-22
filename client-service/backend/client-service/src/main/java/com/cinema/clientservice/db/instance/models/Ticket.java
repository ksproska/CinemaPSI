package com.cinema.clientservice.db.instance.models;

import jakarta.persistence.*;

@Entity
@Table(name="TICKETS")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "is_validated")
    private boolean isValidated;

    @Column(name = "is_student")
    private boolean isStudent;

    @Column(name = "price")
    private double price;

    @Column(name = "repertoire_id")
    private Long repertoireId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "price_id")
    private Long priceId;

    public Ticket(){}

    public Ticket(boolean isStudent, double price, Long repertoireId, Long seatId, Long priceId) {
        this.isValidated = false;
        this.isStudent = isStudent;
        this.price = price;
        this.repertoireId = repertoireId;
        this.seatId = seatId;
        this.priceId = priceId;
    }
}
