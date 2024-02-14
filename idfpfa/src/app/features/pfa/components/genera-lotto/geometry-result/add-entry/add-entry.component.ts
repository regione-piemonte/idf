/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import {
  Component,
  OnInit,
  Input,
  Output,
  EventEmitter,
  OnDestroy
} from "@angular/core";
import {
  FormGroup,
  FormArray,
  FormControl,
  FormBuilder,
  AbstractControl
} from "@angular/forms";
import { Subject } from "rxjs";

@Component({
  selector: "app-add-entry",
  templateUrl: "./add-entry.component.html",
  styleUrls: ["./add-entry.component.css"]
})
export class AddEntryComponent implements OnInit, OnDestroy {
  @Input() display;
  @Output() emitAddEntry = new EventEmitter();
  @Input() searchForm: FormGroup;
  copySearchForm: FormGroup | FormArray | FormControl;
  addEntry = false;
  unsubscribe$ = new Subject<void>();
  items: string[];
  errorMessage = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.copySearchForm = this.copyFormControl(this.searchForm);
  }

  copyFormControl(control: AbstractControl) {
    if (control instanceof FormControl) {
      return new FormControl(control.value);
    } else if (control instanceof FormGroup) {
      const copy = new FormGroup({});
      Object.keys(control.getRawValue()).forEach(key => {
        copy.addControl(key, this.copyFormControl(control.controls[key]));
      });
      return copy;
    } else if (control instanceof FormArray) {
      const copy = new FormArray([]);
      control.controls.forEach(control => {
        copy.push(this.copyFormControl(control));
      });
      return copy;
    }
  }

  addEntryClick() {
    if (this.addEntry === true) {
      /* entryDataIntoCopyForm() */
    }
    this.addEntry = true;
    this.errorMessage = null;
  }

  onHide() {
    (this.copySearchForm.get("ricadenzaCategorieForestali") as FormArray)[
      "controls"
    ].splice(0);
    (this.searchForm.get(
      "ricadenzaCategorieForestali"
    ) as FormArray).controls.forEach((element, index) => {
      (this.copySearchForm.get(
        "ricadenzaCategorieForestali"
      ) as FormArray).push(element);
    });
    this.emitAddEntry.emit({ display: false });
    this.addEntry = false;
    this.errorMessage = null;
  }

  entryDataIntoCopyForm(event) {
    const localFormGroup = this.fb.group({
      codiceAmministrativo: "14",
      nome: "",
      percentualeRicadenza: event
    });
    (this.copySearchForm.get("ricadenzaCategorieForestali") as FormArray).push(
      localFormGroup
    );
    this.addEntry = false;
    this.errorMessage = null;
  }
  closeAndSave() {
    if (
      (this.copySearchForm.get("ricadenzaCategorieForestali") as FormArray)
        .length > 0
    ) {
      this.emitAddEntry.emit({
        formGroup: this.copySearchForm,
        display: false
      });
      this.addEntry = false;
    } else {
      this.errorMessage = "è necessario inserire almeno una particella";
    }
  }

  close() {
    /* (this.copySearchForm.get('ricadenzaCategorieForestali') as FormArray)['controls'].splice(0);
    (this.searchForm.get('ricadenzaCategorieForestali') as FormArray).controls.forEach((element, index) => {
      (this.copySearchForm.get('ricadenzaCategorieForestali') as FormArray).push(element);
    }); */
    this.emitAddEntry.emit({ display: false });
    this.addEntry = false;
    this.errorMessage = null;
  }

  delete(i) {
    (this.copySearchForm.get(
      "ricadenzaCategorieForestali"
    ) as FormArray).removeAt(i);
  }

  filterItem(event) {}

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
