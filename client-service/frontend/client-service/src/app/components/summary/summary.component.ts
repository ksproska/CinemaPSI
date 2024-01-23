import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ReservationDetails} from "../../models/reservationDetails";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit{

  reservationDetails: ReservationDetails | undefined;
  reservationId: number | any;
  constructor(private route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    if (localStorage.getItem("ticketChosen") !== "1"){
      this.router.navigate([`/movies`]);
    }
    setTimeout(() => {
      // Redirect to the main page
      window.location.href = '/';
    },  15*60*1000); // 15 minutes = 900000 = 15*60*1000
    this.reservationId = this.route.snapshot.paramMap.get('reservationId');
    this.route.data.subscribe(
      ({data}) => {
        this.reservationDetails = data;
      });
  }
}
