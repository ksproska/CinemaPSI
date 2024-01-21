package com.cinema.repertoire.db.instance.repositories;

import com.cinema.repertoire.db.instance.models.Repertoire;
import com.cinema.repertoire.web.dtos.RepertoireDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {
    @Query(value = "SELECT r.id FROM Repertoire r " +
            "LEFT JOIN Hall h ON r.hallId = h.id " +
            "WHERE r.starting >= :start AND r.ending < :end " +
            "AND h.cinemaId = :cinemaId")
    List<Long> findRepertoireIdsByStartingAndCinemaId(Long cinemaId, LocalDateTime end, LocalDateTime start);

    @Cacheable(value = "repertoireDto")
    @Query(value = "SELECT new com.cinema.repertoire.web.dtos.RepertoireDTO(r.starting, r.ending, h.hallNumber, r.movieVersionId) FROM Repertoire r " +
            "LEFT JOIN Hall h ON r.hallId = h.id WHERE r.id in :repertoireIds")
    List<RepertoireDTO> findRepertoireDtoByStartingAndCinemaId(List<Long> repertoireIds);
}
