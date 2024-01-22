import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  getGenres(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseURL}/get-all-genres`)
  }
}
