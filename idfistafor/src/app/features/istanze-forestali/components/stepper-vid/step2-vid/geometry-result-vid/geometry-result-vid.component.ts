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
  selector: 'geometry-result-vid',
  templateUrl: './geometry-result-vid.component.html',
  styleUrls: ['./geometry-result-vid.component.css']
})
export class GeometryResultVidComponent implements OnInit, OnDestroy {
  /* searchForm: FormGroup; */
  unsubscribe$ = new Subject<void>();
  display = false;
  fields = ['catastale',
  'trasformazione', 'boscata', 'nonBoscata',
  'inVincolo', 'boscataInVincolo', 'nonBoscataInVincolo',
  'areeProtette', 'reteNatura', 'popolamentiDaSeme', 'categorieForestali', 'vincoloIdrogeologico'];

  @Input() polygonForm: FormGroup;

  constructor(private forestaliService: ForestaliService, private fb: FormBuilder) { }

  ngOnInit() {}

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
  
  getAddEntry(event) {
    this.display = event.display;

    if(event.formGroup) {
      (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).value.forEach((element, index) => {
        (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).removeAt(
          (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).length - 1);
      });

      (event.formGroup.get('ricadenzaCategorieForestali') as FormArray).controls.forEach((element, index) => {
        (this.polygonForm.get('ricadenzaCategorieForestali') as FormArray).push(element);
      });
    }
  }
}