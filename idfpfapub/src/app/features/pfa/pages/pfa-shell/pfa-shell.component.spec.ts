/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PfaShellComponent } from './pfa-shell.component';

describe('PfaShellComponent', () => {
  let component: PfaShellComponent;
  let fixture: ComponentFixture<PfaShellComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PfaShellComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PfaShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
