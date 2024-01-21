package com.cinema.repertoire.web.services;

import com.cinema.repertoire.db.common.repositories.MovieRepository;
import com.cinema.repertoire.db.instance.repositories.RepertoireRepository;
import com.cinema.repertoire.web.dtos.MovieDto;
import com.cinema.repertoire.web.dtos.RepertoireDTO;
import com.cinema.repertoire.web.dtos.RepertoireMovieResponseDto;
import com.cinema.repertoire.web.dtos.RepertoireRequestDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RepertoireService {
    private final RepertoireRepository repertoireRepository;
    private final MovieRepository movieRepository;

    public RepertoireService(RepertoireRepository repertoireRepository, MovieRepository movieRepository) {
        this.repertoireRepository = repertoireRepository;
        this.movieRepository = movieRepository;
    }

    public List<RepertoireMovieResponseDto> createRepertoireResponsesForRequest(RepertoireRequestDto request) {
        LocalDateTime endTime = LocalDateTime.now().plusHours(request.hoursInterval());
        Long cinemaId = request.cinemaId();
        LocalDateTime startTime = LocalDateTime.now().minusHours(5);

        List<RepertoireDTO> repertoire = repertoireRepository.findRepertoireDtoByStartingAndCinemaId(cinemaId, endTime, startTime);
        List<MovieDto> movies = movieRepository.findMovieDtoByVersionID(repertoire.stream().map(RepertoireDTO::getMovieVersionId).toList());

        Map<Long, MovieDto> movieMap = movies.stream()
                .collect(Collectors.toMap(MovieDto::versionId, Function.identity()));

        Map<Long, List<RepertoireDTO>> repertoiresByMovie = repertoire.stream()
                .collect(Collectors.groupingBy(RepertoireDTO::getMovieVersionId));

        List<RepertoireMovieResponseDto> responses = new ArrayList<>();

        for (Map.Entry<Long, List<RepertoireDTO>> entry : repertoiresByMovie.entrySet()) {
            Long movieVersionId = entry.getKey();
            List<RepertoireDTO> repertoiresForMovie = entry.getValue();

            RepertoireMovieResponseDto response = new RepertoireMovieResponseDto();
            MovieDto movie = movieMap.get(movieVersionId);

            response.setTitle(movie.title());
            response.setDescription(movie.description());
            response.setImage(movie.image());
            response.setShowings(repertoiresForMovie.toArray(new RepertoireDTO[0]));

            responses.add(response);
        }

        return responses;
    }
}
