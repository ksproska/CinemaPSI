import { Component } from '@angular/core';
import { Router} from "@angular/router";
import {CinemaNamesMapRev} from "../../models/cinemaNamesMapRev";
import {CinemaNamesMap} from "../../models/cinemaNamesMap";

@Component({
  selector: 'app-choose-cinema',
  templateUrl: './choose-cinema.component.html',
  styleUrls: ['./choose-cinema.component.css']
})
export class ChooseCinemaComponent {
  cinemas = ['Wrocław'];
  selectedCinema: string = 'Wrocław';

  constructor(private router: Router) {}

  onSelectCinema(cinema: string): void {
    this.selectedCinema = cinema;
  }

  onSelectClick() {
    localStorage.setItem("cinema", CinemaNamesMap.cinemaNamesMap[this.selectedCinema]);
    this.router.navigate([`/movies/${CinemaNamesMap.cinemaNamesMap[this.selectedCinema]}`]);
  }

}
