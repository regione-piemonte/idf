/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component,Input, OnInit, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-google-chart',
  templateUrl: './google-chart.component.html',
  styleUrls: ['./google-chart.component.css']
})
export class GoogleChartComponent implements OnInit {
  @Input() public title:any;
  @Input() public id:any;
  @Input() public type:any;
  @Input() public data:any;
  @Input() public columnNames:any;
  @Input() public options:any;
  @Input() public width:any;
  @Input() public height:any;
  
  constructor() { }

  ngOnInit() {
    console.log("GoogleChartComponent data:");
    console.log(this.data);
  }

  ngAfterViewInit(){
    console.log(this.data);
 }

  printPage() {
    var elem = document.getElementById(this.id);
    var windowContent = '<!DOCTYPE html>';
    windowContent += '<html>'
    windowContent += '<head><title>Print chart</title></head>';
    windowContent += '<body>'
    windowContent += elem.innerHTML;
    windowContent += '</body>';
    windowContent += '</html>';
    var printWin = window.open('','','width=800,height=600');
    printWin.document.open();
    printWin.document.write(windowContent);
    printWin.document.close();
				setTimeout(function(){ 
          printWin.focus();
          printWin.print();
          printWin.close(); }, 100); 
  }

}
