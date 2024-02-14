/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestitiPfaComponent } from './gestiti-pfa.component';

describe('GestitiPfaComponent', () => {
  let component: GestitiPfaComponent;
  let fixture: ComponentFixture<GestitiPfaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestitiPfaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestitiPfaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
