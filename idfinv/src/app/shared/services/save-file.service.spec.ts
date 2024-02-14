/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { SaveFileService } from './save-file.service';

describe('SaveFileService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SaveFileService]
    });
  });

  it('should be created', inject([SaveFileService], (service: SaveFileService) => {
    expect(service).toBeTruthy();
  }));
});
