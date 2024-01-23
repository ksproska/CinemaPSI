package com.cinema.clientservice.web.services;

import com.cinema.clientservice.db.common.repositories.PriceRepository;
import com.cinema.clientservice.db.common.repositories.VersionOfferMovieMapRepository;
import com.cinema.clientservice.db.instance.models.Repertoire;
import com.cinema.clientservice.db.instance.models.Ticket;
import com.cinema.clientservice.db.instance.models.TicketReservation;
import com.cinema.clientservice.db.instance.repositories.RepertoireRepository;
import com.cinema.clientservice.db.instance.repositories.TicketRepository;
import com.cinema.clientservice.db.instance.repositories.TicketReservationRepository;
import com.cinema.clientservice.web.dtos.MovieWithLanguageVersionNameDto;
import com.cinema.clientservice.web.exceptions.SeatTakenException;
import com.cinema.clientservice.web.requests.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.apache.commons.math3.util.Precision.round;

@Service
public class TicketService {
    private final TicketReservationRepository ticketReservationRepository;
    private final PriceRepository priceRepository;
    private final RepertoireRepository repertoireRepository;
    private final TicketRepository ticketRepository;
    private final VersionOfferMovieMapRepository versionOfferMovieMapRepository;

    public TicketService(TicketReservationRepository ticketReservationRepository, PriceRepository priceRepository, RepertoireRepository repertoireRepository, TicketRepository ticketRepository, VersionOfferMovieMapRepository versionOfferMovieMapRepository) {
        this.ticketReservationRepository = ticketReservationRepository;
        this.priceRepository = priceRepository;
        this.repertoireRepository = repertoireRepository;
        this.ticketRepository = ticketRepository;
        this.versionOfferMovieMapRepository = versionOfferMovieMapRepository;
    }

    public Long addMultipleTicketReservations(Reservation reservation) throws SeatTakenException {
        checkIfSeatsAlreadyTaken(reservation);
        checkIfSeatIdsInRepertoireHall(reservation);
        if (reservation.numberOfStudentTickets() > reservation.seatIds().size()) throw new IllegalStateException();
        var leftStudentTickets = reservation.numberOfStudentTickets();
        var allReservations = new ArrayList<TicketReservation>();
        var reservationId = ticketReservationRepository.getAllReservationIds().stream().sorted().mapToLong(l -> l).max().orElse(0) + 1;
        for (var seatId : reservation.seatIds()) {
            var newTicketReservation = getNewTicketReservation(reservationId, reservation.repertoireId(), seatId, leftStudentTickets > 0);
            allReservations.add(newTicketReservation);
            leftStudentTickets -= 1;
        }
        ticketReservationRepository.saveAll(allReservations);
        return reservationId;
    }

    private void checkIfSeatIdsInRepertoireHall(Reservation reservation) {
        var allSeatIdsForRepertoireHall = repertoireRepository.getSeatIdsForRepertoireId(reservation.repertoireId());
        for (var seatId : reservation.seatIds()) {
            if (!allSeatIdsForRepertoireHall.contains(seatId))
                throw new IllegalStateException("Seat " + seatId + " is not assigned to repertoire " + reservation.repertoireId());
        }
    }

    private void checkIfSeatsAlreadyTaken(Reservation reservation) throws SeatTakenException {
        var boughtTickets = ticketRepository.findAllSeatIdsForRepertoireId(reservation.repertoireId());
        for (var seatId : reservation.seatIds()) {
            if (boughtTickets.contains(seatId)) throw new SeatTakenException("Seat " + seatId + " is already bought.");
        }
        var reservedTickets = ticketReservationRepository.findAllSeatIdsForRepertoireId(reservation.repertoireId());
        for (var seatId : reservation.seatIds()) {
            if (reservedTickets.contains(seatId))
                throw new SeatTakenException("Seat " + seatId + " is already reserved.");
        }
    }

    public double getTotal(Long reservationId) {
        var prices = ticketReservationRepository.getAllPricesForReservationId(reservationId);
        return prices.stream().mapToDouble(p -> p).sum();
    }

