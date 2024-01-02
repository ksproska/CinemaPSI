package com.cinema.clientservice.web.services;

import com.cinema.clientservice.db.common.repositories.VersionOfferMovieMapRepository;
import com.cinema.clientservice.db.instance.models.Repertoire;
import com.cinema.clientservice.db.instance.repositories.RepertoireRepository;
import com.cinema.clientservice.web.dtos.GenreForMovie;
import com.cinema.clientservice.web.dtos.MovieDetailsDto;
import com.cinema.clientservice.web.dtos.MovieVersionWithLanguageDto;
import com.cinema.clientservice.web.requests.MovieWithRepertoires;
import com.cinema.clientservice.web.requests.MovieWithRepertoiresAndDateResponse;
import com.cinema.clientservice.web.requests.RepertoireDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RepertoireService {
    private final RepertoireRepository repertoireRepository;

    private final VersionOfferMovieMapRepository versionOfferMovieMapRepository;

    public RepertoireService(RepertoireRepository repertoireRepository, VersionOfferMovieMapRepository versionOfferMovieMapRepository) {
        this.repertoireRepository = repertoireRepository;
        this.versionOfferMovieMapRepository = versionOfferMovieMapRepository;
    }

    public List<MovieWithRepertoiresAndDateResponse> getMovieWithRepertoireDetails() {
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
                                    repertoire.getStarting().toLocalTime(),
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
}
