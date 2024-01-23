import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "../../assets/globals";
import {MovieCinemaDTO} from "../dto/movie-cinema-dto";
import {Movie} from "../models/movie";
import {MovieDetailDTO} from "../dto/movie-detail-dto";

@Injectable({
  providedIn: 'root'
})
export class DefineLocalService {

  private http = inject(HttpClient)
  getMovieCinema(): Observable<MovieCinemaDTO> {
    let url = BASE_URL + '/get-cinemas-and-movies'
    return this.http.get<MovieCinemaDTO>(url);
  }

  getMovieDetails(movieId: number, cinemaId: number): Observable<MovieDetailDTO> {
    let url = BASE_URL + '/get-define-repertoire-info'

    const params: HttpParams = new HttpParams()
      .set('cinemaId', 1)
      .set('movieId', movieId)

    return this.http.get<MovieDetailDTO>(url, { params });
  }
}
