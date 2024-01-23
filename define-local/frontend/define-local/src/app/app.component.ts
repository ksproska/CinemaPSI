import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {SeanceFormComponent} from "./components/seance-form/seance-form.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SeanceFormComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'define-local';
}
