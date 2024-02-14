/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventStepComponent } from './event-step.component';

describe('EventStepComponent', () => {
  let component: EventStepComponent;
  let fixture: ComponentFixture<EventStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
