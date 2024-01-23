import {TicketReservationDetailsDto} from "./ticketReservationDetailsDto";

export interface ReservationDetails {
  movieTitle: string,
  languageVersionName: string,
  date: Date,
  time: Date,
  tickets: TicketReservationDetailsDto[];
  total: number;
}
