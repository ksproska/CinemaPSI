package com.cinema.clientservice.db.common.repositories;

import com.cinema.clientservice.db.common.models.VersionOfferMovieMap;
import com.cinema.clientservice.web.dtos.GenreForMovie;
import com.cinema.clientservice.web.dtos.MovieDetailsDto;
import com.cinema.clientservice.web.dtos.MovieVersionWithLanguageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionOfferMovieMapRepository extends JpaRepository<VersionOfferMovieMap, Long> {
    @Query(value = "SELECT DISTINCT new com.cinema.clientservice.web.dtos.MovieDetailsDto(m.id, m.title, m.description, m.duration, m.image) FROM VersionOfferMovieMap vomm LEFT JOIN " +
            "Movie m ON vomm.movieId = m.id LEFT JOIN LanguageVersion lv ON vomm.versionId = lv.id WHERE vomm.id in :movieVersionIds GROUP BY m.id, m.title, m.description, m.duration, m.image")
    List<MovieDetailsDto> getMovieDetailsForMovieVersionIds(List<Long> movieVersionIds);

    @Query(value = "SELECT DISTINCT new com.cinema.clientservice.web.dtos.MovieVersionWithLanguageDto(vomm.id, vomm.movieId, lv.id, lv.versionName) FROM VersionOfferMovieMap vomm LEFT JOIN " +
            "LanguageVersion lv ON vomm.versionId = lv.id WHERE vomm.id in :movieVersionIds")
    List<MovieVersionWithLanguageDto> getVersionWithLanguagesForMovieVersionIds(List<Long> movieVersionIds);

    @Query(value = "SELECT DISTINCT new com.cinema.clientservice.web.dtos.GenreForMovie(mgm.id.movieId, g.id, g.name) FROM MoviesGenresMap mgm " +
            "LEFT JOIN Genre g ON mgm.id.genreId = g.id WHERE mgm.id.movieId in :movieIds")
    List<GenreForMovie> getGenresForMoviesWithIds(List<Long> movieIds);
}
