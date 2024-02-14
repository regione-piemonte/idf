/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { DownloadService } from './download.service';

describe('DownloadService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DownloadService]
    });
  });

  it('should be created', inject([DownloadService], (service: DownloadService) => {
    expect(service).toBeTruthy();
  }));
});
