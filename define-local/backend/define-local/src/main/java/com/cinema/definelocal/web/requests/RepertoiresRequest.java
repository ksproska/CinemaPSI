package com.cinema.definelocal.web.requests;

import java.util.List;

public record RepertoiresRequest(
        Long movieId,
        List<RepertoireCandidate> repertoireCandidates
) {
}
