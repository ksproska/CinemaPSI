import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieDetailsResolver} from "./resolvers/movie-details.resolver";
import {ChooseTicketComponent} from "./components/choose-ticket/choose-ticket.component";
import {RepertoireService} from "./services/repertoire.service";
import {RepertoireResolver} from "./resolvers/repertoire.resolver";
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
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
