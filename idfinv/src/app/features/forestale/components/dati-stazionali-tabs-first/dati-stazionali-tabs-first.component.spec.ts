/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatiStazionaliTabsFirstComponent } from './dati-stazionali-tabs-first.component';

describe('DatiStazionaliTabsFirstComponent', () => {
  let component: DatiStazionaliTabsFirstComponent;
  let fixture: ComponentFixture<DatiStazionaliTabsFirstComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatiStazionaliTabsFirstComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatiStazionaliTabsFirstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
