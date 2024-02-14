/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-reusable-no-select-table',
  templateUrl: './reusable-no-select-table.component.html',
  styleUrls: ['./reusable-no-select-table.component.scss']
})
export class ReusableNoSelectTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Input() initiallySortedColumn: string;
  @Input() totalElements: number;
  @Input() sortable: boolean;
  @Input() pagination: boolean;

  constructor() { }

  ngOnInit() {
  }

  updateData(event) {
   
    let data = event;
    if (event.sortOrder === -1) {
      data.field = event.sortField ? "-" + event.sortField : null;
    } else {
      data.field = event.sortField ? event.sortField : null;
    }
    data.page = event.first/event.rows + 1;
    data.limit = event.rows;
   
    this.updateTableChanges.emit(data);
  }

}
