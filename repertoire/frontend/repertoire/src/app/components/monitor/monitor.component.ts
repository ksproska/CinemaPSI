import {Component, inject, OnInit} from '@angular/core';
import {MovieInfoComponent} from "../movie-info/movie-info.component";
import {ShowtimeComponent} from "../showtime/showtime.component";
import {repeat} from "rxjs";
import {RepertoireService} from "../../services/repertoire.service";
import {Repertoire} from "../../models/repertoire";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-monitor',
  standalone: true,
  imports: [
    MovieInfoComponent,
    ShowtimeComponent,
    NgForOf
  ],
  templateUrl: './monitor.component.html',
  styleUrl: './monitor.component.css'
})
export class MonitorComponent implements OnInit {
  private repertoireService = inject(RepertoireService)

  repertoireInfo?: Repertoire[]

  ngOnInit() {
    this.repertoireService.getRepertoire().subscribe({
      next: value => {
        this.repertoireInfo = value
      }
    })
  }
}
