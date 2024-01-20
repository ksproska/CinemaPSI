import { TestBed } from '@angular/core/testing';
import { ResolveFn } from '@angular/router';

import { getMovieByIdResolver } from './get-movie-by-id.resolver';

describe('getMovieByIdResolver', () => {
  const executeResolver: ResolveFn<boolean> = (...resolverParameters) => 
      TestBed.runInInjectionContext(() => getMovieByIdResolver(...resolverParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeResolver).toBeTruthy();
  });
});
