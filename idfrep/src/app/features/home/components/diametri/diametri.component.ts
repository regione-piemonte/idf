/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges,Input } from '@angular/core';

@Component({
  selector: 'app-diametri',
  templateUrl: './diametri.component.html',
  styleUrls: ['./diametri.component.css']
})
export class DiametriComponent implements OnInit, OnChanges{
  @Input() public reportData: any;
  diametriData:any;
  diametriData2:any;
  infoRelascopia:any
  formatter:any;

  options:any = {
    groupingSymbol:".",
    legend: 'none',
    hAxis: {
      title: 'diametro (cm) ',
      format: ''
    },
    vAxis: {
      title: 'numero alberi'
      ,format: ''
    }
  }; 

  constructor() { }

  ngOnInit() {
    this.formatter = new Intl.NumberFormat('it-IT');
    this.updateData();
  }

  ngOnChanges() {
    console.log('ngOnChanges');
    this.updateData();
  }

  updateData(){
    if(this.reportData != null){
      this.infoRelascopia = this.reportData.infoRelascopia;
      this.diametriData2=[];
      let label;
      //this.options.vAxis['ticks'] = this. getTicks(this.reportData.diametriData.values);
      for(var i in this.reportData.diametriData.labels){
        label = this.formatter.format(this.reportData.diametriData.values[i]);
        // this.chartData2.push([row['Classi diametriche'],{v:Number(value),f:row[selectedSpecie]}]);
        //label = this.reportData.diametriData.values[i]+ '';
        this.diametriData2.push([this.reportData.diametriData.labels[i],{ v:this.reportData.diametriData.values[i], f:label}])
      }
    }
  }

  getTicks(vet){
    let retVet = [];
    for(let i in vet){
      retVet.push({v:vet[i],f:this.formatter.format(vet[i])})
    }
    return retVet;
  }
}
