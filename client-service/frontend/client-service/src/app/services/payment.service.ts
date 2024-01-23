import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private baseURL = environment.baseURL;
  constructor(private http: HttpClient) {}

  pay(paymentData: { reservationId: string; paymentService: string }) {
    // Construct the URL with query parameters

    const params = new HttpParams()
      .set('reservationId', paymentData.reservationId)
      .set('paymentService', paymentData.paymentService);


    const paymentUrl = 'http://localhost:8080/pay';

    // Return the http request observable to be subscribed to in the component
    return this.http.get(`${this.baseURL}/pay`, {params: params, observe: 'response'});
  }
}
