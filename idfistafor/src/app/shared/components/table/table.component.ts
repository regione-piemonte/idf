/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  selectedRow: number;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  updateData(event) {
    const data = event;
    if (event.sortOrder === -1) {
      data.field = event.sortField ? '-' + event.sortField : null;
    } else {
      data.field = event.sortField ? event.sortField : null;
    }
    data.page = event.first / event.rows + 1;
    data.limit = event.rows;
    this.updateTableChanges.emit(data);
  }

  onOpen() {}

}
