/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  loadingObservable = new EventEmitter<boolean>();

  constructor() { }

  /**
   * Emit Loading
   * @param isActiveLoading. Is message error
   */
  emitLoading(isLoadingActive: boolean) {
    console.log('Loading', isLoadingActive);
    this.loadingObservable.emit(isLoadingActive);
  }
}
