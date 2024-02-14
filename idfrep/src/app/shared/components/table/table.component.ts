/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { DownloadService } from "src/app/shared/services/download.service";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnChanges {

  @Input() public data:any;
  @Input() public title:string;

  headers:string[];
  
  listRowBold = ['Totale','Incremento corrente (m<sup>3</sup>/ha)','Volume (m<sup>3</sup>)'];

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

  isRowBold(rowName){
    return this.listRowBold.filter((elem)=>{
      return elem == rowName}).length > 0;
  }
}
