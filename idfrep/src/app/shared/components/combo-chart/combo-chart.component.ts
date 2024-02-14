/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, Input, OnInit, OnChanges, AfterViewInit } from '@angular/core';
import * as Chart from 'chart.js';

@Component({
  selector: 'app-combo-chart',
  templateUrl: './combo-chart.component.html',
  styleUrls: ['./combo-chart.component.css']
})
export class ComboChartComponent implements OnInit,AfterViewInit, OnChanges {

  @Input() public title:string;  
  @Input() public description:string;
  @Input() public id:string;
  @Input() public mainType:any;
  @Input() public labels:any;
  @Input() public label1:any;
  @Input() public data1:any;
  @Input() public borderColor1:any;
  @Input() public backgroundColor1:any;
  @Input() public secondType:any;
  @Input() public label2:any;
  @Input() public data2:any;
  @Input() public borderColor2:any;
  @Input() public backgroundColor2:any;
  @Input() public options:any={
                                scales: {
                                    xAxes: [{
                                        type: 'linear',
                                        position: 'bottom'
                                    }]
                                }
                            };
  @Input() public width:any;
  @Input() public height:any;

  chart:any;

  constructor() { }

  ngOnInit() {}

  ngOnChanges() {
    let canvas = <HTMLCanvasElement> document.getElementById(this.id);
    if(canvas){
      canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
      console.log('clear context');
      this.drawCart();
    }
  }

   ngAfterViewInit(){
      this.drawCart();
   }

   drawCart(){
    console.log('drawCartTest -> id: ' + this.id);
    if(this.chart){
      this.chart.destroy();
    }
    this.chart = new Chart(document.getElementById(this.id), {
      type: this.mainType,
      data: {
        datasets: [{ 
            //label: this.label1,
            data: this.getPointsXY(this.data1),
     borderColor: this.borderColor1,
     backgroundColor:this.backgroundColor1
       }, {
        //label: this.label2,
        data: this.getPointsXY(this.data2),
        type: this.secondType,
        fill: false,
        borderColor: this.borderColor2,
        backgroundColor:this.backgroundColor2
       }]
      },
      options: this.options
  });
   }

   printPage() {
    var elem = <HTMLCanvasElement> document.getElementById(this.id);
    var canvas= elem.toDataURL();
    console.log(canvas);
    var windowContent = '<!DOCTYPE html>';
    windowContent += '<html>'
    windowContent += '<head><title>Curva ipsometrica</title></head>';
    windowContent += '<body>'
    windowContent += '<div><span style="background-color: yellowgreen;">'+ this.description + '</span></div>'
    windowContent += '<img src="' + canvas + '">';
    windowContent += '</body>';
    windowContent += '</html>';
    var printWin = window.open('','','width=800,height=600');
    printWin.document.open();
    printWin.document.write(windowContent);
    printWin.document.close();
				setTimeout(function(){ 
          printWin.focus();
          printWin.print();
          printWin.close(); }, 500); 
  }

  getPointsXY(data){
    let map = {};
    let xy = [];
    let key;
    let elem;
    for(let i in data){
      elem = data[i];
      key = JSON.stringify(elem);
      if(map[key] == null){
        map[key] = true;
        xy.push({x:elem[0],y:elem[1]});
      }
    }
    return xy;
  }

  }
