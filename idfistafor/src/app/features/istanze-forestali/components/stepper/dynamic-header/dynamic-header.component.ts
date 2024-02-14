/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-dynamic-header',
  templateUrl: './dynamic-header.component.html',
  styleUrls: ['./dynamic-header.component.css']
})
export class DynamicHeaderComponent implements OnInit {

  @Input()activeIndex;
  @Input()isCompleted;
  intestazione = [
    'dati del richiedente',
    'localizzazione del bosco che si intende trasformare',
    'descrizione del bosco che si intende trasformare e tipologia di trasformazione',
    'compensazione della superficie forestale da trasformare',
    'dati del tecnico forestale abilitato che ha determinato il calcolo economico della compensazione',
    'dichiarazioni'
  ];
  constructor() { }

  ngOnInit() {
  }

}
