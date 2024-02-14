/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaglioComponent } from './taglio.component';

describe('TaglioComponent', () => {
  let component: TaglioComponent;
  let fixture: ComponentFixture<TaglioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaglioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaglioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
