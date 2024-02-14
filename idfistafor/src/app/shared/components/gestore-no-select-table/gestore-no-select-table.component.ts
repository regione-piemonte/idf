/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, EventEmitter, Output, OnChanges } from '@angular/core';
import { CONST } from '../../constants';
import { TableHeader } from '../../models/table-header';
import { UserModel } from '../../models/user.model';

@Component({
  selector: 'app-gestore-no-select-table',
  templateUrl: './gestore-no-select-table.component.html',
  styleUrls: ['./gestore-no-select-table.component.css']
})
export class GestoreNoSelectTableComponent implements OnInit, OnChanges {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() replaceTrueFalse: string[];
  @Input() view: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean
  @Input() fieldToEdit: string;
  @Input() fieldToDownload: string;
  @Input() fieldToDelete: string;
  @Output() updateTableChanges: EventEmitter<any> = new EventEmitter();
  @Output() editRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() viewRowEmitter: EventEmitter<any> = new EventEmitter();
  @Output() deleteRow: EventEmitter<any> = new EventEmitter();
  @Output() downloadEmitter: EventEmitter<string> = new EventEmitter();
  @Input() simpleTable?: boolean;
  @Input() totalSize: number;
  @Input() visualizaTable: boolean;
  @Input() isGestore: boolean;
  @Input() isSportello: boolean = false;

  constructor() { }

  user: UserModel;

  isConsultazione: boolean = false;

  ngOnInit() {
    // console.log(this.data);
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.setGrant();
  }

  ngOnChanges(){
    this.setGrant();
  }

  setGrant(){
    if(this.user){
      if(this.user.ruolo == '' + CONST.ROLE_CONSULTAZIONE){
        this.isConsultazione = true;
      }else if(this.user.ruolo > '2' && this.data && this.data.length>0 && this.data[0]['competenza']){
        this.isConsultazione = !((this.user.ruolo == '' + CONST.ROLE_COMUNE && this.data['competenza'] == 'Comunale')
        || (this.user.ruolo == '' + CONST.ROLE_UF_TERRIRORIALE && this.data['competenza'] == 'Regionale'))
      }
    }
  }

  getValues(car: any, col:any){
    if(this.getValue(car[col.field])){
      return this.getValue(car[col.field]).split(';');
    }
    return [];
  }

  getValue(val:any){
    if(this.replaceTrueFalse && this.replaceTrueFalse.length == 2){
      return val==true?this.replaceTrueFalse[0]:val==false?this.replaceTrueFalse[1]:val;
    }
    return val;
  }

  getBgColor(car: any, col:any){
    if (col.field == 'statoCauzione' && this.getValue(car) && this.getValue(car).indexOf('MANCANTE') > -1){
      return 'red';
    }
    if (col.field == 'varianteProroga' && (this.getValue(car[col.field]) === 'VARIANTE'
      || this.getValue(car[col.field]) === 'PROROGA' || this.getValue(car[col.field]) === 'SI')){
      return 'yellow';
    }
    if (col.field == 'rimboschimento' && this.getValue(car[col.field]) === 'Compensazione fisica - senza istanze di taglio'){
      return 'yellow';
    }
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

  viewRow(rowIndex) {
    this.viewRowEmitter.emit(rowIndex);
  }

  editRow(index) {
    this.editRowEmitter.emit(index);
  }

  deleteRowFromParcels(rowIndex) {
    this.deleteRow.emit(rowIndex);
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
  }

  getDescr(elem:any){
    if (this.headers && this.headers.length) {
      let label = this.headers[0].header;
      let key = this.headers[0].field;
      return label.toLocaleLowerCase() + " " + elem[key];
    }
  }
}
