package com.cinema.clientservice.db.common.repositories;

import com.cinema.clientservice.db.common.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> getPriceByDateSinceBeforeAndDateUntilAfter(LocalDateTime dateTime1, LocalDateTime dateTime2);
}
