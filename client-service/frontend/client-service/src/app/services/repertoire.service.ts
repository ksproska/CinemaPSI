import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {HallSetupForRepertoire} from "../models/hallSetupforRepertoire";
import {RepertoireByDate} from "../models/repertoireByDate";
import {formatDate} from "@angular/common";

@Injectable({
  providedIn: 'root'
})
export class RepertoireService {

  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  getHallSetupByRepertoireId(repertoireId: string): Observable<HallSetupForRepertoire> {
    return this.http.get<HallSetupForRepertoire>(`${this.baseURL}/hall-setup/${repertoireId}`)
  }

  getRepertoireByDate(date: Date): Observable<RepertoireByDate[]> {
    console.log(date.toDateString())
    const params = new HttpParams()
      .set('afterDate', formatDate(date, "yyyy-MM-dd", 'en-GB'))
    return this.http.get<RepertoireByDate[]>(`${this.baseURL}/get-future-repertoire-by-date`, {params})
  }
}
