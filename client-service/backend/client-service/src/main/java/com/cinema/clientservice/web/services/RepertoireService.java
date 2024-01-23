package com.cinema.clientservice.web.services;

import com.cinema.clientservice.db.common.repositories.MovieRepository;
import com.cinema.clientservice.db.common.repositories.VersionOfferMovieMapRepository;
import com.cinema.clientservice.db.instance.models.Repertoire;
import com.cinema.clientservice.db.instance.repositories.RepertoireRepository;
import com.cinema.clientservice.web.dtos.GenreForMovie;
import com.cinema.clientservice.web.dtos.MovieDetailsDto;
import com.cinema.clientservice.web.dtos.MovieVersionWithLanguageDto;
import com.cinema.clientservice.web.requests.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RepertoireService {
    private final RepertoireRepository repertoireRepository;

    private final VersionOfferMovieMapRepository versionOfferMovieMapRepository;
    private final MovieRepository movieRepository;

    public RepertoireService(RepertoireRepository repertoireRepository, VersionOfferMovieMapRepository versionOfferMovieMapRepository, MovieRepository movieRepository) {
        this.repertoireRepository = repertoireRepository;
        this.versionOfferMovieMapRepository = versionOfferMovieMapRepository;
        this.movieRepository = movieRepository;
    }

    public RepertoiresForSingleMovie getFutureRepertoireForMovie(Long movieId) {
        var movie = this.movieRepository.findById(movieId).orElseThrow();
        var movieVersionIds = this.versionOfferMovieMapRepository.getMovieVersionsForMovieId(movieId);
        var repertoires = getRepertoireAfterNowForMovieVersions(movieVersionIds);
        var dates = getRepertoireDatesSorted(repertoires);
        var movieVersionWithLanguageList = versionOfferMovieMapRepository.getVersionWithLanguagesForMovieVersionIds(movieVersionIds);
        var genres = versionOfferMovieMapRepository.getGenresForMovieWithId(movieId);
        return new RepertoiresForSingleMovie(movie, dates.stream().map(
                date -> new RepertoiresForDates(
                        date,
                        repertoires
                                .stream()
                                .filter(r -> r.getStarting().toLocalDate().equals(date))
                                .map(repertoire -> {
                                            var languageVersion = getLanguageVersionForRepertoire(repertoire, movieVersionWithLanguageList);
                                            return new RepertoireDetails(
                                                    repertoire.getId(),
                                                    repertoire.getStarting(),
                                                    repertoire.getMovieVersionId(),
                                                    languageVersion.languageVersionId(),
                                                    languageVersion.languageVersionName());
                                        }
                                )
                                .toList()
                )
        ).toList(),
                genres);
    }

    public List<MovieWithRepertoiresAndDateResponse> getFutureRepertoiresWithMovieDetails() {
        var repertoires = getRepertoireAfterNow();
        List<Long> movieVersionIds = getMovieVersionIds(repertoires);
        var movieDetailsList = getVersionDetailsForMovieIds(movieVersionIds);
        var movieVersionWithLanguageList = versionOfferMovieMapRepository.getVersionWithLanguagesForMovieVersionIds(movieVersionIds);
        var dates = getRepertoireDatesSorted(repertoires);
        var genres = versionOfferMovieMapRepository.getGenresForMoviesWithIds(movieDetailsList.stream().map(MovieDetailsDto::movieId).distinct().toList());

        return dates
                .stream()
                .map(
                        date -> new MovieWithRepertoiresAndDateResponse(
                                date,
                                movieDetailsList.stream().map(
                                        movieDetails -> new MovieWithRepertoires(
                                                movieDetails,
                                                genres.stream().filter(genreForMovie -> genreForMovie.movieId().equals(movieDetails.movieId())).map(GenreForMovie::genreName).toList(),
                                                getRepertoireDetailsForMovieIdAndDate(movieDetails.movieId(), date, repertoires, movieVersionWithLanguageList)
                                        )
                                ).filter(movieWithRepertoires -> !movieWithRepertoires.repertoires().isEmpty()).toList()
                        )
                ).toList();
    }

    private static List<RepertoireDetails> getRepertoireDetailsForMovieIdAndDate(Long movieId, LocalDate date, List<Repertoire> repertoires, List<MovieVersionWithLanguageDto> movieVersionWithLanguageList) {
        return repertoires
                .stream()
                .filter(repertoire -> isRepertoireForMovieWithId(
                                movieVersionWithLanguageList,
                                movieId,
                                repertoire.getMovieVersionId()
                        )
                )
                .filter(repertoire -> repertoire.getStarting().toLocalDate().equals(date))
                .map(repertoire -> {
                            var languageVersion = getLanguageVersionForRepertoire(repertoire, movieVersionWithLanguageList);
                            return new RepertoireDetails(
                                    repertoire.getId(),
                                    repertoire.getStarting(),
                                    repertoire.getMovieVersionId(),
                                    languageVersion.languageVersionId(),
                                    languageVersion.languageVersionName());
                        }
                )
                .toList();
    }

    public List<Repertoire> getRepertoireAfterNow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return repertoireRepository.getRepertoireByStartingAfterOrderByStarting(localDateTime);
    }

    public List<Repertoire> getRepertoireAfterNowForMovieVersions(List<Long> movieVersionsIds) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return repertoireRepository.getRepertoireByStartingAfterAndMovieVersionIdIsInOrderByStarting(localDateTime, movieVersionsIds);
    }

    private static List<Long> getMovieVersionIds(List<Repertoire> repertoires) {
        return repertoires.stream()
                .map(Repertoire::getMovieVersionId)
                .distinct()
                .toList();
    }

    public List<MovieDetailsDto> getVersionDetailsForMovieIds(List<Long> movieVersionIds) {
        return versionOfferMovieMapRepository.getMovieDetailsForMovieVersionIds(movieVersionIds);
    }

    private static MovieVersionWithLanguageDto getLanguageVersionForRepertoire(Repertoire repertoire, List<MovieVersionWithLanguageDto> versionWithLanguage) {
        return versionWithLanguage
                .stream()
                .filter(m -> m.movieVersionId().equals(repertoire.getMovieVersionId()))
                .findFirst()
                .orElseThrow();
    }

    private static boolean isRepertoireForMovieWithId(List<MovieVersionWithLanguageDto> versionWithLanguage, Long movieId, Long movieVersionId) {
        return versionWithLanguage
                .stream()
                .filter(v -> v.movieId().equals(movieId))
                .map(MovieVersionWithLanguageDto::movieVersionId)
                .toList()
                .contains(movieVersionId);
    }

    private static List<LocalDate> getRepertoireDatesSorted(List<Repertoire> repertoires) {
        return repertoires.stream().map(Repertoire::getStarting).map(LocalDateTime::toLocalDate).distinct().sorted().toList();
    }

    private List<RepertoireDetailsWithMovieId> getRepertoireDetailsForRepertoires(List<Repertoire> repertoires) {
        var movieVersionIds = repertoires.stream().map(repertoire -> repertoire.getMovieVersionId()).toList();
        var movieVersionWithLanguageDto = versionOfferMovieMapRepository.getVersionWithLanguagesForMovieVersionIds(movieVersionIds);
        List<RepertoireDetailsWithMovieId> repertoireDetails = new ArrayList<>();
        for (Repertoire repertoire : repertoires) {
            var languageVersionForRepertoire = getLanguageVersionForRepertoire(repertoire, movieVersionWithLanguageDto);
            var repertoireDetail = new RepertoireDetailsWithMovieId(repertoire.getId(), repertoire.getStarting(),
                    repertoire.getMovieVersionId(), languageVersionForRepertoire.languageVersionId(),
                    languageVersionForRepertoire.languageVersionName(), languageVersionForRepertoire.movieId());
            repertoireDetails.add(repertoireDetail);
        }
        return repertoireDetails;
    }

    private static List<RepertoireDetails> getRepertoireDetailsForMovieIds(List<RepertoireDetailsWithMovieId> repertoireDetailsWithMovieId, Long movieId) {
        return repertoireDetailsWithMovieId.stream()
                .filter(repertoire -> repertoire.movieId().equals(movieId))
                .map(repertoire -> new RepertoireDetails(
                        repertoire.repertoireId(),
                        repertoire.starting(),
                        repertoire.movieVersionId(),
                        repertoire.languageVersionId(),
                        repertoire.languageVersionName())).toList();
    }

    private static List<Repertoire> sortRepertoires(List<Repertoire> repertoires) {
        repertoires.sort(Comparator.comparing(Repertoire::getStarting));
        return repertoires;
    }

    public List<MovieWithRepertoires> getRepertoireDetailsByDate(LocalDate afterDate) {
        LocalDateTime startingBefore = afterDate.atTime(LocalTime.MAX);
        LocalDateTime startingAfter = (afterDate.equals(LocalDate.now()) ? afterDate.atTime(LocalTime.now()) : afterDate.atTime(LocalTime.MIN));
        //movie version ids in this particular date
        List<Long> movieVersionIds = this.repertoireRepository.getMovieVersionIdByStartingAfterAndStartingBeforeOrderByStarting(startingAfter, startingBefore);
        //repertoires for the date
        List<Repertoire> repertoires = sortRepertoires(this.repertoireRepository.getRepertoireByStartingAfterAndStartingBefore(startingAfter, startingBefore));
        //repetoire details for the date, including movie id
        List<RepertoireDetailsWithMovieId> repertoireDetailsWithMovieId = getRepertoireDetailsForRepertoires(repertoires);
        //movie details for the movie version ids
        var movieDetailsList = getVersionDetailsForMovieIds(movieVersionIds);
        //genres for the movie ids
        var genres = this.versionOfferMovieMapRepository.getGenresForMoviesWithIds(movieDetailsList.stream().map(MovieDetailsDto::movieId).distinct().toList());

        return
                movieDetailsList.stream().map(
                        movieDetails -> new MovieWithRepertoires(
                                movieDetails,
                                genres.stream().filter(genreForMovie -> genreForMovie.movieId().equals(movieDetails.movieId())).map(GenreForMovie::genreName).toList(),
                                getRepertoireDetailsForMovieIds(repertoireDetailsWithMovieId, movieDetails.movieId())
                        )
                ).filter(movieWithRepertoires -> !movieWithRepertoires.repertoires().isEmpty()).toList();
    }
}
