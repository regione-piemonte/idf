/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter, TemplateRef } from '@angular/core';
import { TableHeader } from '../../models/table-header';
import { SearchFormComponent } from 'src/app/features/forestale/pages/search-form/search-form.component';
import { AreaDiSaggio } from '../../models/area-di-saggio';


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
  @Input() currentPage = 1;
  @Input() pageSize: number = 25;  
  @Input() totalSize: number; 
  @Input() sortOrder: number = 1;  
  @Output() rowObjectEmitter:EventEmitter<AreaDiSaggio> = new EventEmitter();
  @Output() buttonPressed: EventEmitter<AreaDiSaggio> = new EventEmitter();
  
  @Input() buttonsTempRef: TemplateRef<any>;

  icon:boolean;
  
  constructor(private search: SearchFormComponent) { }

  ngOnInit() { 
  }

  getFieldId(name: string, index: number, rowObject: AreaDiSaggio) {    
    this.selectedRow = index;
    this.rowIdEmitter.emit(name);
    this.rowObjectEmitter.emit(rowObject);
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
