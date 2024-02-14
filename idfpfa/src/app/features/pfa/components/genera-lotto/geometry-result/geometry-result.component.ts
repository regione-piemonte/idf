/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnDestroy } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { Subject } from "rxjs";
import { FasciaAltimetrica } from "src/app/shared/models/fascia-altimetrica.model";
import { RicadenzaIntervento } from "src/app/shared/models/ricadenzaIntervento.model";
import { TableHeader } from "src/app/shared/models/table-header";

@Component({
  selector: "app-geometry-result",
  templateUrl: "./geometry-result.component.html",
  styleUrls: ["./geometry-result.component.css"]
})
export class GeometryResultComponent implements OnInit, OnDestroy {
  unsubscribe$ = new Subject<void>();
  display = false;
  fields = [
    "catastale",
    "trasformazione",
    "areeProtette",
    "reteNatura",
    "popolamentiDaSeme",
    "categorieForestali",
    "vincoloIdrogeologico"
  ];
  valorizzata: boolean = false;
  @Input() ricadenzeForm: FormGroup;
  @Input() fasceAltimetriche: FasciaAltimetrica[];
  tableData: RicadenzaIntervento[] = [];
  @Input() tableDataInput: RicadenzaIntervento[];

  tableHeaders: TableHeader[] = [
    { field: "tipoVincolo", header: "Tipo Vincolo" },
    { field: "nomeVincolo", header: "Nome vincolo" },
    { field: "provvedimento", header: "Provvedimento" },
    { field: "percentuale", header: "% di ricadenza" },
  ];
  constructor() {}

  ngOnInit() {

  }

  ngOnChanges() {
    if (this.tableHeaders.filter((a) => a.field)) {
      this.valorizzata = true;
      console.log(this.tableHeaders);
    }

    //RICEVE in input la tabella RicadenzaIntervento da step3-Tagli.component
    this.tableData = this.tableDataInput;
    console.log(this.tableData);
    console.log(this.tableDataInput);
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  edit() {
    this.display = true;
  }

  changeValueAnnotazioni(event) {
   localStorage.setItem("annotazioni",event.target.value) ;
  }
  getAddEntry(event) {
    this.display = event.display;

    if (event.formGroup) {
      (this.ricadenzeForm.get(
        "ricadenzaCategorieForestali"
      ) as FormArray).value.forEach((element, index) => {
        (this.ricadenzeForm.get(
          "ricadenzaCategorieForestali"
        ) as FormArray).removeAt(
          (this.ricadenzeForm.get("ricadenzaCategorieForestali") as FormArray)
            .length - 1
        );
      });

      (event.formGroup.get(
        "ricadenzaCategorieForestali"
      ) as FormArray).controls.forEach((element, index) => {
        (this.ricadenzeForm.get("ricadenzaCategorieForestali") as FormArray).push(
          element
        );
      });
    }
  }

  formatTwoDecimal(value){
    if(typeof value == 'number' && (value+'').indexOf('.') > 0){
      return value.toFixed(2).replace('.',',');
    }
    return value;
  }

  formatValue(value){
    if(typeof value == 'number'){
      return (value).toLocaleString('it-IT');
    }else{
      return value;
    }
  }
}
