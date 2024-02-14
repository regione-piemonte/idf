/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, EventEmitter, Input, Output, OnInit, OnChanges } from '@angular/core';

@Component({
  selector: 'app-incrementi',
  templateUrl: './incrementi.component.html',
  styleUrls: ['./incrementi.component.css']
})
export class IncrementiComponent implements OnInit, OnChanges {
  @Output()  filter = new EventEmitter();
  @Input() public reportData: any;
  tableDati:any;
  tableCavallettati:any;
  pointsDiametriIncrementi:any;
  specieFilter:any;
  options:any = {
    hAxis: {
      title: 'DIAMETRI'
    },
    vAxis: {
      title: 'INCREMENTI'
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
    
    this.pointsDiametriIncrementi = this.reportData.listPointsCampione;
    console.log("pointsDiametriIncrementi");
    console.log(this.pointsDiametriIncrementi);
    this.tableDati = this.reportData.tableTotali;
    this.specieFilter = this.reportData.specieFilter;
    this.tableCavallettati = this.reportData.tableCavallettati;
  }

}
