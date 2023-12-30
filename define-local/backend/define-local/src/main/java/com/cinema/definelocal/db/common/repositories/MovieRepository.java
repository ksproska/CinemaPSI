package com.cinema.definelocal.db.common.repositories;

import com.cinema.definelocal.db.common.models.Movie;
import com.cinema.definelocal.web.dtos.MovieDataWithOfferDatesDto;
import com.cinema.definelocal.web.dtos.MovieTitleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT new com.cinema.definelocal.web.dtos.MovieTitleDto(m.id, m.title) FROM Movie m")
    List<MovieTitleDto> findAllMovieTitles();

    @Query(value = "SELECT new com.cinema.definelocal.web.dtos.MovieDataWithOfferDatesDto(m.id, m.title, m.description, m.duration, m.image, mo.dateSince, mo.dateUntil) FROM Movie m " +
            "LEFT JOIN MovieOffer mo ON m.id = mo.movieId WHERE m.id = :movieId"
    )
    List<MovieDataWithOfferDatesDto> findMovieDataForMovieId(Long movieId);
}
