import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseCinemaComponent } from './choose-cinema.component';

describe('ChooseCinemaComponent', () => {
  let component: ChooseCinemaComponent;
  let fixture: ComponentFixture<ChooseCinemaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChooseCinemaComponent]
    });
    fixture = TestBed.createComponent(ChooseCinemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
