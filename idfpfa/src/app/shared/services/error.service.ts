/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable, EventEmitter } from "@angular/core";

@Injectable({
  providedIn: "root"
})
export class ErrorService {
  errorObservable: EventEmitter<string> = new EventEmitter();

  constructor() {}

  emitError(message: string) {
    console.log("Emmiting error", message);
    this.errorObservable.emit(message);
  }
}
