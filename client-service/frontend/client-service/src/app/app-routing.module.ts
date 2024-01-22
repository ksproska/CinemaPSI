import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieDetailsResolver} from "./resolvers/movie-details.resolver";
import {ChooseTicketComponent} from "./components/choose-ticket/choose-ticket.component";
import {RepertoireService} from "./services/repertoire.service";
import {RepertoireResolver} from "./resolvers/repertoire.resolver";
import {PaymentSuccessComponent} from "./components/payment-success/payment-success.component";
import {PaymentFailureComponent} from "./components/payment-failure/payment-failure.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/movies'
  },
  {
    path: 'movies',
    component: MovieListComponent,
    // resolve: {
    //   movies: MoviesListResolver,
    //   screenings: getScreeningsTodayResolver
    // }
  },
  {
    path: 'movies/:movieId',
    component: MovieDetailsComponent,
    resolve: {
      data: MovieDetailsResolver,
    }
  },
  {
    path: 'tickets/:repertoireId',
    component: ChooseTicketComponent,
    resolve: {
      data: RepertoireResolver,
    }
  },
  {
    path: 'payment-status/success',
    component: PaymentSuccessComponent
  },
  {
    path: 'payment-status/failure',
    component: PaymentFailureComponent
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
