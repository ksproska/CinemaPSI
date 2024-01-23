import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {BASE_URL} from "../../assets/globals";
import {MovieCinemaDTO} from "../dto/movie-cinema-dto";
import {Movie} from "../models/movie";
import {MovieDetailDTO} from "../dto/movie-detail-dto";
import {AddRepertoireRequest} from "../dto/add-repertoire-request";

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

  addRepertoire(payload: AddRepertoireRequest):Observable<string>{
    let url = BASE_URL + '/add-repertoire-for-movie';
    return this.http.post<string>(url, payload);
  }
}
