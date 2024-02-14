/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-paginated-table',
  templateUrl: './paginated-table.component.html',
  styleUrls: ['./paginated-table.component.css']
})
export class PaginatedTableComponent implements OnInit {

  headers: TableHeader[];
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() replaceTrueFalse: string[];
  @Input() simpleTable?: boolean;
  @Input() title:string;
  paging: any;
  tableData: any[]=[];
  rowsPerPageOptions = [50, 100, 200];

  constructor() { }

  ngOnInit() {
    // this.headers = this.getColumns();
    // this.paging = {
    //   page: 1,
    //   limit: this.rowsPerPageOptions[0]
    // };
    // this.filterData(this.paging);
  }

  ngOnChanges() {
    this.headers = this.getColumns();
  }

  getColumns(){
    let tableHeaders = null;
    if(this.data.length > 0){
      tableHeaders = [];
      let row = this.data[0];
      for(let key in row){
        //tableHeaders.push(key);
        tableHeaders.push({field: key, header: key});
      }
    }
    return tableHeaders;
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
    this.filterData(data);
  }

  filterData(paging){
    this.tableData = [];
    let first = (paging.page - 1) * paging.limit;
    let last = (first + paging.limit) > this.data.length?this.data.length:first + paging.limit;
    for(let i=first; i < last; i++){
      this.tableData.push(this.data[i]);
    }
  }

}
