import { TestBed } from '@angular/core/testing';

import { DefineLocalService } from './define-local.service';

describe('DefineLocalService', () => {
  let service: DefineLocalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DefineLocalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
