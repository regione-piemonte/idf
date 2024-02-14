/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatiTecniciComponent } from './dati-tecnici.component';

describe('DatiTecniciComponent', () => {
  let component: DatiTecniciComponent;
  let fixture: ComponentFixture<DatiTecniciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatiTecniciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatiTecniciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
