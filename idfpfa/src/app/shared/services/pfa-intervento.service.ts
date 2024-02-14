/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class PfaInterventoService {
  private _polygonoSubject: Subject<PolygonoEvent> = new Subject();

  constructor() {}

  emitPolygonoEvento(polygonoEvent: PolygonoEvent) {
    this._polygonoSubject.next(polygonoEvent);
  }

  get polygonoSubject() {
    return this._polygonoSubject;
  }
}

export interface PolygonoEvent {

  tipoEvento?: any;
  payload?: any;
}
