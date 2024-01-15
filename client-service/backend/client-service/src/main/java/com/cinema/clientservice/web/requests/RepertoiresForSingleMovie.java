package com.cinema.clientservice.web.requests;

import com.cinema.clientservice.db.common.models.Movie;

import java.util.List;

public record RepertoiresForSingleMovie(Movie movie, List<RepertoiresForDates> repertoiresForDates) {}
