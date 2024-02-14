/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ForestaleShellComponent } from './forestale-shell.component';

describe('ForestaleShellComponent', () => {
  let component: ForestaleShellComponent;
  let fixture: ComponentFixture<ForestaleShellComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ForestaleShellComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ForestaleShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
