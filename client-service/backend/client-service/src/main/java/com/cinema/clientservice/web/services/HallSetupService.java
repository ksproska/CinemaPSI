package com.cinema.clientservice.web.services;

import com.cinema.clientservice.db.common.repositories.VersionOfferMovieMapRepository;
import com.cinema.clientservice.db.instance.repositories.HallRepository;
import com.cinema.clientservice.db.instance.repositories.RepertoireRepository;
import com.cinema.clientservice.db.instance.repositories.SeatsRepository;
import com.cinema.clientservice.web.dtos.SeatInfo;
import com.cinema.clientservice.web.requests.HallSetupForRepertoire;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HallSetupService {
    private final RepertoireRepository repertoireRepository;
    private final HallRepository hallRepository;
    private final SeatsRepository seatsRepository;
    private final VersionOfferMovieMapRepository versionOfferMovieMapRepository;

    public HallSetupService(RepertoireRepository repertoireRepository, HallRepository hallRepository, SeatsRepository seatsRepository, VersionOfferMovieMapRepository versionOfferMovieMapRepository) {
        this.repertoireRepository = repertoireRepository;
        this.hallRepository = hallRepository;
        this.seatsRepository = seatsRepository;
        this.versionOfferMovieMapRepository = versionOfferMovieMapRepository;
    }

    public HallSetupForRepertoire getHallSetup(Long repertoireId) {
        var repertoire = this.repertoireRepository.findById(repertoireId).orElseThrow();
        var hall = this.hallRepository.findById(repertoire.getHallId()).orElseThrow();
        var seats = this.seatsRepository.findAllSeatsForRepertoireId(repertoireId);
        var seatPlacement = new ArrayList<List<SeatInfo>>(){};
        for (int i = 0; i < hall.getRowsNum(); i++) {
            var j = i + 1;
            seatPlacement.add(
                    seats.stream()
                            .filter(s -> s.row() == j)
                            .map(s -> new SeatInfo(s.seatId(), s.ticket() != null))
                            .toList()
            );
        }
        var movieInfo = this.versionOfferMovieMapRepository.getMovieWithLanguageName(repertoire.getMovieVersionId()).stream().findFirst().orElseThrow();
        return new HallSetupForRepertoire(
                movieInfo.movieTitle(),
                movieInfo.languageVersionName(),
                repertoire.getId(),
                repertoire.getHallId(),
                repertoire.getStarting().toLocalDate(),
                repertoire.getStarting().toLocalTime(),
                hall.getRowsNum(),
                hall.getSeatsInRow(),
                seatPlacement
        );
    }
}
