package com.cinema.repertoire.web.services;

import com.cinema.repertoire.db.common.repositories.MovieRepository;
import com.cinema.repertoire.db.instance.repositories.RepertoireRepository;
import com.cinema.repertoire.web.dtos.MovieDto;
import com.cinema.repertoire.web.dtos.RepertoireDTO;
import com.cinema.repertoire.web.dtos.RepertoireMovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RepertoireService {
    @Autowired
    RepertoireRepository repertoireRepository;
    @Autowired
    MovieRepository movieRepository;
    public List<RepertoireMovieResponseDto> createRepertoireResponses(Long cinemaId, LocalDateTime endTime) {
        List<RepertoireDTO> repertoire = repertoireRepository.findRepertoireDtoByStartingAndCinemaId(cinemaId, endTime);
        List<MovieDto> movies = movieRepository.findMovieDtoByVersionID(repertoire.stream().map(RepertoireDTO::getMovieVersionId).collect(Collectors.toList()));
        Map<Long, MovieDto> map = movies.stream()
                .collect(Collectors.toMap(MovieDto::getVersionId, Function.identity()));
        List<RepertoireMovieResponseDto> responses = new ArrayList<>();
        for (RepertoireDTO rep : repertoire) {
            RepertoireMovieResponseDto response = new RepertoireMovieResponseDto();
            response.setEnding(rep.getEnding());
            response.setStarting(rep.getStarting());
            response.setHallNumber(rep.getHallNumber());
            MovieDto movie = map.get(rep.getMovieVersionId());
            response.setTitle(movie.getTitle());
            response.setDescription(movie.getDescription());
            response.setImage(movie.getImage());
            responses.add(response);
        }
        return responses;
    }

}
