import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { clientTicketsResolver } from './client-tickets.resolver';
import {ClientTickets} from "../models/client-tickets";

describe('clientTicketsResolver', () => {
  const executeResolver: ResolveFn<ClientTickets> = (...resolverParameters) =>
      TestBed.runInInjectionContext(() => clientTicketsResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
