/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnChanges,Input } from '@angular/core';
import * as google from 'angular-google-charts'

@Component({
  selector: 'app-volumi',
  templateUrl: './volumi.component.html',
  styleUrls: ['./volumi.component.css']
})
export class VolumiComponent implements OnInit, OnChanges {
  @Input() public reportData: any;
  @Input() adsType:any;
  totaliData:any;
  classiDiametrichePercentuali: any;
  classiDiametricheM3: any;
  specieM3Ha: any;
  specieList:any;
  chartData2:any;
  chartDataM3Ha:any;

  volumiPerSpecie:any;
  graficoVolumiPerSpecie:any

  optionsSpecie:any = {
    legend: 'none',
    hAxis: {
      title: 'classi diametriche',
      format: ''
    },
    vAxis: {
      title: 'm³',
      format: ''
    }
  };

  optionsSpecieDiametriche:any = {
    legend: 'none',
    hAxis: {
      title: 'Specie',
      format: ''
    },
    vAxis: {
      title: 'm³',
      format: ''
    }
  };

  selectedSpecie:any;

  constructor() { }

  ngOnChanges() {
    this.updateData();
  }

  ngOnInit() {
    this.updateData();
    
  }

  updateData(){ 
    if(this.reportData != null){
      this.volumiPerSpecie = this.reportData.volumiPerSpecie;
      this.graficoVolumiPerSpecie = this.getDataChartRelascopica(this.reportData.graficoVolumiPerSpecie);
      this.classiDiametrichePercentuali = this.reportData.classiDiametrichePercentuali;
      this.classiDiametricheM3 = this.reportData.classiDiametricheM3;
      this.specieList = this.reportData.specieList;;
      if(this.adsType > 0 && this.reportData.classiDiametrichePercentuali){
        this.classiDiametrichePercentuali = this.reportData.classiDiametrichePercentuali.sort(this.sortByClassiDiametriche);
        this.classiDiametricheM3 = this.reportData.classiDiametricheM3.sort(this.sortByClassiDiametriche);
        this.specieList = this.reportData.specieList;
      }
      // else if(this.adsType == 3){
      //   this.specieM3Ha= this.reportData.specieM3Ha;
      //   this.drowChartSpecieM3Ha();
      // }
    }
  }

  sortByClassiDiametriche(a,b){
    if ( a['Classi diametriche'] < b['Classi diametriche'] ){
      return -1;
    }else{
      return 1;
    }
  }

  drowChartSpecieM3Ha(){
    //chartDataM3Ha
    let row;
    let value;
    for(let i in this.classiDiametricheM3){
      row = this.specieM3Ha[i];
      // value = row[selectedSpecie].replace('.','').replace(',','.');
      // this.chartData2.push([row['Classi diametriche'],{v:Number(value),f:row[selectedSpecie]}]);
    }
  }

  drowChartSpecie(selectedSpecie){
    this.selectedSpecie = selectedSpecie;
    //let selectedSpecie = (<HTMLInputElement>document.getElementById('incrementiSpecieFilter')).value;
    if(selectedSpecie == ''){
      this.chartData2 = null;
    }else{
      selectedSpecie = selectedSpecie.split(" - ")[0];
      let value;
      let row;
      this.chartData2=[];
      for(let i in this.classiDiametricheM3){
        row = this.classiDiametricheM3[i];
        if(row['Classi diametriche'] != 'Totale'){
          value = row[selectedSpecie].replace('.','').replace(',','.');
          this.chartData2.push([row['Classi diametriche'],{v:Number(value),f:row[selectedSpecie]}]);
        }
      }
    }
  }

  getDataChartRelascopica(data){
    if(data){
      let value;
      let row;
      let chartData=[];
      let labels = data.labels;
      let values = data.values;
      for(let i in labels){
        value = values[i].replace('.','').replace(',','.');
        chartData.push([labels[i],{v:Number(value),f:values[i]}]);
      }
      return chartData;
    }else{
      return null;
    }
  }
}
