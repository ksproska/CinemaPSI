package com.cinema.definelocal.web.requests;

import com.cinema.definelocal.db.common.models.Cinema;
import com.cinema.definelocal.web.dtos.MovieTitleDto;

import java.util.List;

public record MovieAndCinemaOptionsResponse(List<MovieTitleDto> movieTitles, List<Cinema> cinemas) {}
