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

        List<Long> repertoireIds = repertoireRepository.findRepertoireIdsByStartingAndCinemaId(cinemaId, endTime, startTime);
        List<RepertoireDTO> repertoire = repertoireRepository.findRepertoireDtoByStartingAndCinemaId(repertoireIds);
        List<Long> movieVersionIds = repertoire.stream().map(RepertoireDTO::getMovieVersionId).toList();
        List<MovieDto> movies = movieRepository.findMovieDtoByVersionID(movieVersionIds);

        Map<Long, MovieDto> movieMapForMovieVersionId = movies.stream()
                .collect(Collectors.toMap(MovieDto::versionId, Function.identity()));

        Map<Long, List<RepertoireDTO>> repertoiresByMovieId = repertoire.stream()
                .collect(Collectors.groupingBy(r -> movieMapForMovieVersionId.get(r.getMovieVersionId()).movieId()));

        List<RepertoireMovieResponseDto> responses = new ArrayList<>();

        for (Map.Entry<Long, List<RepertoireDTO>> entry : repertoiresByMovieId.entrySet()) {
            Long movieId = entry.getKey();
            List<RepertoireDTO> repertoiresForMovie = entry.getValue();

            RepertoireMovieResponseDto response = new RepertoireMovieResponseDto();
            MovieDto movie = movies.stream().filter(movieDto -> movieDto.movieId().equals(movieId)).findFirst().orElseThrow();

            response.setTitle(movie.title());
            response.setDescription(movie.description());
            response.setImage(movie.image());
            response.setShowings(repertoiresForMovie.toArray(new RepertoireDTO[0]));

            responses.add(response);
        }

        return responses;
    }
}
