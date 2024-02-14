/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  @Input() public data:any;
  @Input() public title:string;
  @Input() public type:string;
  @Input() public id:string;

  constructor() { }

  ngOnInit() {
    console.log('init app-chart');
  }


  printPage() {
    var elem = document.getElementById(this.id);
    var canvas= elem.getElementsByTagName("canvas")[0].toDataURL();
    console.log(canvas);
    var windowContent = '<!DOCTYPE html>';
    windowContent += '<html>'
    windowContent += '<head><title>Print canvas</title></head>';
    windowContent += '<body>'
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

}
