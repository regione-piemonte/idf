/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlberiCavallettatiStepComponent } from './alberi-cavallettati-step.component';

describe('AlberiCavallettatiStepComponent', () => {
  let component: AlberiCavallettatiStepComponent;
  let fixture: ComponentFixture<AlberiCavallettatiStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlberiCavallettatiStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlberiCavallettatiStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
