/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableHeader } from 'src/app/shared/models/table-header';
import { AlberiCampioneDominante } from 'src/app/shared/models/alberi-campione-dominante';
import { Content } from 'src/app/shared/models/table-object';

@Component({
  selector: 'app-alberi-cavallettati',
  templateUrl: './alberi-cavallettati.component.html',
  styleUrls: ['./alberi-cavallettati.component.scss']
})
export class AlberiCavallettatiComponent implements OnInit {

  @Input() initiallySortedColumn: string;
  @Input() alberiData: AlberiCampioneDominante[];
  @Input() tableHeaders: TableHeader[];
  @Input() totalElements: number;
  @Output() emitTableChanges = new EventEmitter<any>();
  tipoCampione: string = 'CAV';
  constructor() { }

  ngOnInit() { }

  getUpdatedTable(event) {
    this.emitTableChanges.emit(event);
  }

}
