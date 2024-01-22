import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {MovieDetailsResolver} from "./resolvers/movie-details.resolver";

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
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
