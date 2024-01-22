import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-failure',
  templateUrl: './payment-failure.component.html',
  styleUrls: ['./payment-failure.component.css']
})
export class PaymentFailureComponent {
  constructor(private router: Router) {}

  seeClientTickets(){
    this.router.navigate(['/my-tickets']);
  }
}
