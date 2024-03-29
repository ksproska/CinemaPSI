import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieDetailsResolver} from "./resolvers/movie-details.resolver";
import {ChooseTicketComponent} from "./components/choose-ticket/choose-ticket.component";
import {RepertoireResolver} from "./resolvers/repertoire.resolver";
import {SummaryComponent} from "./components/summary/summary.component";
import {ReservationResolver} from "./resolvers/reservation.resolver";
import {PaymentSuccessComponent} from "./components/payment-success/payment-success.component";
import {PaymentFailureComponent} from "./components/payment-failure/payment-failure.component";
import {ClientTicketsComponent} from "./components/client-tickets/client-tickets.component";
import {clientTicketsResolver} from "./resolvers/client-tickets.resolver";
import {ChooseCinemaComponent} from "./components/choose-cinema/choose-cinema.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/selectCinema'
  },
  {
    path: 'selectCinema',
    component: ChooseCinemaComponent
  },
  {
    path: 'movies/:city',
    component: MovieListComponent,
    // resolve: {
    //   movies: MoviesListResolver,
    //   screenings: getScreeningsTodayResolver
    // }
  },
  {
    path: 'movies/:city/:movieId',
    component: MovieDetailsComponent,
    resolve: {
      data: MovieDetailsResolver,
    }
  },
  {
    path: 'tickets/:city/:repertoireId',
    component: ChooseTicketComponent,
    resolve: {
      data: RepertoireResolver,
    }
  },
  {
    path: 'summary/:city/:reservationId',
    component: SummaryComponent,
    resolve: {
      data: ReservationResolver,
    }
  },
  {
    path: 'payment-status/success',
    component: PaymentSuccessComponent
  },
  {
    path: 'payment-status/failure',
    component: PaymentFailureComponent
  },
  {
    path: 'my-tickets',
    component: ClientTicketsComponent,
    resolve: {
      data: clientTicketsResolver
    }
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
