/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatiStazionaliTabsSecondComponent } from './dati-stazionali-tabs-second.component';

describe('DatiStazionaliTabsSecondComponent', () => {
  let component: DatiStazionaliTabsSecondComponent;
  let fixture: ComponentFixture<DatiStazionaliTabsSecondComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatiStazionaliTabsSecondComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatiStazionaliTabsSecondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
