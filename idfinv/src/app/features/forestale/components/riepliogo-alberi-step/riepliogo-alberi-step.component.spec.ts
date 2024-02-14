/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiepliogoAlberiStepComponent } from './riepliogo-alberi-step.component';

describe('RiepliogoAlberiStepComponent', () => {
  let component: RiepliogoAlberiStepComponent;
  let fixture: ComponentFixture<RiepliogoAlberiStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiepliogoAlberiStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiepliogoAlberiStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
