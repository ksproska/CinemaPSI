package com.cinema.clientservice.web.requests;

import java.time.LocalTime;

public record RepertoireDetailsWithMovieId(
        Long repertoireId,
        LocalTime starting,
        Long movieVersionId,
        Long languageVersionId,
        String languageVersionName,
        Long movieId
) {
}
