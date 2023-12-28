package com.cinema.repertoire.web.request;

import java.util.List;

public record Movie(String title, String description, String imageUrl, List<Show> shows) {}
