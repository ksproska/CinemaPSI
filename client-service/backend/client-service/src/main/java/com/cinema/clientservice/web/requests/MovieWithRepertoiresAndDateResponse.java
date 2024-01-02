package com.cinema.clientservice.web.requests;

import java.time.LocalDate;
import java.util.List;

public record MovieWithRepertoiresAndDateResponse(
        LocalDate date,
        List<MovieWithRepertoires> movieWithRepertoires
) {
}
