import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MovieListComponent } from './components/movie-list/movie-list.component';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {HttpClientModule} from "@angular/common/http";
import { ChooseTicketComponent } from './components/choose-ticket/choose-ticket.component';
import { SummaryComponent } from './components/summary/summary.component';
import { PaymentFormComponent } from './components/payment-form/payment-form.component';
import { PaymentFailureComponent } from './components/payment-failure/payment-failure.component';
import { PaymentSuccessComponent } from './components/payment-success/payment-success.component';
import { ClientTicketsComponent } from './components/client-tickets/client-tickets.component';
import {QRCodeModule} from "angularx-qrcode";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule} from '@angular/material/core';
import {MatCheckboxModule} from "@angular/material/checkbox";
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ChooseCinemaComponent } from './components/choose-cinema/choose-cinema.component';

@NgModule({
  declarations: [
    AppComponent,
    MovieListComponent,
    MovieDetailsComponent,
    TopBarComponent,
    ChooseTicketComponent,
    SummaryComponent,
    PaymentFormComponent,
    SummaryComponent,
    ChooseTicketComponent,
    PaymentFailureComponent,
    PaymentSuccessComponent,
    ClientTicketsComponent,
    ChooseCinemaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatIconModule,
    HttpClientModule,
    ReactiveFormsModule,
    QRCodeModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatSelectModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
