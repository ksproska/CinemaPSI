import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PaymentService } from '../../services/payment.service';
import {ActivatedRoute} from "@angular/router"; // Import the payment service you created

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css']
})
export class PaymentFormComponent {
  paymentForm: FormGroup;
  reservationId = "";
  chosenPaymentService = "mock-service";

  constructor(private fb: FormBuilder, private paymentService: PaymentService, private route: ActivatedRoute) {
    this.paymentForm = this.fb.group({
      paymentService: ['mock-service']
    });
    if (this.route.snapshot.paramMap.get('reservationId') !== null){
      // @ts-ignore
      this.reservationId = this.route.snapshot.paramMap.get('reservationId');
    }
  }

  onSubmit(): void {

    // window.location.href = 'http://localhost:8000/?paymentId=7&total=39.88';
    if (this.paymentForm.valid) {
      this.paymentService.pay({"reservationId" : this.reservationId, "paymentService" : this.chosenPaymentService}) .subscribe(
        response => {

          // window.location.href = 'https://www.externalwebsite.com';
        },
        error => {
          console.error('There was an error!', error);
        }
      );
    }
  }
}