    private TicketReservation getNewTicketReservation(Long reservationId, Long repertoireId, Long seatId, boolean isStudent) {
        var timeNow = LocalDateTime.now();
        var priceEntity = priceRepository.getPriceByDateSinceBeforeAndDateUntilAfter(timeNow, timeNow).orElseThrow();
        var repertoireStartingTime = repertoireRepository.findById(repertoireId).map(Repertoire::getStarting).orElseThrow();
        var isPromotion = timeNow.plusDays(priceEntity.getPromotionMinDays()).isBefore(repertoireStartingTime);
        var studentPromotion = (isStudent) ? 1 - (priceEntity.getReductionPct() * 0.01) : 1;
        var timePromotion = (isPromotion) ? 1 - (priceEntity.getPromotionPct() * 0.01) : 1;
        var price = round((priceEntity.getBasePrice() * studentPromotion) * timePromotion, 2);
        return new TicketReservation(
                isStudent,
                price,
                repertoireId,
                seatId,
                priceEntity.getId(),
                reservationId,
                timeNow
        );
    }

    public void moveTicketReservationsToTickets(Long paymentId) {
        var ticketReservations = ticketReservationRepository.findAllByPaymentId(paymentId);
        if (ticketReservations.isEmpty())
            throw new IllegalStateException("no tickets to move from reservation to final");
        var ticketsToAdd = ticketReservations.stream().map(
                ticketReservation -> new Ticket(
                        ticketReservation.isStudent(),
                        ticketReservation.getPrice(),
                        ticketReservation.getRepertoireId(),
                        ticketReservation.getSeatId(),
                        ticketReservation.getPriceId()
                )
        ).toList();
        ticketRepository.saveAll(ticketsToAdd);
        ticketReservationRepository.deleteAll(ticketReservations);
    }

    public void savePaymentIdForReservation(Long reservationId, Long paymentId, String paymentService) {
        var ticketReservations = ticketReservationRepository.findAllByReservationId(reservationId);
        for (var reservation : ticketReservations) {
            reservation.setPaymentId(paymentId);
            reservation.setPaymentServiceName(paymentService);
        }
        ticketReservationRepository.saveAll(ticketReservations);
    }

    public ReservationDetails getReservationDetails(Long reservationId) {
        List<TicketReservationDetailsDto> ticketReservations = ticketReservationRepository.findAllTicketReservationDetails(reservationId);
        Repertoire repertoire = repertoireRepository.findRepertoireByReservationId(reservationId).orElseThrow();
        MovieWithLanguageVersionNameDto movieDetails = versionOfferMovieMapRepository.getMovieWithLanguageName(repertoire.getMovieVersionId()).getFirst();
        return new ReservationDetails(
                movieDetails.movieTitle(),
                movieDetails.languageVersionName(),
                repertoire.getStarting().toLocalDate(),
                repertoire.getStarting().toLocalTime(),
                ticketReservations.stream().map(ticketReservation -> new TicketReservationDetailsDto(
                                ticketReservation.seatRow(),
                                ticketReservation.seatNumber(),
                                ticketReservation.isStudent(),
                                round(ticketReservation.price(), 2)
                        )
                ).toList(),
                round(ticketReservations.stream().map(TicketReservationDetailsDto::price).mapToDouble(p -> round(p, 2)).sum(), 2)
        );
    }

    public AllBoughtTicketsDetails getAllBoughtTicketDetails() {
        var allTicketsDetails = ticketRepository.findAllTicketDetails();
        return new AllBoughtTicketsDetails(
                allTicketsDetails.stream().map(
                        ticketDetails -> new SingleTicketDetails(
                                versionOfferMovieMapRepository.getMovieTitleFovMovieVersionId(ticketDetails.movieVersionId())
                                        .orElse("ERROR: unable to retrieve movie title for movie version id " + ticketDetails.movieVersionId()),
                                ticketDetails.isStudent(),
                                round(ticketDetails.price(), 2),
                                ticketDetails.repertoireStarting().toLocalDate(),
                                ticketDetails.repertoireStarting().toLocalTime(),
                                ticketDetails.seatNumber(),
                                ticketDetails.seatRow(),
                                ticketDetails.ticketId(),
                                ticketDetails.isValidated()
                        )
                ).sorted(Comparator.comparing(SingleTicketDetails::ticketId)).toList().reversed()
        );
    }
}
