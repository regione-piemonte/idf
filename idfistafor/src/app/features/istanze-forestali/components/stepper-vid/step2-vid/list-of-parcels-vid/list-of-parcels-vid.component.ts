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
  EventEmitter,
  Output,
} from "@angular/core";
import {
  FormGroup,
  FormControl,
  Validators,
  FormBuilder,
  FormArray,
} from "@angular/forms";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";

import { ForestaliService } from "src/app/features/istanze-forestali/services/forestali.service";
import { TableHeader } from "src/app/shared/models/table-header";
import { ShowParcel } from "src/app/shared/models/particle-cadastral";
import { IdfistaforHeaderHelper } from "src/app/features/istanze-forestali/idfistafor-header-helper";

@Component({
  selector: "list-of-parcels-vid",
  templateUrl: "./list-of-parcels-vid.component.html",
  styleUrls: ["./list-of-parcels-vid.component.css"],
})
export class ListOfParcelsVidComponent implements OnInit, OnDestroy {
  unsubscribe$ = new Subject<void>();
  @Input() tableData: ShowParcel[];
  @Input() polygonForm: FormGroup;
  @Input() enabledGeneraPoligono: boolean;
  selectedRow: number;
  sortedColumn = "comune";
  @Output() deleteRowEmitter = new EventEmitter<number>();
  @Output() openGeecoEmitter = new EventEmitter<number>();

  tableHeaders: TableHeader[] =
    IdfistaforHeaderHelper.getParticleCadastralHeader();

  constructor(
    private forestaliService: ForestaliService,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    /*  this.forestaliService
    .getElencoParticelleCatastaliData()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.initForm();
        res.forEach((item, index) => {
          this.searhFormArray = this.searchForParcelsForm.get('searchParcel') as FormArray;
          const localForm: FormGroup = this.fb.group({
            comune: [ { value: '', disabled: true} ],
            sezione: [ { value: '', disabled: true} ],
            foglio: [ { value: '', disabled: true} ],
            particella: [ { value: '', disabled: true} ],
            catastale: [ { value: '', disabled: true} ]
          });
          localForm.patchValue(item);
          this.searhFormArray.push(localForm);
        });
      }); */
      this.tableData.forEach((item,index) =>{
        if(item.supCatastale.toString().includes(".")){
          item.supCatastale = item.supCatastale.toString().replace('.',',')
        }
      })
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  /* deleteFiledValue(index: number) {
    (this.parcelsForm.get('parcelArray') as FormArray).removeAt(index);
  } */

  /* private initForm() {
    this.searchForParcelsForm = this.fb.group({
      searchParcel: this.fb.array([this.fillSearchForm])
    });
  }

  get fillSearchForm() {
    this.searchForm = this.fb.group({
      comune: [{ value: 'comune', disabled: true }, Validators.required],
      sezione: [{ value: 'sezione', disabled: true }, Validators.required],
      foglio: [{ value: 'foglio', disabled: true }, Validators.required],
      particella: [{ value: 'particella', disabled: true }, Validators.required],
      catastale: [{ value: 'catastale', disabled: true }, Validators.required]
    });
    return this.searchForm;
  } */

  getRowId(event) {}
  getUpdatedTable(event) {}

  deleteRow(event) {
    // this.tableData.splice(event.index, 1);
    this.deleteRowEmitter.emit(event);
  }

  createPolygon() {
    console.log(
      ">>> createPolygon en Idrogeologico: ",
      this.enabledGeneraPoligono
    );
    if (this.enabledGeneraPoligono) {
      //this.forestaliService.emitPolygon();
      this.openGeecoEmitter.emit();
    }
  }
}
