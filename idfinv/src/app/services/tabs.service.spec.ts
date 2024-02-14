/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TestBed, inject } from '@angular/core/testing';

import { TabsService } from './tabs.service';

describe('TabsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TabsService]
    });
  });

  it('should be created', inject([TabsService], (service: TabsService) => {
    expect(service).toBeTruthy();
  }));
});
