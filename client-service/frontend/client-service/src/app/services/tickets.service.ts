import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HallSetupForRepertoire} from "../models/hallSetupforRepertoire";
import {Reservation} from "../models/reservation";
import {ReservationDetails} from "../models/reservationDetails";

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  reserveTickets(reservation: Reservation, city: string): Observable<number> {
    return this.http.post<number>(`${this.baseURL}/reserve-tickets/${city}`, reservation);
  }


  getReservationDetails(reservationId: string, city: string): Observable<ReservationDetails> {
    return this.http.get<ReservationDetails>(`${this.baseURL}/get-reservation-details/${city}/${reservationId}`);
  }
}
