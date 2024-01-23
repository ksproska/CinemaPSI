import {Component, inject, OnInit} from '@angular/core';
import {SeanceFormComponent} from "../seance-form/seance-form.component";
import {DefineLocalService} from "../../services/define-local.service";
import {MovieCinemaDTO} from "../../dto/movie-cinema-dto";
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {Movie} from "../../models/movie";
import {MovieDetailDTO} from "../../dto/movie-detail-dto";

@Component({
  selector: 'app-define-repertoire',
  standalone: true,
  imports: [
    SeanceFormComponent,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './define-repertoire.component.html',
  styleUrl: './define-repertoire.component.css'
})
export class DefineRepertoireComponent implements OnInit {
  private defineLocalService = inject(DefineLocalService)
  private formBuilder = inject(FormBuilder)
  movieCinema?: MovieCinemaDTO
  movieDetails?: MovieDetailDTO

  cinemaMovieForm = this.formBuilder.group({
    cinema :[0, Validators.required],
    movie: [0, Validators.required]
  })

  ngOnInit(): void {
        this.defineLocalService.getMovieCinema().subscribe({
          next: value => this.movieCinema = value
        });
    }

  onSubmit() {
    let movieId = this.cinemaMovieForm.value.movie
    let cinemaId = this.cinemaMovieForm.value.cinema
    if (movieId && cinemaId)
      this.defineLocalService.getMovieDetails(movieId,cinemaId).subscribe({
        next: value => this.movieDetails = value
      })
  }
}
