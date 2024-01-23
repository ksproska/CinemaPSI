package com.cinema.definelocal.web.requests;

import com.cinema.definelocal.db.common.models.Cinema;
import com.cinema.definelocal.db.instance.models.Hall;

import java.util.List;

public record DefineRepertoireInfoResponse(Cinema cinema, List<Hall> halls, MovieData movieData) {}
