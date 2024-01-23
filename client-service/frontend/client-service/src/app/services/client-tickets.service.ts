import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ClientTickets} from "../models/client-tickets";

@Injectable({
  providedIn: 'root'
})
export class ClientTicketsService {
  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  getAllClientTickets(): Observable<ClientTickets> {
    return this.http.get<ClientTickets>(`${this.baseURL}/get-all-tickets`)
  }
}
