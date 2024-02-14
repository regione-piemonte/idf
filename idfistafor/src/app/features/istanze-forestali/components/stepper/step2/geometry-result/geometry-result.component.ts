/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { Subject } from 'rxjs';
import { FormGroup, Validators, FormBuilder, FormArray } from '@angular/forms';
import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-geometry-result',
  templateUrl: './geometry-result.component.html',
  styleUrls: ['./geometry-result.component.css']
})
export class GeometryResultComponent implements OnInit, OnDestroy {
  /* searchForm: FormGroup; */
  unsubscribe$ = new Subject<void>();
  display = false;
  fields = ['catastale', 'trasformazione', 'areeProtette', 'reteNatura', 'popolamentiDaSeme', 'categorieForestali', 'vincoloIdrogeologico'];

  @Input() polygonForm: FormGroup;

  constructor(private forestaliService: ForestaliService, private fb: FormBuilder) { }

  ngOnInit() {
    /* this.initForm(); */
    //console.log('POLY FORM', this.polygonForm.getRawValue());

   /*  this.forestaliService
    .getElencoParticelleCatastaliData2()
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res) => {
        this.initForm();
        this.polygonForm.patchValue(res);
        res.categorieForestali.forEach((item) => {
          const localFormGroup = this.fb.group({
            id: item.id,
            name: item.name
          });
          (this.polygonForm.get('categorieForestali') as FormArray)
          .push(localFormGroup);
        });
        (this.polygonForm.get('categorieForestali') as FormArray)
        .removeAt(0);
        // console.log(this.polygonForm.getRawValue());

        this.fields.forEach((items) => {

        });
        // this.polygonForm.patchValue(res);
        // this.items = ((this.polygonForm.get('categorieForestali').value) as string).split(';');
        // this.polygonForm.get('categorieForestali').value;
         }); */
  }
  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  /* private initForm() {
    this.polygonForm = this.fb.group({
      catastale: [{ value: 'catastale', disabled: true }, Validators.required],
      trasformazione: [{ value: 'trasformazione', disabled: true }, Validators.required],
      areeProtette: [{ value: 'areeProtette', disabled: true }, Validators.required],
      reteNatura: [{ value: 'reteNatura', disabled: true }, Validators.required],
      popolamentiDaSeme: [{ value: 'popolamentiDaSeme', disabled: true }, Validators.required],
      categorieForestali: this.fb.array([ this.createForm() ]), [{ value: 'categorieForestali', disabled: true }, Validators.required]
      vincoloIdrogeologico: [{ value: 'vincoloIdrogeologico', disabled: false }, Validators.required]
    });
    return this.polygonForm;
  } */

  /* createForm() {
    return this.fb.group({
      id: '1',
      name: 'asd'
    });
  } */
  getAddEntry(event) {
    this.display = event.display;

    if (event.formGroup) {
      (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).value.forEach((element, index) => {
        (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).removeAt(
          (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).length - 1);
      });

      (event.formGroup.get('ricadenzaCategorieForestali') as FormArray).controls.forEach((element, index) => {
        (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).push(element);
      });
    }


    /* (this.polygonForm.get('categorieForestali') as FormArray).patchValue(event.formGroup.get('categorieForestali').value);
    (this.polygonForm.get('categorieForestali') as FormArray).patchValue([{'id': 8, 'name': 'asd'}]); */

    /* (this.polygonForm).patchValue(event.formGroup); */
    /* console.log(this.polygonForm.get('categorieForestali') as FormArray); */
    /* (this.polygonForm.get('categorieForestali') as FormArray).patchValue(event.formGroup); */

  }
}
