import {Component, inject} from '@angular/core';
import {FormControl} from "@angular/forms";
import {RepertoireService} from "../../services/repertoire.service";
import {RepertoireByDate} from "../../models/repertoireByDate";
import {GenreService} from "../../services/genre.service";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent {
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
  }

  getRepertoireByDate(date: Date) {
    this.repertoireService.getRepertoireByDate(date).subscribe({
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
    // if (!this.datePassed(new Date(screening.date))) {
    //   this.router.navigate([`/movies/${screening.id}/${screening.hallId}/book`]);
    // }
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
        console.log(event.source.value)
        this.selectedGenres.push(event.source.value)
        this.moviesInSelectedGenre = this.movies.filter(movie => {
          return this.selectedGenres.some(selectedGenre => movie.genres.includes(selectedGenre));
        });
        console.log(this.selectedGenres)
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
        console.log(event.source.value)
        console.log(this.selectedGenres)
      }
    }
  }

  onDateChange(newDate: Date) {
    this.selectedDate = newDate;
    this.getRepertoireByDate(this.selectedDate);
  }
}
