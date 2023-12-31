package com.cinema.definelocal.web.services;

import com.cinema.definelocal.db.common.repositories.MovieRepository;
import com.cinema.definelocal.db.instance.models.Repertoire;
import com.cinema.definelocal.db.instance.repositories.RepertoireRepository;
import com.cinema.definelocal.web.requests.RepertoireCandidate;
import com.cinema.definelocal.web.requests.RepertoiresRequest;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RepertoireService {
    private final MovieRepository movieRepository;
    private final RepertoireRepository repertoireRepository;

    public RepertoireService(MovieRepository movieRepository, RepertoireRepository repertoireRepository) {
        this.movieRepository = movieRepository;
        this.repertoireRepository = repertoireRepository;
    }

    Comparator<Repertoire> comparatorByStartingTime = Comparator.comparing(Repertoire::getStarting);

    public Optional<String> addRepertoires(RepertoiresRequest repertoiresRequest) {
        var allStartingTimes = repertoiresRequest.repertoireCandidates().stream().map(RepertoireCandidate::starting).distinct().sorted().toList();
        var allHallIds = repertoiresRequest.repertoireCandidates().stream().map(RepertoireCandidate::hallId).distinct().sorted().toList();
        var repertoiresForDates =
                repertoireRepository.findAllByEndingGreaterThanEqualAndStartingLessThanEqualAndHallIdIsInOrderByStarting(
                        allStartingTimes.getFirst(),
                        allStartingTimes.getLast(),
                        allHallIds
                );
        var hallIdToRepertoires = getMapHallIdToRepertoires(allHallIds, repertoiresForDates);
        var hallIdToBreaksBetweenShows = getMapHallIdToBreaksBetweenShows(allHallIds, hallIdToRepertoires);
        var repertoiresToSave = new ArrayList<Repertoire>() {};
        for (var repertoireCandidate : repertoiresRequest.repertoireCandidates()) {
            var breaksBetweenShowsForHallId = hallIdToBreaksBetweenShows.get(repertoireCandidate.hallId());
            var repertoireCandidateStartingTime = repertoireCandidate.starting();
            var repertoireCandidateEndingTime = repertoireCandidateStartingTime.plusMinutes(
                    movieRepository.findById(repertoiresRequest.movieId()).orElseThrow().getDuration()
            );

            var canRepertoireBeAddedErrorMessage =
                    canRepertoireBeAdded(
                            repertoireCandidate,
                            breaksBetweenShowsForHallId,
                            repertoireCandidateStartingTime,
                            repertoireCandidateEndingTime,
                            hallIdToRepertoires
                    );
            if (canRepertoireBeAddedErrorMessage.isPresent()) {
                return canRepertoireBeAddedErrorMessage;
            }
            repertoiresToSave.add(new Repertoire(
                    repertoireCandidateStartingTime,
                    repertoireCandidateEndingTime,
                    repertoireCandidate.hallId(),
                    repertoireCandidate.versionOfferMovieId()
            ));
        }
        // TODO fix so that endpoint saves required repertoires
//        repertoireRepository.saveAll(repertoiresToSave);
        return Optional.empty();
    }

    private static Optional<String> canRepertoireBeAdded(RepertoireCandidate repertoireCandidate, List<Pair<LocalDateTime, LocalDateTime>> breaksBetweenShowsForHallId, LocalDateTime repertoireCandidateStartingTime, LocalDateTime repertoireCandidateEndingTime, HashMap<Long, List<Repertoire>> hallIdToRepertoires) {
        if (breaksBetweenShowsForHallId.isEmpty()) return Optional.empty();
        var breakBetweenShowBigEnough = breaksBetweenShowsForHallId
                .stream()
                .filter(p ->
                        p.a.isBefore(repertoireCandidateStartingTime)
                                && p.b.isAfter(repertoireCandidateEndingTime)
                )
                .findFirst();
        if (breakBetweenShowBigEnough.isPresent()) return Optional.empty();

        var firstShowStartingTime = hallIdToRepertoires
                .get(repertoireCandidate.hallId())
                .getFirst()
                .getStarting();
        var lastShowEndingTime = hallIdToRepertoires
                .get(repertoireCandidate.hallId())
                .getLast()
                .getEnding();

        var isAvailableBefore = firstShowStartingTime.isAfter(repertoireCandidateEndingTime);
        var isAvailableAfter = lastShowEndingTime.isBefore(repertoireCandidateStartingTime);

        if (!(isAvailableBefore || isAvailableAfter)) {
            return Optional.of(String.format(
                    "For repertoire (%s - %s) slot is not available. Available slots: (.. - %s), %s, (%s - ..)",
                    repertoireCandidateStartingTime,
                    repertoireCandidateEndingTime,
                    firstShowStartingTime,
                    String.join(",", breaksBetweenShowsForHallId.stream().map(p -> String.format("(%s, %s)", p.a, p.b)).toList()),
                    lastShowEndingTime
            ));
        }
        return Optional.empty();
    }

    private static HashMap<Long, List<Pair<LocalDateTime, LocalDateTime>>> getMapHallIdToBreaksBetweenShows(List<Long> allHallIds, HashMap<Long, List<Repertoire>> hallIdToRepertoires) {
        var hallIdToBreaks = new HashMap<Long, List<Pair<LocalDateTime, LocalDateTime>>>();
        for (Long hallId : allHallIds) {
            var repertoiresForHallId = hallIdToRepertoires.get(hallId);
            var breaks = new ArrayList<Pair<LocalDateTime, LocalDateTime>>() {
            };
            for (int i = 0; i < repertoiresForHallId.size() - 1; i++) {
                breaks.add(new Pair<>(repertoiresForHallId.get(i).getEnding(), repertoiresForHallId.get(i + 1).getStarting()));
            }
            hallIdToBreaks.put(hallId, breaks);
        }
        return hallIdToBreaks;
    }

    private HashMap<Long, List<Repertoire>> getMapHallIdToRepertoires(List<Long> allHallIds, List<Repertoire> repertoiresForDates) {
        var hallIdToRepertoires = new HashMap<Long, List<Repertoire>>();
        allHallIds.forEach(
                hallId -> hallIdToRepertoires.put(
                        hallId,
                        repertoiresForDates.stream().filter(r -> r.getHallId().equals(hallId)).sorted(comparatorByStartingTime).toList()
                )
        );
        return hallIdToRepertoires;
    }
}
