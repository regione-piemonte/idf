/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { TableHeader } from '../../models/table-header';

@Component({
  selector: 'app-list-of-geometries',
  templateUrl: './list-of-geometries.component.html',
  styleUrls: ['./list-of-geometries.component.css']
})
export class ListOfGeometriesComponent implements OnInit, OnChanges {

  @Input() tableData: any[];

  tableHeaders: TableHeader[] = [
    { field: "tipoGeometria", header: "Tipo"},
    { field: "descrizione", header: "Descrizione"},
    { field: "geometryInfo", header: "Info geometria"},
    { field: "particelleForet", header: "N. particella forestale"}
    // { field: "puntoCoord", header: "Coordinate punto",},
    // { field: "lunghezzaLinea", header: "Lunghezza linea (m)",},
    // { field: "superficiePoligon", header: "Superficie poligono (ha)"}
  ]

  supTotIntervento:number

  constructor() { }

  ngOnInit() {
    this.fillSupTotIntervento();
  }

  ngOnChanges() {
    this.fillSupTotIntervento();
  }

  formatNumber(value){    
    return value.toFixed(2).replace('.',',');
  }

  fillSupTotIntervento(){
    this.supTotIntervento = 0;
    if(this.tableData && this.tableData.length > 0){
      for(let i in this.tableData){
        let item = this.tableData[i];
        if(item.superficiePoligon && item.superficiePoligon.length > 0){
          try{
            this.supTotIntervento+=Number(item.superficiePoligon.replace(',','.'))
          }catch(e){
            console.log('error on ')
          }
        }
      }
    }
  }

}
