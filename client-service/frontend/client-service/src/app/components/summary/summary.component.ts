import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ReservationDetails} from "../../models/reservationDetails";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit{

  reservationDetails: ReservationDetails | undefined;
  reservationId: number | any;
  constructor(private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.reservationId = this.route.snapshot.paramMap.get('reservationId');
    this.route.data.subscribe(
      ({data}) => {
        this.reservationDetails = data;
      });
  }
}
