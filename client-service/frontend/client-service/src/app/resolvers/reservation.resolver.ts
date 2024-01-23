import {ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot} from '@angular/router';
import {inject} from "@angular/core";
import {RepertoireService} from "../services/repertoire.service";
import {TicketsService} from "../services/tickets.service";
import {HallSetupForRepertoire} from "../models/hallSetupforRepertoire";
import {RepertoireDetails} from "../models/RepertoireDetails";
import {ReservationDetails} from "../models/reservationDetails";

export const ReservationResolver: ResolveFn<ReservationDetails>  =
  (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(TicketsService).getReservationDetails(route.paramMap.get('reservationId')!);
  };


