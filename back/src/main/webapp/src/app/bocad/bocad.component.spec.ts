import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BocadComponent } from './bocad.component';

describe('BocadComponent', () => {
  let component: BocadComponent;
  let fixture: ComponentFixture<BocadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BocadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BocadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
