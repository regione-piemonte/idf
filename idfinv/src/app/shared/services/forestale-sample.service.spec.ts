/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { ForestaleSampleService } from './forestale-sample.service';

describe('ForestaleSampleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ForestaleSampleService]
    });
  });

  it('should be created', inject([ForestaleSampleService], (service: ForestaleSampleService) => {
    expect(service).toBeTruthy();
  }));
});
