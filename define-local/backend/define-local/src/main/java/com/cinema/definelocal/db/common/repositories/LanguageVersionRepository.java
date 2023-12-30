package com.cinema.definelocal.db.common.repositories;

import com.cinema.definelocal.db.common.models.Cinema;
import com.cinema.definelocal.web.dtos.LanguageVersionNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageVersionRepository extends JpaRepository<Cinema, Long> {
    @Query(value = "SELECT new com.cinema.definelocal.web.dtos.LanguageVersionNameDto(lv.id, lv.versionName) FROM LanguageVersion lv " +
            "LEFT JOIN VersionOfferMovieMap vomm ON lv.id = vomm.versionId WHERE vomm.movieId = :movieId")
    List<LanguageVersionNameDto> findAllLanguageVersionNamesForMovieId(Long movieId);
}
