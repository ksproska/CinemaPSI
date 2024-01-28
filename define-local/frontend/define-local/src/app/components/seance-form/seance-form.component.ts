import {Component, inject, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {MovieDetailDTO} from "../../dto/movie-detail-dto";
import {DatePipe, NgIf} from "@angular/common";
import {MovieOffer} from "../../models/movie-offer";
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {setAnalyticsConfig} from "@angular/cli/src/analytics/analytics";
import {RepertoireCandidate} from "../../models/repertoire-candidate";
import {AddRepertoireRequest} from "../../dto/add-repertoire-request";
import {DefineLocalService} from "../../services/define-local.service";

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
  private defineLocalService = inject(DefineLocalService)
  @Input() movieDetails?: MovieDetailDTO
  movieOffer?: MovieOffer
  today?: Date
  responseMessage?: string
  submitMessage?: string

  addRepertoiresForm = this.formBuilder.group({
    seances: this.formBuilder.array([], Validators.required)
  });

  ngOnChanges(changes: SimpleChanges): void {
    this.today = new Date
    if(this.movieDetails?.movieData.movieOffers)
      this.movieOffer = this.movieDetails?.movieData.movieOffers[0]
  }

  get seances() {
    return this.addRepertoiresForm.get('seances') as FormArray;
  }

  addSeance() {
    const seance = this.formBuilder.group({
      date: [null, Validators.required],
      hall: [null, Validators.required],
      startTime: [null, Validators.required],
      endTime: [''],
      versionOfferMovieId: [null, Validators.required]
    });

    seance.get('startTime')?.valueChanges.subscribe(startTime => {
      let endTime
      if(startTime) {
        endTime = this.calculateEndTime(startTime, this.movieDetails?.movieData?.lengthMinutes);
      }
      if(endTime)
        seance.get('endTime')?.setValue(endTime, { emitEvent: true });
    });

    this.seances.push(seance);
  }

  removeSeance(index: number) {
    this.seances.removeAt(index);
  }

  onSubmit() {
    if (this.addRepertoiresForm.valid) {
      const seances: any[] = this.addRepertoiresForm.get('seances')!.value;

      const movieId = this.movieDetails!.movieData.id

      const repertoireCandidates: RepertoireCandidate[] = seances.map(seanceData => {
        console.log(seanceData)
        const startingDateTime = `${seanceData.date}T${seanceData.startTime}:00`;
        return {
          starting: startingDateTime,
          hallId: seanceData.hall,
          versionOfferMovieId: seanceData.versionOfferMovieId
        };
      });

      const payload: AddRepertoireRequest = {
        movieId: movieId,
        repertoireCandidates: repertoireCandidates
      };
      this.defineLocalService.addRepertoire(payload).subscribe({
        next: value => console.log(value),
        error: err => {
          if(err.status == 400)
            this.responseMessage = err.error
          else if (err.status == 200)
            this.responseMessage = err.error.text
        }
      })
      console.log('Payload to be sent:', payload);
      this.submitMessage = ""
    } else {
      console.log('Form is not valid');
      this.submitMessage = "Upewnij się że wszystkie pola są uzupełnione!"
    }
  }

  calculateEndTime(startTime: string, movieLength: number | undefined): string {
    const start = new Date();
    const [hours, minutes] = startTime.split(':').map(Number);
    start.setHours(hours, minutes);

    if(movieLength)
      start.setMinutes(start.getMinutes() + movieLength);

    return start.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  }
}
