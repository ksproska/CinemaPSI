import {inject, Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {BASE_URL} from "../utility/globals";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Repertoire} from "../models/repertoire";

@Injectable({
  providedIn: 'root'
})
export class RepertoireService {

  private http = inject(HttpClient)
  getRepertoire(): Observable<Repertoire[]> {
    let url = BASE_URL + '/get-repertoire2'

    const params = new HttpParams()
      .set('hoursInterval', 300)
      .set('cinemaId', 1);

    return this.http.get<Repertoire[]>(url, { params });
  }
}
