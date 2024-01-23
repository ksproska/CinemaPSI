import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TicketDetails} from "../../models/ticket-details";

@Component({
  selector: 'app-client-tickets',
  templateUrl: './client-tickets.component.html',
  styleUrls: ['./client-tickets.component.css']
})
export class ClientTicketsComponent implements OnInit {
  ticketDetails?: TicketDetails[]
  constructor(private route: ActivatedRoute) {}

  ngOnInit(){
    this.route.data.subscribe(
      ({data}) => {
        this.ticketDetails = data["ticketDetails"]
      });
  }
}
