package com.cinema.clientservice.web.requests;

import com.cinema.clientservice.web.dtos.MovieDetailsDto;

import java.util.List;

public record MovieWithRepertoires(
        MovieDetailsDto movieVersionDetails,
        List<String> genres,
        List<RepertoireDetails> repertoires
) {
}
