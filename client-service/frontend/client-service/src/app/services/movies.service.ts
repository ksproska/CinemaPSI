import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {RepertoiresForSingleMovie} from "../models/repertoiresForSingleMovie";

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  getMovieById(city: string, id: string): Observable<RepertoiresForSingleMovie> {
    return this.http.get<RepertoiresForSingleMovie>(`${this.baseURL}/get-future-repertoire/${city}/${id}`)
  }
}
