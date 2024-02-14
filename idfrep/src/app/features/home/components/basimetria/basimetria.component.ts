/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges, Input } from '@angular/core';

@Component({
  selector: 'app-basimetria',
  templateUrl: './basimetria.component.html',
  styleUrls: ['./basimetria.component.css']
})
export class BasimetriaComponent implements OnInit, OnChanges {
  @Input() public reportData: any;
  @Input() adsType: any;
  specieData:any;
  areaData:any;
  qualitaData:any;
  totaliData:any;
  specieHeaders:string[];
  areaHeaders:string[];
  qualitaHeaders:string[];
  totaliHeaders:string[];

  constructor() {
   }

  ngOnInit() {
    this.updateData();
  }

  ngOnChanges() {
    console.log('ngOnChanges');
    this.updateData();
  }

  getColumns(data){
    console.log("start getColumns");
    let tableHeaders = [];
    if(data.length > 0){
      let row = data[0];
      for(let key in row){
        tableHeaders.push(key);
      }
    }
    //console.log(tableHeaders);
    return tableHeaders;
  }

  updateData(){
    if(this.reportData != null){
      this.specieData = this.reportData.specie;
      this.areaData = this.reportData.areaSaggio;
      this.qualitaData = this.reportData.qualita;
      this.totaliData = this.reportData.totali;
      if(this.specieData){
        this.specieHeaders = this.getColumns(this.specieData);
      }
      if(this.areaData){
        this.areaHeaders = this.getColumns(this.areaData);
      }
      if(this.qualitaData){
        this.qualitaHeaders = this.getColumns(this.qualitaData);
      }
      if(this.totaliData){
        this.totaliHeaders = this.getColumns(this.totaliData);
      }
      
    }
  }

}
