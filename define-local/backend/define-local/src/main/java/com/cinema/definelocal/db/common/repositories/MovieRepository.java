package com.cinema.definelocal.db.common.repositories;

import com.cinema.definelocal.db.common.models.Movie;
import com.cinema.definelocal.web.dtos.MovieDataDto;
import com.cinema.definelocal.web.dtos.MovieOfferVersionDto;
import com.cinema.definelocal.web.dtos.MovieTitleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT new com.cinema.definelocal.web.dtos.MovieTitleDto(m.id, m.title) FROM Movie m")
    List<MovieTitleDto> findAllMovieTitles();

    @Query(value = "SELECT new com.cinema.definelocal.web.dtos.MovieDataDto(m.id, m.title, m.description, m.duration, m.image) FROM Movie m WHERE m.id = :movieId")
    List<MovieDataDto> findMovieDataForMovieId(Long movieId);

    @Query(value = "SELECT new com.cinema.definelocal.web.dtos.MovieOfferVersionDto(vomm.id, m.id, m.dateSince, m.dateUntil, lv.id, lv.versionName) FROM MovieOffer m " +
            "LEFT JOIN VersionOfferMovieMap vomm ON m.movieId = vomm.movieId " +
            "LEFT JOIN LanguageVersion lv ON vomm.versionId = lv.id WHERE m.movieId = :movieId")
    List<MovieOfferVersionDto> findMovieOffersVersionForMovieId(Long movieId);
}
