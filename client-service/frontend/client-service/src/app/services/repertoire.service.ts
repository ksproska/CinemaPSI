import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RepertoiresForSingleMovie} from "../models/repertoiresForSingleMovie";
import {HallSetupForRepertoire} from "../models/hallSetupforRepertoire";

@Injectable({
  providedIn: 'root'
})
export class RepertoireService {

  private baseURL = environment.baseURL
  constructor(private readonly http: HttpClient) {  }
  getHallSetupByRepertoireId(repertoireId: string): Observable<HallSetupForRepertoire> {
    return this.http.get<HallSetupForRepertoire>(`${this.baseURL}/hall-setup/${repertoireId}`)
  }
}
