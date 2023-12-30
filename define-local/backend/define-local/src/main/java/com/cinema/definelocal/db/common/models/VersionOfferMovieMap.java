package com.cinema.definelocal.db.common.models;

import jakarta.persistence.*;

@Entity
@Table(name="VERSION_OFFER_MOVIE_MAP")
public class VersionOfferMovieMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="VERSION_OFFER_MOVIE_ID")
    private Long id;

    @Column(name="MOVIE_ID")
    private Long movieId;

    @Column(name="VERSION_ID")
    private Long versionId;

    @Column(name="OFFER_ID")
    private Long offerId;
}
