package com.cinema.clientservice.db.instance.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="TICKETS_RESERVATIONS")
public class TicketReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_reservation_id")
    private Long id;

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

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "payment_service_name", nullable = true)
    private String paymentServiceName;

    @Column(name = "payment_id", nullable = true)
    private Long paymentId;

    @Column(name = "reservation_timestamp")
    private LocalDateTime reservationTimestamp;

    public TicketReservation(){}

    public TicketReservation(
            boolean isStudent,
            double price,
            Long repertoireId,
            Long seatId,
            Long priceId,
            Long reservationId,
            LocalDateTime reservationTimestamp
    ) {
        this.isStudent = isStudent;
        this.price = price;
        this.repertoireId = repertoireId;
        this.seatId = seatId;
        this.priceId = priceId;
        this.reservationId = reservationId;
        this.reservationTimestamp = reservationTimestamp;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public double getPrice() {
        return price;
    }

    public Long getRepertoireId() {
        return repertoireId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPaymentServiceName(String paymentServiceName) {
        this.paymentServiceName = paymentServiceName;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
}
