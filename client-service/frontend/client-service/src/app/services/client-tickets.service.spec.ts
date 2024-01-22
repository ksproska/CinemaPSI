import { TestBed } from '@angular/core/testing';

import { ClientTicketsService } from './client-tickets.service';

describe('ClientTicketsService', () => {
  let service: ClientTicketsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientTicketsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
