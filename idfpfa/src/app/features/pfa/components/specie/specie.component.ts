/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaConfigComponent } from "../../pfa-config";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { Observable } from "rxjs";
import { SpeciesAndTaglio } from "src/app/shared/models/dettaglio-intervento";

@Component({
  selector: "app-specie",
  templateUrl: "./specie.component.html",
  styleUrls: ["./specie.component.css"]
})
export class SpecieComponent implements OnInit {
  specieHeader: TableHeader[];

  specieData: any;
  selectedRow: number;
  @Input() specieAndTaglio$: Observable<SpeciesAndTaglio>;
  constructor(private pfaService: PfaSampleService) {}

  ngOnInit() {
    this.specieHeader = PfaConfigComponent.getSpecieHeaders();
  }

  getFieldId(index: number) {
    this.selectedRow = index;
  }

  getPriorita(value){
    if(value == 'P'){
      return 'Principale';
    }else if(value == 'S'){
      return 'Secondario';
    }else if(value == 'A'){
      return 'Altro';
    }
    return '';
  }
}
