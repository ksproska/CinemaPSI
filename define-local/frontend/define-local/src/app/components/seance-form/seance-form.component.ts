import {Component, inject, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {MovieDetailDTO} from "../../dto/movie-detail-dto";
import {DatePipe, NgIf} from "@angular/common";
import {MovieOffer} from "../../models/movie-offer";
import {FormArray, FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {setAnalyticsConfig} from "@angular/cli/src/analytics/analytics";

@Component({
  selector: 'app-seance-form',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    DatePipe
  ],
  templateUrl: './seance-form.component.html',
  styleUrl: './seance-form.component.css'
})
export class SeanceFormComponent implements OnChanges {

  private formBuilder = inject(FormBuilder)
  @Input() movieDetails?: MovieDetailDTO
  movieOffer?: MovieOffer

  addRepertoiresForm = this.formBuilder.group({
    seances: this.formBuilder.array([], Validators.required)
  });

  ngOnChanges(changes: SimpleChanges): void {
    if(this.movieDetails?.movieData.movieOffers)
      this.movieOffer = this.movieDetails?.movieData.movieOffers[0]
  }

  get seances() {
    return this.addRepertoiresForm.get('seances') as FormArray;
  }

  addSeance() {
    const seance = this.formBuilder.group({
      date: ['', Validators.required],
      hall: [0, Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required],
      languageVersion: [0, Validators.required]
    });
    this.seances.push(seance);
  }

  removeSeance(index: number) {
    this.seances.removeAt(index);
  }

  onSubmit() {
    if (this.addRepertoiresForm.valid) {
      console.log('Form Submitted', this.addRepertoiresForm.value);
    } else {
      console.log('Form is not valid');
    }
  }

  protected readonly Date = Date;
}
