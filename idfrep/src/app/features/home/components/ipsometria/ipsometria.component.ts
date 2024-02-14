/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component,EventEmitter, OnInit, OnChanges,Input,Output } from '@angular/core';

@Component({
  selector: 'app-ipsometria',
  templateUrl: './ipsometria.component.html',
  styleUrls: ['./ipsometria.component.css']
})
export class IpsometriaComponent implements OnInit, OnChanges {
  @Input() public reportData: any;
  @Output()  filter = new EventEmitter();
  @Output() filtersData:any;
  ipsometriaData:any;
  formulaRetta:any;
  ipsometriaDataInfo1:any;
  ipsometriaDataInfo2:any;
  pointsNormal:any;
  pointsLineNormal:any;
  pointsInterpolation:any;
  pointsLineInterpolation:any;
  ipsometriaInterpolaz:any;
  formError:any;

  options:any={
    legend: {
      display: false
    },
    tooltips: {
      callbacks: {
          label: function(tooltipItem, data) {
              return tooltipItem.label + ' ; ' + tooltipItem.value.replace('.',',') ;
          }
      },
      bodyFontSize: 16,
    },
    scales: {
        xAxes: [{
            type: 'linear',
            position: 'bottom',
            ticks: {
              //beginAtZero:true,
              callback: function(value, index, values) {
                  return  ("" + value).replace('.',',');
              }
            } 
        },{
          display: true,
          scaleLabel: {
            display: true,
            labelString: 'diametro (cm)'
          }
        }],
        yAxes: [{
          display: true,
          scaleLabel: {
            display: true,
            labelString: 'altezza (m)'
          },
          ticks: {
            //beginAtZero:true,
            callback: function(value, index, values) {
                return  ("" + value).replace('.',',');
            }
          }
        }]
    }
};

  constructor() { }


  executeFilter() {
    this.filter.emit();
  }

  ngOnChanges() {
    this.updateData();
  }

  ngOnInit() {
    this.updateData();
  }

  updateData(){

    if(this.reportData != null){
      console.log('update ipsometria');
      console.log(this.reportData.filters);
      this.filtersData = this.reportData.filters;
      this.ipsometriaData = this.reportData.ipsometiraData
      this.pointsNormal = this.ipsometriaData.pointsNormal;
      this.formError = this.ipsometriaData.error;
      this.pointsLineNormal = this.ipsometriaData.pointsLineNormal;
      this.pointsInterpolation = this.ipsometriaData.pointsInterpolation;
      this.pointsLineInterpolation = this.ipsometriaData.pointsLineInterpolation;
      this.ipsometriaDataInfo1 = this.reportData.infoWorkingData.Table1;
      this.ipsometriaDataInfo2 = this.reportData.infoWorkingData.Table2;
      this.formulaRetta = 'altezza=' + this.ipsometriaData.m + '*ln(diametro)'
        + (this.ipsometriaData.q.indexOf('-') == 0?'':'+') + this.ipsometriaData.q + 
      ';    R<sup>2</sup>=' + this.ipsometriaData.r2;
    }
  }
}
