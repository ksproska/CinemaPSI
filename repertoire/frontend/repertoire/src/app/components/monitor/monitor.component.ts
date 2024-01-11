import {AfterViewInit, Component, ElementRef, inject, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MovieInfoComponent} from "../movie-info/movie-info.component";
import {ShowtimeComponent} from "../showtime/showtime.component";
import {RepertoireService} from "../../services/repertoire.service";
import {Repertoire} from "../../models/repertoire";
import {NgForOf} from "@angular/common";
import {interval, startWith} from "rxjs";

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
export class MonitorComponent implements OnInit, AfterViewInit, OnDestroy {
  private repertoireService = inject(RepertoireService)
  repertoireInfo?: Repertoire[]

  @ViewChild('scrollContainer') private scrollContainer!: ElementRef;
  private intervalSubscription: any;

  ngOnInit() {
    this.intervalSubscription = interval(60000).pipe(startWith(0))
      .subscribe(() => {
        this.repertoireService.getRepertoire().subscribe({
          next: value => {
            this.repertoireInfo = value
          }
        });
      })
  }

  ngAfterViewInit(): void {
    this.startAutoScroll();
  }

  startAutoScroll(): void {
    const container = this.scrollContainer.nativeElement;
    let scrollSpeed = 1;

    setInterval(() => {
      container.scrollTop += scrollSpeed;

      const firstElement = container.querySelector('.item');
      if (firstElement && firstElement.getBoundingClientRect().bottom < 0) {
        container.appendChild(firstElement);
        container.scrollTop -= firstElement.clientHeight;
      }
    }, 20);
  }

  ngOnDestroy() {
    if (this.intervalSubscription) {
      this.intervalSubscription.unsubscribe();
    }
  }
}
