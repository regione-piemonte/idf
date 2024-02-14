/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { PfaInterventoService } from './pfa-intervento.service';

describe('PfaInterventoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PfaInterventoService]
    });
  });

  it('should be created', inject([PfaInterventoService], (service: PfaInterventoService) => {
    expect(service).toBeTruthy();
  }));
});
