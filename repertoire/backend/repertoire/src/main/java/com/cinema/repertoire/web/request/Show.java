package com.cinema.repertoire.web.request;

import java.time.LocalTime;

public record Show(int roomNumber, LocalTime startTime, LocalTime end) {}
