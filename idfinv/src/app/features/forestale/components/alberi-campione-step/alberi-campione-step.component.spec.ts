/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlberiCampioneStepComponent } from './alberi-campione-step.component';

describe('AlberiCampioneStepComponent', () => {
  let component: AlberiCampioneStepComponent;
  let fixture: ComponentFixture<AlberiCampioneStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlberiCampioneStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlberiCampioneStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
