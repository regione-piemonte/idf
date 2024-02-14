/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { AlberiCampioneDominante } from 'src/app/shared/models/alberi-campione-dominante';

@Component({
  selector: 'app-alberi-campione',
  templateUrl: './alberi-campione.component.html',
  styleUrls: ['./alberi-campione.component.css']
})
export class AlberiCampioneComponent implements OnInit {

  @Input() campioneData: AlberiCampioneDominante[];
  gridData: AlberiCampioneDominante[];
  tipoCampione: string = 'CAV';
  test: any;

  constructor() { this.test={DOM :{}, CAM:{},  CS1:{}, CS2:{}}}

  ngOnInit() {
   
    this.showData();
    
  }

  showData(){
    this.gridData = this.campioneData.filter(item => item.codTipoCampione !== this.tipoCampione);
    this.test = this.gridData.reduce((acc, cur) => {
      acc[cur.codTipoCampione] = cur;
      return acc;
    },  {DOM :{}, CAM:{},  CS1:{}, CS2:{}});}}
