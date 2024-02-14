/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";
import { Dettaglio, Detail } from "../../models/dettaglio";
import { s } from "@angular/core/src/render3";

@Component({
  selector: "app-denominazione",
  templateUrl: "./denominazione.component.html",
  styleUrls: ["./denominazione.component.css"]
})
export class DenominazioneComponent implements OnInit {
  @Input() denominazione: Detail;
  constructor() {}

  ngOnInit() {
  }

  formatDate(date:string){
    if(date && date.length == 10){
      if(date.indexOf('-') == 4){
        let dateVet = date.split('-');
        return dateVet[2] + '-' + dateVet[1] + '-' + dateVet[0] ;
      }
    }
    return date;
  }

}
