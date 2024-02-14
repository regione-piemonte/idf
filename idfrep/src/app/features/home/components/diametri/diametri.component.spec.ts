/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiametriComponent } from './diametri.component';

describe('DiametriComponent', () => {
  let component: DiametriComponent;
  let fixture: ComponentFixture<DiametriComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiametriComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiametriComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
