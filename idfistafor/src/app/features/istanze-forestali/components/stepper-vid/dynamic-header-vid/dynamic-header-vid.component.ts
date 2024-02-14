/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-dynamic-header-vid',
  templateUrl: './dynamic-header-vid.component.html',
  styleUrls: ['./dynamic-header-vid.component.css']
})
export class DynamicHeaderVidComponent implements OnInit {

  @Input()activeIndex;
  @Input()isCompleted;
  intestazione = [
    'dati del richiedente',
    'localizzazione dell\'area oggetto dell\'intervento',
    'descrizione dell\'intervento',
    'cauzione',
    'rimboschimento e compensazione forestale',
    'dichiarazioni, comunicazioni, allegati'
  ];
  constructor() { }

  ngOnInit() {
  }

}
