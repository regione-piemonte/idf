/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-dynamic-header-tagli',
  templateUrl: './dynamic-header-tagli.component.html',
  styleUrls: ['./dynamic-header-tagli.component.css']
})
export class DynamicHeaderTagliComponent implements OnInit {

  @Input()activeIndex;
  @Input()isCompleted;
  intestazione = [
    'dati generali',
    'dati del richiedente',
    'localizzazione dell\'intervento',
    'descrizione dell\'intervento',
    'Allegati'
  ];
  constructor() { }

  ngOnInit() {
  }

}
