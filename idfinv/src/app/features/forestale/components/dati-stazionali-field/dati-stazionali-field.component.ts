/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { DatiStazionaliField } from 'src/app/shared/models/dati-stazionali-field';
import { TabLabels } from 'src/app/shared/models/label-tooltip-mapper';

@Component({
  selector: 'app-dati-stazionali-field',
  templateUrl: './dati-stazionali-field.component.html',
  styleUrls: ['./dati-stazionali-field.component.css']
})
export class DatiStazionaliFieldComponent implements OnInit {

  @Input() item: DatiStazionaliField;
  tabLabels: Map<string, string>;

  constructor() { }

  ngOnInit() {
    this.tabLabels = TabLabels
  }

}
