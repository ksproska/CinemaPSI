package com.cinema.definelocal.web.requests;

import java.time.LocalDateTime;

public record RepertoireCandidate(
        LocalDateTime starting,
        Long hallId,
        Long versionOfferMovieId
) {
}
