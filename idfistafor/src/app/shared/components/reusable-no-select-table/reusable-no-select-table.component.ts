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
  styleUrls: ['./reusable-no-select-table.component.css']
})
export class ReusableNoSelectTableComponent implements OnInit {

  @Input() headers: TableHeader[];
  @Input() data: any[];
  @Input() initiallySortedColumn: string;
  @Input() replaceTrueFalse: string[];
  @Input() view: boolean;
  @Input() edit: boolean;
  @Input() delete: boolean;
  @Input() download: boolean;
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

  constructor() { }

  ngOnInit() {
  }

  getValue(val:any){
    if(this.replaceTrueFalse && this.replaceTrueFalse.length == 2){
      return val==true?this.replaceTrueFalse[0]:val==false?this.replaceTrueFalse[1]:val;
    }
    return val;
  }

  getValues(car: any, col:any){
    if(this.getValue(car[col.field])){
      return this.getValue(car[col.field]).split(';');
    }
    return [];
  }

  getBgColor(car: any, col:any){
    if (col.field == 'statoCauzione' && this.getValue(car) && this.getValue(car).indexOf('MANCANTE') > -1){
      return 'red';
    }
    if (col.field == 'varianteProroga' && (this.getValue(car[col.field]) === 'VARIANTE' 
      || this.getValue(car[col.field]) === 'PROROGA' || this.getValue(car[col.field]) === 'SI')){
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

  editRow(item) {
    let index = item[this.fieldToEdit];
    if(item.idStato && item.idStato > 1){
      this.viewRowEmitter.emit(index);
    }else{
      this.editRowEmitter.emit(index);
    }
  }

  deleteRowFromParcels(rowIndex) {
    this.deleteRow.emit(rowIndex);
  }

  downloadRow(index) {
    this.downloadEmitter.emit(index);
  }

  getDescr(elem:any){
    let label = this.headers[0].header;
    let key = this.headers[0].field;
    return label.toLocaleLowerCase() + " " + elem[key];
  }

  isDownloadEnabled(item){
    if(this.fieldToDownload == 'uidIndex' && !item.uidIndex){
      return false;
    }
    return true;
  }

  isEditAllowd(elem:any){
    let idTipoIstanza = elem['idTipoIstanza'];
    if(idTipoIstanza){
      if(idTipoIstanza == 1 ){// trasformazioni
        return 1 == elem['idStato'];
      }else if([2,3].indexOf(idTipoIstanza) > -1 ){// tagli
        return [1,2,3].indexOf(elem['idStato']) > -1;
      }else if([4,5].indexOf(idTipoIstanza) > -1 ){// vincolo
        return [1,3].indexOf(elem['idStato']) > -1;
      }
    }
    return false;
  }

  isViewAllowd(elem:any){
    let idTipoIstanza = elem['idTipoIstanza'];
    if(idTipoIstanza){
      if(idTipoIstanza == 1 ){// trasformazioni
        return 1 != elem['idStato'];
      }else if([2,3].indexOf(idTipoIstanza) > -1 ){// tagli
        return [4,5,6,7].indexOf(elem['idStato']) > -1;
      }else if([4,5].indexOf(idTipoIstanza) > -1 ){// vincolo
        return [2,4,5,6,7].indexOf(elem['idStato']) > -1;
      }
    }
    return false;
  }
}
