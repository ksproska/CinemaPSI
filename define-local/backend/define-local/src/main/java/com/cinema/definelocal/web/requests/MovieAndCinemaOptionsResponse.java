package com.cinema.definelocal.web.requests;

import java.util.List;

public record MovieAndCinemaOptionsResponse(List<MovieTitle> movieTitles, List<Cinema> cinemas) {}
