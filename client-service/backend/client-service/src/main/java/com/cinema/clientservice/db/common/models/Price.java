package com.cinema.clientservice.db.common.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_id")
    private Long id;

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name = "reduction_pct")
    private Integer reductionPct;

    @Column(name = "promotion_pct")
    private Integer promotionPct;

    @Column(name = "date_since")
    private LocalDateTime dateSince;

    @Column(name = "date_untill")
    private LocalDateTime dateUntil;

    public Double getBasePrice() {
        return basePrice;
    }

    public Integer getReductionPct() {
        return reductionPct;
    }

    public Integer getPromotionPct() {
        return promotionPct;
    }

    public Long getId() {
        return id;
    }
}
