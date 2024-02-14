/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { StepsService } from './steps.service';

describe('StepsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StepsService]
    });
  });

  it('should be created', inject([StepsService], (service: StepsService) => {
    expect(service).toBeTruthy();
  }));
});
