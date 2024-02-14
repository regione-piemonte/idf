/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableHeader } from '../../models/table-header';


@Component({
  selector: 'app-reusable-table',
  templateUrl: './reusable-table.component.html',
  styleUrls: ['./reusable-table.component.css']
})
export class ReusableTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  selectedRow: number;
  @Output() rowIdEmitter: EventEmitter<any> = new EventEmitter();
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Input() initiallySortedColumn: string;
  @Input() selectableId: string;

  constructor() { }

  ngOnInit() {
  }

  getFieldId(name: string, index: number) {
    this.selectedRow = index;
    this.rowIdEmitter.emit(name);
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
