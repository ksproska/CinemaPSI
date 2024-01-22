import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MoviesService} from "../../services/movies.service";
import {Movie} from "../../models/movie";
import {RepertoiresForDates} from "../../models/repertoiresForDates";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  movieId: number | undefined;
  private sub: any;
  data: any;
  movie: Movie | undefined;
  genres: string[] | undefined;
  repertoiresForDates: RepertoiresForDates[] | undefined;
  showMax: number = 1;
  selectedCinema: string = 'Wrocław';
  cinemas = ['Wrocław', 'Warszawa', 'Kraków'];
  constructor(private route: ActivatedRoute) {}
  ngOnInit(){
    this.route.data.subscribe(
      ({data}) => {
        this.data = data
      });
    this.movie = this.data['movie'];
    this.genres = this.data['genres'];
    this.repertoiresForDates = this.data['repertoiresForDates']
    console.log(this.repertoiresForDates);
  }

  datePassed(date: Date): boolean {
    console.log(date);
    let now = new Date()
    date = new Date(date)
    return now > date;
  }

  navigateToBuyView(screening: number) {
    // if (!this.datePassed(new Date(screening.date))) {
    //   this.router.navigate([`/movies/${screening.id}/${screening.hallId}/book`]);
    // }
  }

  showMoreDates(){
    this.showMax += 3;
  }

  onSelectCinema(cinema: string): void {
    this.selectedCinema = cinema;
  }
}
