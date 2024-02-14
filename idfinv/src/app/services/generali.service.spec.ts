/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { GeneraliService } from './generali.service';

describe('GeneraliService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GeneraliService]
    });
  });

  it('should be created', inject([GeneraliService], (service: GeneraliService) => {
    expect(service).toBeTruthy();
  }));
});
