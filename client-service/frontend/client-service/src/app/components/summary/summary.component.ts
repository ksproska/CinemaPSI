import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PriceService} from "../../services/price.service";
import {TicketsService} from "../../services/tickets.service";
import {Price} from "../../models/price";
import {Reservation} from "../../models/reservation";
import {ReservationDetails} from "../../models/reservationDetails";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit{

  reservationDetails: ReservationDetails | undefined;
  constructor(private route: ActivatedRoute, private priceService: PriceService,  private cdRef: ChangeDetectorRef,
              private ticketsService: TicketsService, private router: Router) {}
  ngOnInit(): void {
    this.route.data.subscribe(
      ({data}) => {
        this.reservationDetails = data;
      });
  }


  goToPayment(): void {
    this.router.navigate([`/payment/${this.route.snapshot.paramMap.get('reservationId')}`]);
  }

}
