/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlberiCavallettatiComponent } from './alberi-cavallettati.component';

describe('AlberiCavallettatiComponent', () => {
  let component: AlberiCavallettatiComponent;
  let fixture: ComponentFixture<AlberiCavallettatiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlberiCavallettatiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlberiCavallettatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
