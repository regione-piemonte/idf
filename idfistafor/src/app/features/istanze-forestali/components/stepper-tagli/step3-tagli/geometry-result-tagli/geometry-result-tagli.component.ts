/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  OnDestroy,
  Input,
  Output,
  EventEmitter,
  SimpleChanges,
} from "@angular/core";
import { Subject } from "rxjs";
import { FormGroup, Validators, FormBuilder, FormArray } from "@angular/forms";
import { TagliService } from "src/app/features/istanze-forestali/services/tagli.service";
import { CategoriaForestale } from "src/app/shared/models/categoria-forestale.model";
import { TableHeader } from "src/app/shared/models/table-header";
import { RicadenzaIntervento } from "src/app/shared/models/ricadenzaIntervento.model";

@Component({
  selector: "geometry-result-tagli",
  templateUrl: "./geometry-result-tagli.component.html",
  styleUrls: ["./geometry-result-tagli.component.css"],
})
export class GeometryResultTagliComponent implements OnInit, OnDestroy {
  unsubscribe$ = new Subject<void>();
  categorieForestali = [];
  display = false;
  catSelected: CategoriaForestale = null;
  valorizzata: boolean = false;

  //Tabella ricadenze
  tableData: RicadenzaIntervento[] = [];
  @Input() tableDataInput: RicadenzaIntervento[];

  tableHeaders: TableHeader[] = [
    { field: "tipoVincolo", header: "Tipo Vincolo" },
    { field: "nomeVincolo", header: "Nome vincolo" },
    { field: "provvedimento", header: "Provvedimento" },
    { field: "percentuale", header: "% di ricadenza" },
  ];

  paging: any;
  totalCount: number;
  sortField: any;

  @Input() polygonForm: FormGroup;

  constructor(private tagliService: TagliService, private fb: FormBuilder) {}

  ngOnInit() {
    this.tagliService.getCategorieForestali().subscribe((results) => {
      this.categorieForestali = results;
    });
    console.log("=== geo queryu resul === ", this.polygonForm.get(
      'ricadenzaPortaSeme'
    )['controls']);
    /*polygonForm.get(
      'ricadenzaPortaSeme'
    )['controls']*/
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

  getUpdatedTable($event) {}

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  remove(event: any, index: number) {
    (this.polygonForm.get("ricadenzaCategorieForestali") as FormArray).removeAt(
      index
    );
  }

  onCategoryChange(el: any) {
    this.catSelected = el;
  }

  showAdd() {
    this.display = true;
  }

  cancelAddRicadenza() {
    this.display = false;
  }

  addElement(event) {
    let element = this.categoriaForestaleElement(this.catSelected);
    let exist = (
      this.polygonForm.get("ricadenzaCategorieForestali") as FormArray
    ).controls.find(
      (c) =>
        c.get("codiceAmministrativo").value ===
        this.catSelected.codiceAmministrativo
    );

    if (!exist) {
      (this.polygonForm.get("ricadenzaCategorieForestali") as FormArray).push(
        element
      );
    }
    this.display = false;
    this.catSelected = null;
  }

  categoriaForestaleElement(item) {
    return this.fb.group({
      codiceAmministrativo: [item ? item.codiceAmministrativo : ""],
      nome: [item ? item.descrizione : ""],
      percentualeRicadenza: 0,
    });
  }
}
