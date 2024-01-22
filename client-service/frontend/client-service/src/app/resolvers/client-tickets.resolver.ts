import { ResolveFn } from '@angular/router';
import {inject} from "@angular/core";
import {ClientTicketsService} from "../services/client-tickets.service";
import {ClientTickets} from "../models/client-tickets";

export const clientTicketsResolver: ResolveFn<ClientTickets> = (route, state) => {
  return inject(ClientTicketsService).getAllClientTickets();
};
