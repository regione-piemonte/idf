/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { AreaSearch } from '../../models/area-search';
import { AreaDiSaggio } from '../../models/area-di-saggio';

@Component({
  selector: 'app-tipologia-panel',
  templateUrl: './tipologia-panel.component.html',
  styleUrls: ['./tipologia-panel.component.css']
})
export class TipologiaPanelComponent implements OnInit {

  @Input() panelData: AreaDiSaggio;

  constructor() { }

  ngOnInit() {
  }

}
