package com.cinema.clientservice.web.requests;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record RepertoireDetails(
        Long repertoireId,
        LocalDateTime starting,
        Long movieVersionId,
        Long languageVersionId,
        String languageVersionName
) {
}
