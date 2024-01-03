import {Component, OnInit} from '@angular/core';
import {NgForOf, NgStyle} from "@angular/common";
import {interval} from "rxjs";

@Component({
  selector: 'app-time-line',
  standalone: true,
  imports: [
    NgForOf,
    NgStyle
  ],
  templateUrl: './time-line.component.html',
  styleUrl: './time-line.component.css'
})
export class TimeLineComponent implements OnInit {
  next5Hours: number[] = [];
  currentMinutePercentage: number = 0;
  private intervalSubscription: any;

  ngOnInit() {
    this.updateTimeline();

    this.intervalSubscription = interval(60000).subscribe(() => {
      this.updateTimeline();
      this.getHourStyles(new Date().getHours());
    });
  }

  ngOnDestroy() {
    if (this.intervalSubscription) {
      this.intervalSubscription.unsubscribe();
    }
  }

  private updateTimeline() {
    const currentDate = new Date();
    const currentHour = currentDate.getHours();

    this.next5Hours = Array.from({ length: 5 }, (_, index) => (currentHour + index) % 24);

    const currentMinute = currentDate.getMinutes();
    this.currentMinutePercentage = (currentMinute / 60) * 100;
  }

  isCurrentHour(hour: number): boolean {
    // Check if the provided hour is the current hour
    return hour === new Date().getHours();
  }
  getHourStyles(hour: number): any {
    if (this.isCurrentHour(hour)) {
      return {
        'background': `linear-gradient(to right, #cbcbcb 0%, #cbcbcb ${this.currentMinutePercentage}%, transparent ${this.currentMinutePercentage}%, transparent 100%)`,
        'color': '#000'
      };
    } else {
      return {
        'background-color': '#fff',
        'color': '#000'
      };
    }
  }
}
