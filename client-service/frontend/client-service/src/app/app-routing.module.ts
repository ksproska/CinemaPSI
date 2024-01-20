import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MovieListComponent} from "./components/movie-list/movie-list.component";
import {MovieDetailsComponent} from "./components/movie-details/movie-details.component";
import {getMovieByIdResolver} from "./resolvers/get-movie-by-id.resolver";

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
    path: 'movies/:movieId/details',
    component: MovieDetailsComponent,
    resolve: {
      movie: getMovieByIdResolver,
    }
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
