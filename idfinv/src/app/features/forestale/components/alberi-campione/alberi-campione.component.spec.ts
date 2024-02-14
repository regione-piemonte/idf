/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlberiCampioneComponent } from './alberi-campione.component';

describe('AlberiCampioneComponent', () => {
  let component: AlberiCampioneComponent;
  let fixture: ComponentFixture<AlberiCampioneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlberiCampioneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlberiCampioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
