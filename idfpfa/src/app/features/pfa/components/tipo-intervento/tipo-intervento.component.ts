/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { TipoInterventoDettaglio } from 'src/app/shared/models/dettaglio-intervento';

@Component({
  selector: 'app-tipo-intervento',
  templateUrl: './tipo-intervento.component.html',
  styleUrls: ['./tipo-intervento.component.css']
})
export class TipoInterventoComponent implements OnInit {
 @Input() tipoInterventoDettaglio$: Observable<TipoInterventoDettaglio>;
  constructor() { }

  ngOnInit() {
  }

}
