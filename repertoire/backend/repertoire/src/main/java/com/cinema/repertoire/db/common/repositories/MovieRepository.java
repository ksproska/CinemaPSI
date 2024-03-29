package com.cinema.repertoire.db.common.repositories;

import com.cinema.repertoire.db.common.models.Movie;
import com.cinema.repertoire.web.dtos.MovieDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Cacheable(value = "movieDto")
    @Query(value = "SELECT new com.cinema.repertoire.web.dtos.MovieDto(m.id, m.title, m.description, m.image, map.id) " +
            "FROM Movie m LEFT JOIN VersionOfferMovieMap map ON m.id = map.movieId WHERE map.id in :versionId")
    List<MovieDto> findMovieDtoByVersionID(List<Long> versionId);
}
