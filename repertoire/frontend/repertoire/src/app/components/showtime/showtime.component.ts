import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {DatePipe, NgForOf, NgStyle} from "@angular/common";
import {TimeLineComponent} from "../time-line/time-line.component";
import {Showing} from "../../models/showing";
import {interval, startWith} from "rxjs";

@Component({
  selector: 'app-showtime',
  standalone: true,
  imports: [
    NgForOf,
    TimeLineComponent,
    DatePipe,
    NgStyle
  ],
  templateUrl: './showtime.component.html',
  styleUrl: './showtime.component.css',
})
export class ShowtimeComponent implements OnInit, OnDestroy {

  @Input() showings: Showing[] = [];
  showingsFiltered: Showing[] = [];
  currentHour: number = new Date().getHours()
  private intervalSubscription: any;
  ngOnInit() {
    this.intervalSubscription = interval(60000).pipe(startWith(0)).subscribe(() => {
      this.filterShowing()
    });
  }

  calculateWidth(showing: Showing): any {
    const startTime = new Date(showing.starting);
    const endTime = new Date(showing.ending);

    const startHour = startTime.getHours();
    const startMinutes = startTime.getMinutes();
    const endHour = endTime.getHours();
    const endMinutes = endTime.getMinutes();

    const isNextDay = endTime.getDate() > startTime.getDate() ||
      (endTime.getDate() === startTime.getDate() && endHour < startHour);

    const startTimeInMinutes = startHour * 60 + startMinutes - this.currentHour * 60;
    let endTimeInMinutes = endHour * 60 + endMinutes - this.currentHour * 60;

    if (isNextDay) {
      endTimeInMinutes += 24 * 60;
    }

    const totalTimeInMinutes = endTimeInMinutes - startTimeInMinutes;

    const widthPercentage = (totalTimeInMinutes / 60) * 20;
    const positionPercentage = ((startHour - this.currentHour) * 60 + startMinutes) / 60 * 20;

    return {
      'width': `${widthPercentage}%`,
      'left': `${positionPercentage}%`
    };
  }

  filterShowing(): void {
    const currentDate = new Date()
    const next5Hours = new Date(currentDate.getTime() + 5 * 60 * 60 * 1000);
    this.showingsFiltered = this.showings.filter(
      (showing) => {
        const startTime = new Date(showing.starting)
        const endTime = new Date(showing.ending)
        return (startTime > currentDate && endTime < next5Hours)
          || (startTime < currentDate && endTime > currentDate)
          || (startTime < next5Hours && endTime > next5Hours)
      }
    )
  }

  ngOnDestroy() {
    if (this.intervalSubscription) {
      this.intervalSubscription.unsubscribe();
    }
  }

}
