/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Pipe, PipeTransform } from '@angular/core';
import { ShootingMirrorModel } from '../models/shooting-mirror';
import { TableElements } from '../models/tableElements';
import { TableHeader } from '../models/table-header';

@Pipe({
  name: 'tableTransform'
})
export class TableTransformPipe implements PipeTransform {

  transform( header: TableHeader[], data: any[], args?: any): TableElements {
    let tableElements: TableElements = new TableElements();
    
    if(isHeaderObject(header[0])){
      header.forEach(header => {
      tableElements.header.push(header.header);
    });
    } else {
      throw new Error("It`s not TableHeader Object, there is no field property");
    }
      for (let index = 0; index < data.length; index++) {
        let count = 0;
        let newSM = {};
        header.forEach( header => {
          for(let props in data[0]){
            if(header.field === props){
              newSM[count] = data[index][props];
            }
          }
          count++;
        });
        tableElements.tableData.push(newSM);
      }
    return tableElements;
  }

}

const isShootingMirrorObject = (sm : any): sm is ShootingMirrorModel => !!sm.codParticellaFor;
const isHeaderObject = (h : any): h is TableHeader => !!h.field;