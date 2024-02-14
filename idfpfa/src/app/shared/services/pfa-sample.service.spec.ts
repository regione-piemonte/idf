/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { PfaSampleService } from './pfa-sample.service';

describe('PfaSampleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PfaSampleService]
    });
  });

  it('should be created', inject([PfaSampleService], (service: PfaSampleService) => {
    expect(service).toBeTruthy();
  }));
});
