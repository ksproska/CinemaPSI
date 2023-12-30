package com.cinema.definelocal.db.instance.repositories;

import com.cinema.definelocal.db.instance.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {}
