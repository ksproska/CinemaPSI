import {Component, Input, OnInit} from '@angular/core';
import {DatePipe, NgForOf, NgStyle} from "@angular/common";
import {TimeLineComponent} from "../time-line/time-line.component";
import {Showing} from "../../models/showing";

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
export class ShowtimeComponent implements OnInit {

  @Input() showings: Showing[] = [];
  showingsFiltered: Showing[] = [];
  currentHour: number = new Date().getHours()
  currentMinutes: number = new Date().getMinutes()
  ngOnInit() {
    const next5Hours = this.currentHour + 5;
    this.showingsFiltered = this.showings.filter(
      (showing) => new Date(showing.starting).getHours() < next5Hours
        && new Date(showing.ending).getHours() > this.currentHour
    );
    this.showingsFiltered.forEach((showing) => console.log(`${new Date (showing.starting).getHours()} ${new Date(showing.ending).getHours()}`))
    console.log(new Date())
    console.log(this.currentHour)
  }

  calculateWidth(showing: Showing): any {
    const startTime = new Date(showing.starting);
    const endTime = new Date(showing.ending);

    const startHour = startTime.getHours();
    const startMinutes = startTime.getMinutes();
    const endHour = endTime.getHours();
    const endMinutes = endTime.getMinutes();

    const startTimeInMinutes = startHour * 60 + startMinutes - this.currentHour * 60;
    const endTimeInMinutes = endHour * 60 + endMinutes - this.currentHour * 60;
    // const currentHourInMinutes = this.currentHour * 60 + this.currentMinutes

    const totalTimeInMinutes = endTimeInMinutes - startTimeInMinutes;

    const widthPercentage = (totalTimeInMinutes / 60) * 20;
    const positionPercentage = startTimeInMinutes / 60 * 20;

    return {
      'width': `${widthPercentage}%`,
      'left': `${positionPercentage}%`
    };
  }

}
