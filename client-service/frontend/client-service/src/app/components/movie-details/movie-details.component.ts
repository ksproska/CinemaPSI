import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MoviesService} from "../../services/movies.service";
import {Movie} from "../../models/movie";
import {RepertoiresForDates} from "../../models/repertoiresForDates";
import {CinemaNamesMapRev} from "../../models/cinemaNamesMapRev";
import {CinemaNamesMap} from "../../models/cinemaNamesMap";

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
  cinemas = ['Wrocław'];

  constructor(private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.data.subscribe(
      ({data}) => {
        this.data = data
        this.movie = this.data['movie'];
        this.genres = this.data['genres'];
        this.repertoiresForDates = this.data['repertoiresForDates']


      });

    // @ts-ignore
    this.selectedCinema = CinemaNamesMapRev.cinemaNamesMapRev[(this.route.snapshot.paramMap.get('city') ? this.route.snapshot.paramMap.get('city') : "wroclaw")];

  }

  datePassed(date: Date): boolean {
    let now = new Date()
    date = new Date(date)
    return now > date;
  }

  navigateToBuyView(repertoireId: number) {
    this.router.navigate([`/tickets/${CinemaNamesMap.cinemaNamesMap[this.selectedCinema]}/${repertoireId}`]);
  }

  showMoreDates() {
    this.showMax += 3;
  }

  onSelectCinema(cinema: string): void {
    this.selectedCinema = cinema;
  }

  protected readonly CinemaNamesMap = CinemaNamesMap;
}
