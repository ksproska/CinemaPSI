import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from "@angular/router";
import {RepertoiresForSingleMovie} from "../models/repertoiresForSingleMovie";
import {inject} from "@angular/core";
import {MoviesService} from "../services/movies.service";

export const MovieDetailsResolver: ResolveFn<RepertoiresForSingleMovie> =
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(MoviesService).getMovieById(route.paramMap.get('city')!, route.paramMap.get('movieId')!);
  };
