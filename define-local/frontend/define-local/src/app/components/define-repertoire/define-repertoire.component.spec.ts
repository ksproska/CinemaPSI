import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefineRepertoireComponent } from './define-repertoire.component';

describe('DefineRepertoireComponent', () => {
  let component: DefineRepertoireComponent;
  let fixture: ComponentFixture<DefineRepertoireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefineRepertoireComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefineRepertoireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
