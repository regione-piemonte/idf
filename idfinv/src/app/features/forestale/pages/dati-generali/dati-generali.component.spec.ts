/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatiGeneraliComponent } from './dati-generali.component';

describe('DatiGeneraliComponent', () => {
  let component: DatiGeneraliComponent;
  let fixture: ComponentFixture<DatiGeneraliComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatiGeneraliComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatiGeneraliComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
