package com.cinema.definelocal.web.requests;

import com.cinema.definelocal.db.common.models.Cinema;

public record DefineRepertoireInfoResponse(Cinema cinema, MovieData movieData) {}
