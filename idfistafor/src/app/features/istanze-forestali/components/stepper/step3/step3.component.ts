/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, EventEmitter, Output, Input } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { ForestaliService } from '../../../services/forestali.service';
import { CheckboxAndRadio } from 'src/app/shared/models/checkbox-and-radio';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { DialogButtons, ButtonType } from 'src/app/shared/error-dialog/error-dialog.component';

@Component({
  selector: 'app-step3',
  templateUrl: './step3.component.html',
  styleUrls: ['./step3.component.css']
})
export class Step3Component implements OnInit, OnDestroy {
  descriptionOfTheForestForm: FormGroup;
  @Input() editMode: number;
  @Input() thirdFormData: CheckboxAndRadio;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() nextStepEmitter = new EventEmitter<void>();
  unsubscribe$ = new Subject<void>();
  disableCategoriaForestale = false;
  invalid: boolean;

  constructor(
    private forestaliService: ForestaliService,
    private fb: FormBuilder,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.descriptionOfTheForestForm = this.createFormGroup();
    if ((this.descriptionOfTheForestForm.get('headings') as FormArray).length === 1 &&
      (this.descriptionOfTheForestForm.get('headings') as FormArray).value[0].id === null) {
        (this.descriptionOfTheForestForm.get('headings') as FormArray).removeAt(0);
      this.descriptionOfTheForestForm.patchValue(this.thirdFormData);
      this.thirdFormData.headings.forEach((element, index) => {
        const tempHeading: FormGroup = this.fb.group({
          id: element.id,
          name: element.name,
          visibility: element.visibility,
          type:
            element.name.substring(0, 1) === 'D' ||
            element.name.substring(0, 1) === 'E' ? 'checkbox' : 'radio',
          subheadings: this.fb.array([])
        });
        element.subheadings.forEach((subElement, subIndex) => {
          const tempSubHeading: FormGroup = this.fb.group({
            id: subElement.id,
            name: subElement.name,
            visibility: subElement.visibility,
            valore: subElement.valore,
            checked: subElement.checked
          });
          // this.disableCategoriaForestale = true je kada treba da se disejbluje ako ima bilo koji check sa becka
          if (element.id === 2 && subElement.checked) { this.disableCategoriaForestale = false; }
          (tempHeading.get('subheadings') as FormArray).push(tempSubHeading);
        });
        /* if (tempHeading.get('type').value === 'radio') {console.log('element', element);
        tempHeading.setValidators(Validators.required); } */
        (this.descriptionOfTheForestForm.get('headings') as FormArray).push(tempHeading);
      });
      this.checkValidation();
    }
  }

  checkValidation() {
    this.invalid = false;
    (this.descriptionOfTheForestForm.get('headings') as FormArray).controls.forEach((element, index) => {
      if (element.get('type').value == 'radio' || element.get('type').value == 'checkbox') {
        let mustChecked = false;
        (element.get('subheadings') as FormArray).controls.forEach((subElement, subIndex) => {
          mustChecked = mustChecked || subElement.get('checked').value;
        });
        this.invalid = this.invalid || !mustChecked;
      }
    });
  }

  /* buildForm() {
    this.descriptionOfTheForestForm = this.fb.group({

    });
  } */

  onChange(index, subIndex, event) {
    const headingArray = (this.descriptionOfTheForestForm.get('headings') as FormArray).controls[index];
    const subHeadingArray = (headingArray.get('subheadings') as FormArray).controls[subIndex];
    if (event.target.type === 'radio') {
      (headingArray.get('subheadings') as FormArray).controls.forEach(
        element => {
          element.get('checked').patchValue(false);
        }
      );
    }
    subHeadingArray.get('checked').patchValue(!subHeadingArray.get('checked').value);
    this.checkValidation();
  }

  createFormGroup() {
    return this.fb.group({
      headings: this.fb.array([this.createheadingArray()])
    });
  }
  createheadingArray() {
    return this.fb.group({
      id: null,
      name: '',
      visibility: '',
      type: '',
      subheadings: this.fb.array([this.createsubheadingsArray()])
    });
  }
  createsubheadingsArray() {
    return this.fb.group({
      id: '',
      name: '',
      visibility: '',
      valore: '',
      checked: ''
    });
  }

  save(nextStep?: boolean) {
    const thirdFormObject: CheckboxAndRadio = this.descriptionOfTheForestForm.getRawValue();

    thirdFormObject.headings.forEach(item => {
      delete item['type'];
    });
    this.forestaliService
      .postStep3(thirdFormObject, this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(response => {
        if (response.warning) {
        this.dialogService.showDialog(true, 'Attenzione! Variazione voce predefinita:'+'<p>'+`${response.warning}`+"</p>", 'AVVERTIMENTO', DialogButtons.OK, '', (buttonId: number): void => {
          if (buttonId === ButtonType.OK_BUTTON) {
            if (nextStep) {
              this.nextStepEmitter.emit();
            }
          }
        });
        }
        else {
          if (nextStep) {
            this.nextStepEmitter.emit();
          }
        }
      });
  }

  continue() {
    this.save(true);
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
