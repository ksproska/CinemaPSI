import { Component } from '@angular/core';
import { Router} from "@angular/router";

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
    this.router.navigate([`/movies`]);
  }
}
