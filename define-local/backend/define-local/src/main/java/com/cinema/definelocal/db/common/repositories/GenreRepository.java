package com.cinema.definelocal.db.common.repositories;

import com.cinema.definelocal.db.common.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(value = "SELECT g.name FROM Genre g LEFT JOIN MoviesGenresMap mgm ON mgm.id.genreId = g.id WHERE mgm.id.movieId = :movieId")
    List<String> findAllGenresForMovieId(Long movieId);
}
