/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges, Input} from '@angular/core';
import { DownloadService } from '../../services/download.service';

@Component({
  selector: 'app-table-row-merge',
  templateUrl: './table-row-merge.component.html',
  styleUrls: ['./table-row-merge.component.css']
})
export class TableRowMergeComponent implements OnInit, OnChanges {

  @Input() public data:any;
  @Input() public title:string;
  firstCellVal:string = null;

  headers:string[];

  constructor(private downloadService: DownloadService) { }

  ngOnInit() {
    this.headers = this.getColumns();
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
        tableHeaders.push(key);
      }
    }
    return tableHeaders;
  }
  
  donwloadExcel(){
    let params = {
      title:this.title,
      dataCsv:this.downloadService.getCsv(this.headers,this.data)
    };
    this.downloadService.downloadExcel(params);
  }

  showCell(rowData){
    if(this.firstCellVal && this.firstCellVal == rowData[this.headers[0]]){
      return false;
    }
    this.firstCellVal = rowData[this.headers[0]];
    return true;
  }

  setRowSpan(i){
    if(i==0){
      return 'rowspan="2"'
    }
    return '';
  }

  getRowspan(value){
    let count = 0;
    for(let i in this.data){
      if(this.data[i][this.headers[0]] == value){
        count++;
      }
    }
    return count;
  }
}
