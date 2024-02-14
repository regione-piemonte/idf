/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { DatiStazionaliField } from 'src/app/shared/models/dati-stazionali-field';

@Component({
  selector: 'app-dettaglio-grid',
  templateUrl: './dettaglio-grid.component.html',
  styleUrls: ['./dettaglio-grid.component.css']
})
export class DettaglioGridComponent implements OnInit {

  @Input() dataStazionaliFields: DatiStazionaliField[];

  constructor() { }

  ngOnInit() {
  }

}
