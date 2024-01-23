import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from '@angular/router';
import {inject} from "@angular/core";
import {MoviesService} from "../services/movies.service";
import {RepertoireService} from "../services/repertoire.service";
import {HallSetupForRepertoire} from "../models/hallSetupforRepertoire";

export const RepertoireResolver: ResolveFn<HallSetupForRepertoire> =
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(RepertoireService).getHallSetupByRepertoireId(route.paramMap.get('city')!, route.paramMap.get('repertoireId')!);
};
