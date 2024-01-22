import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HallSetupForRepertoire} from "../models/hallSetupforRepertoire";
import {Price} from "../models/price";

@Injectable({
  providedIn: 'root'
})
export class PriceService {

  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  getCurrentPrice(): Observable<Price> {
    return this.http.get<Price>(`${this.baseURL}/get-current-price`)
  }
}
