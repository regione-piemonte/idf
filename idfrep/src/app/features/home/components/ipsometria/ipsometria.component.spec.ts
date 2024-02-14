/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IpsometriaComponent } from './ipsometria.component';

describe('IpsometriaComponent', () => {
  let component: IpsometriaComponent;
  let fixture: ComponentFixture<IpsometriaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IpsometriaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IpsometriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
