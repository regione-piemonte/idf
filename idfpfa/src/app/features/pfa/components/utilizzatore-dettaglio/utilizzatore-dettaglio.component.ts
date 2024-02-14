/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { UtilizzatoreDetails } from 'src/app/shared/models/data-taglio';

@Component({
  selector: 'app-utilizzatore-dettaglio',
  templateUrl: './utilizzatore-dettaglio.component.html',
  styleUrls: ['./utilizzatore-dettaglio.component.css']
})
export class UtilizzatoreDettaglioComponent implements OnInit {

  @Input() utilizzatoreDettaglio$: Observable<UtilizzatoreDetails>;
  constructor() { }

  ngOnInit() {
  }

  getDescrizione(item){
    if(item){
      if(item.denominazione){
        return item.denominazione
      }else if(item.cognome && item.nome){
        return item.nome + " " + item.cognome;
      }
    }
    return '';
  }

}
