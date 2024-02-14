/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BasimetriaComponent } from './basimetria.component';

describe('BasimetriaComponent', () => {
  let component: BasimetriaComponent;
  let fixture: ComponentFixture<BasimetriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasimetriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BasimetriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
