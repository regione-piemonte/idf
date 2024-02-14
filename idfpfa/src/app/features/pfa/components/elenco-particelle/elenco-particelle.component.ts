/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";
import { FormBuilder } from "@angular/forms";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { PfaConfigComponent } from "../../pfa-config";
import { TableHeader } from "src/app/shared/models/table-header";
import { Observable } from "rxjs";
import { PropAndRicandeza } from "src/app/shared/models/dettaglio-intervento";

@Component({
  selector: "app-elenco-particelle",
  templateUrl: "./elenco-particelle.component.html",
  styleUrls: ["./elenco-particelle.component.css"]
})
export class ElencoParticelleComponent implements OnInit {
  ricadeHeader: TableHeader[];
  @Input() propAndRicandeza$: Observable<PropAndRicandeza>;

 
  selectedRow: number;

  constructor(private pfaService: PfaSampleService) {}

  ngOnInit() {
    this.ricadeHeader = PfaConfigComponent.getRicadeHeader();
  }

  getFieldId(index: number) {
    this.selectedRow = index;
  }

  getRicadenza(item){
    let val = item.nome;
    if(item.percentualeRicadenza){
      val+=' ' + this.formatTwoDecimal(item.percentualeRicadenza) + '%';
    }
    return val+'; ';
  }

  formatTwoDecimal(value){
    if(typeof value == 'number' && (value+'').indexOf('.') > 0){
      return value.toFixed(2).replace('.',',');
    }
    return value;
  }
  
  formatNumber(value){
    if(typeof value == 'number'){
      return (value).toLocaleString('it-IT');
    }
    return value;
  }
}
