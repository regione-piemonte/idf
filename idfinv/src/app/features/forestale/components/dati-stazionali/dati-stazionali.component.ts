/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-dati-stazionali',
  templateUrl: './dati-stazionali.component.html',
  styleUrls: ['./dati-stazionali.component.css']
})
export class DatiStazionaliComponent implements OnInit {

  @Input() datiStazionali: any;

  constructor() { }

  ngOnInit() { }

}
