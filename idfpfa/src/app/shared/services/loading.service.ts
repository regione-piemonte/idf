/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable, EventEmitter } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class LoadingService {
  loadingObservable = new EventEmitter<boolean>();

  constructor() {}

  /*
   * Emit loading
   *
   */

  emitLoading(isLoadingActive: boolean) {
    this.loadingObservable.emit(isLoadingActive);
  }
}
