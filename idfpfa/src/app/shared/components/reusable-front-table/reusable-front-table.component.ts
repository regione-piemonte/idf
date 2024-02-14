/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { TableHeader } from "../../models/table-header";

@Component({
  selector: "app-reusable-front-table",
  templateUrl: "./reusable-front-table.component.html",
  styleUrls: ["./reusable-front-table.component.css"]
})
export class ReusableFrontTableComponent implements OnInit {
  @Input() headers: TableHeader[];
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() view: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean;
  @Input() fieldToEdit: string;
  @Input() fieldToDownload: string;
  @Input() fieldToDelete: string;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteRow: EventEmitter<any> = new EventEmitter();
  @Output() downloadEmitter: EventEmitter<string> = new EventEmitter();
  @Input() simpleTable?: boolean;
  constructor() {}

  ngOnInit() {}

  viewRow(rowIndex) {}

  editRow(index) {
    this.editRowEmitter.emit(index);
  }

  deleteRowFromParcels(rowIndex, index) {
    this.deleteRow.emit({ rowIndex, index });
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
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
