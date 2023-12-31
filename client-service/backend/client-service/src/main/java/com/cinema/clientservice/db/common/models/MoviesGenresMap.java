package com.cinema.clientservice.db.common.models;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "MOVIES_GENRES_MAP")
public class MoviesGenresMap {
    @Embeddable
    private class IdReplacement implements Serializable {
        @Column(name = "genre_id")
        private Long genreId;

        @Column(name = "movie_id")
        private Long movieId;
    }

    @EmbeddedId
    private IdReplacement id;
}
