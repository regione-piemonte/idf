/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { TableHeader } from "../../models/table-header";


var $: any;

@Component({
  selector: "app-re-table",
  templateUrl: "./re-table.component.html",
  styleUrls: ["./re-table.component.css"],
})


export class ReTableComponent implements OnInit {
  @Input() values: any[];
  @Input() headers: TableHeader[];
  @Input() selectableField: string;
  @Input() idComune: string;
  @Input() idPopolamento: string;
  @Input() totalItems: number;
  selectedRow: number;
  @Output() rowIdEmiter: EventEmitter<any> = new EventEmitter();
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();


  constructor() {}

  ngOnInit() {
    
    // $("th").ready(function(){
    //   $('[data-toggle="tooltip"]').tooltip();   
    // });
  }
  

  getFieldId(rowData: any, index: number) {
    this.selectedRow = index;
    this.rowIdEmiter.emit(rowData);
  }

  updateData(event) {
    let data = event;

    if (event.sortOrder === -1) {
      data.field = event.sortField ? "-" + event.sortField : null;
    } else {
      data.field = event.sortField ? event.sortField : null;
    }
    data.page = event.first / event.rows + 1;
    data.limit = event.rows;
    this.updateTableChanges.emit(data);
  }

  getValue(value){
    if(typeof value == 'number'){
      return (value).toLocaleString('it-IT');
    }else if(Array.isArray(value)){
      return value.toString().replace(/,/g,', ');
    }else{
      return value;
    }
  }

  getDescr(elem:any){
    let label = this.headers[0].header;
    let key = this.headers[0].field;
    return label.toLocaleLowerCase() + " " + elem[key];
  }
 
}
