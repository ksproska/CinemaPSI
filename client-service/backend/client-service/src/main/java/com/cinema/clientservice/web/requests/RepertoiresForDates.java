package com.cinema.clientservice.web.requests;

import java.time.LocalDate;
import java.util.List;

public record RepertoiresForDates(LocalDate date, List<RepertoireDetails> repertoires) {}
