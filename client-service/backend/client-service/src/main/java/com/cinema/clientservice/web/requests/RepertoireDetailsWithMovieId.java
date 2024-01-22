package com.cinema.clientservice.web.requests;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record RepertoireDetailsWithMovieId(
        Long repertoireId,
        LocalDateTime starting,
        Long movieVersionId,
        Long languageVersionId,
        String languageVersionName,
        Long movieId
) {
}
