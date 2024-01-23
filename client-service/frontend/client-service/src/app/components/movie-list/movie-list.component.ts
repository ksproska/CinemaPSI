import {Component, inject, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";
import {RepertoireService} from "../../services/repertoire.service";
import {RepertoireByDate} from "../../models/repertoireByDate";
import {GenreService} from "../../services/genre.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CinemaNamesMapRev} from "../../models/cinemaNamesMapRev";
import {CinemaNamesMap} from "../../models/cinemaNamesMap";


@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute) {
  }

  private repertoireService = inject(RepertoireService)
  private genreService = inject(GenreService)

  cinemas = ['Wrocław'];
  selectedCinema: string = 'Wrocław';

  genres = new FormControl('');
  genresList: string[] = [];
  selectedGenres: string[] = [];
  movies: RepertoireByDate[] = [];
  moviesInSelectedGenre: RepertoireByDate[] = this.movies;
  selectedDate: Date = new Date();

  ngOnInit() {
    this.getRepertoireByDate(this.selectedDate)

    this.genreService.getGenres().subscribe({
      next: value => {
        this.genresList = value
      }
    });

  //@ts-ignore
    this.selectedCinema = CinemaNamesMapRev.cinemaNamesMapRev[this.route.snapshot.paramMap.get('reservationId') ? this.route.snapshot.paramMap.get('reservationId') : "wroclaw"];
  }

  getRepertoireByDate(date: Date) {
    this.repertoireService.getRepertoireByDate(CinemaNamesMap.cinemaNamesMap[this.selectedCinema], date).subscribe({
      next: value => {
        this.movies = value
        if (this.selectedGenres.length == 0) {
          this.moviesInSelectedGenre = this.movies
        } else {
          this.moviesInSelectedGenre = this.movies.filter(movie => {
            return this.selectedGenres.some(selectedGenre => movie.genres.includes(selectedGenre));
          });
        }
      }
    });
  }

  onSelectCinema(cinema: string): void {
    this.selectedCinema = cinema;
  }

  datePassed(date: Date): boolean {
    let now = new Date()
    date = new Date(date)
    return now > date;
  }

  navigateToBuyView(screening: number) {
    this.router.navigate([`/movies/${screening}`])
  }

  onMovieSelected(movieId: number) {
    this.router.navigate([`/movies/${CinemaNamesMap.cinemaNamesMap[this.selectedCinema]}/${movieId}`])
  }

  isLast(movie: any, genre: string): boolean {
    return movie.genres.indexOf(genre) === movie.genres.length - 1;
  }

  updateSelectedGenresList(event: {
    isUserInput: any;
    source: { value: any; selected: any };
  }) {
    if (event.isUserInput) {
      if (event.source.selected === true) {

        this.selectedGenres.push(event.source.value)
        this.moviesInSelectedGenre = this.movies.filter(movie => {
          return this.selectedGenres.some(selectedGenre => movie.genres.includes(selectedGenre));
        });

      } else {
        this.selectedGenres = this.selectedGenres.filter((element) => {
          return element !== event.source.value;
        });
        if (this.selectedGenres.length == 0) {
          this.moviesInSelectedGenre = this.movies
        } else {
          this.moviesInSelectedGenre = this.movies.filter(movie => {
            return this.selectedGenres.some(selectedGenre => movie.genres.includes(selectedGenre));
          });
        }


      }
    }
  }

  onDateChange(newDate: Date) {
    this.selectedDate = newDate;
    this.getRepertoireByDate(this.selectedDate);
  }
}
