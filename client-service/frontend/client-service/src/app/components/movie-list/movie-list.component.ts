import {Component} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent {

  cinemas = ['Wrocław'];
  selectedCinema: string = 'Wrocław';

  genres = new FormControl('');
  genresList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
  selectedGenres : string[] = [];

  movies = [
    {
      movieVersionDetails: {
        movieId: 1,
        movieTitle: 'The Shawshank Redemption',
        movieDescription: 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        movieLengthMinutes: 142,
        movieImageUrl: 'image-1.jpg',
        rating: 9.3
      },
      genres: ['Drama', 'Science fiction'],
      repertoires: [
        {
          repertoireId: 101,
          starting: new Date('2024-01-21T18:00:00Z'),
          movieVersionId: 1,
          languageVersionId: 1,
          languageVersionName: 'English'
        },
        {
          repertoireId: 101,
          starting: new Date('2024-01-21T18:00:00Z'),
          movieVersionId: 1,
          languageVersionId: 1,
          languageVersionName: 'English'
        }
      ]
    },
    {
      movieVersionDetails: {
        movieId: 1,
        movieTitle: 'The Shawshank Redemption',
        movieDescription: 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        movieLengthMinutes: 142,
        movieImageUrl: 'image-1.jpg',
        rating: 9.3
      },
      genres: ['Drama', 'Science fiction'],
      repertoires: [
        {
          repertoireId: 101,
          starting: new Date('2024-01-21T18:00:00Z'),
          movieVersionId: 1,
          languageVersionId: 1,
          languageVersionName: 'English'
        },
        {
          repertoireId: 101,
          starting: new Date('2024-01-21T18:00:00Z'),
          movieVersionId: 1,
          languageVersionId: 1,
          languageVersionName: 'English'
        }
      ]
    }
  ];

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
        console.log(this.selectedGenres)
      } else {
        this.selectedGenres = this.selectedGenres.filter((element) => {
          return element !== event.source.value;
        })
        console.log(event.source.value)
        console.log(this.selectedGenres)
      }
    }
  }
}
