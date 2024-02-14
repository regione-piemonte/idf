/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, EventEmitter, Output, Input } from '@angular/core';
import { Subject } from 'rxjs';

import { CheckboxAndRadio } from 'src/app/shared/models/checkbox-and-radio';
import { FormArray, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { VincoloService } from '../../../services/vincolo.service';
import { takeUntil } from 'rxjs/operators';
import { DialogButtons, ButtonType } from 'src/app/shared/error-dialog/error-dialog.component';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { TipoIntervento } from 'src/app/shared/models/tipo-intervento';
import { CONST } from 'src/app/shared/constants';
import { VincoloStep3 } from './vincoloStep3';
import { UtilService } from 'src/app/shared/services/util.service';

@Component({
  selector: 'step3-vid',
  templateUrl: './step3-vid.component.html',
  styleUrls: ['./step3-vid.component.css']
})
export class Step3VidComponent implements OnInit, OnDestroy {
  @Input() editMode: number;
  @Input() thirdFormData: VincoloStep3;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() nextStepEmitter = new EventEmitter<void>();

  showPopUp:boolean = false;
  msgPopUp:string = '';
  goNextStep:boolean = false;

  checkboxRadioForm: FormGroup;
  unsubscribe$ = new Subject<void>();
  disableCategoriaForestale = false;
  invalidForm: boolean;
  invalidHeadings: boolean;
  invalidTotal: boolean;
  tipiIntervento: TipoIntervento[];

  totaleSuperficieCatastale: string;
  totaleSuperficieTrasf: string;
  totaleSuperficieBoscata: string;
  totaleSuperficieNonBoscata: string;
  totaleSuperficieInVincolo: string;
  totaleSuperficieBoscataInVincolo: string;
  totaleSuperficieNonBoscataInVincolo: string;

  descrizioneInterventoForm: FormGroup;
  emptyMessageAC : string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  constructor(
    private dialogService: DialogService,
    private fb: FormBuilder,
    private utilService: UtilService,
    private vincoloService: VincoloService
  ) {}

  ngOnInit() {
    window.scrollTo(0, 0);
    this.getSuperfici();
    this.initForms();
    this.fillForms();
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  checkValidation() {
    this.invalidForm = false;
    this.invalidHeadings = false;
    this.invalidTotal = false;

    (this.checkboxRadioForm.get('headings') as FormArray).controls.forEach((element, index) => {
      if(element.get('type').value == 'radio' || element.get('type').value == 'checkbox') {
        let mustChecked = false;
        (element.get('subheadings') as FormArray).controls.forEach((subElement, subIndex) => {
          mustChecked = mustChecked || subElement.get('checked').value;
        });
        this.invalidHeadings = this.invalidHeadings || !mustChecked;
      }
    });

    this.invalidForm = this.descrizioneInterventoForm.invalid;
    this.invalidTotal = this.invalidForm || this.invalidHeadings;
    return this.invalidTotal;
  }

  continue() {
    this.save(true);
  }

  fillForms() {
    if(this.thirdFormData != null) {
      this.descrizioneInterventoForm.patchValue(this.thirdFormData);

      this.vincoloService.getTipiIntervento(9).pipe(takeUntil(this.unsubscribe$)).subscribe((res: TipoIntervento[]) => {
        const tipoIntervento: TipoIntervento = res.find(x => x.idTipoIntervento == this.thirdFormData.tipoIntervento);
        if(tipoIntervento != null) this.descrizioneInterventoForm.get('tipoIntervento').patchValue(tipoIntervento);
      });
    }

    if((this.checkboxRadioForm.get('headings') as FormArray).length === 1 && (this.checkboxRadioForm.get('headings') as FormArray).value[0].id === null) {
      (this.checkboxRadioForm.get('headings') as FormArray).removeAt(0);
      this.checkboxRadioForm.patchValue(this.thirdFormData);

      this.thirdFormData.headings.forEach((element, index) => {
        let controlType: string;

        switch(element.name.charAt(0)) {
          case "C":
            controlType = "checkbox";
            break;
          case "R":
            controlType = "radio";
            break;
          case "T":
            controlType = "text";
            break;
        }

        const tempHeading: FormGroup = this.fb.group({
          id: element.id,
          name: element.name,
          visibility: element.visibility,
          type: controlType,
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
          
          if(element.id === 2 && subElement.checked) this.disableCategoriaForestale = false;
          (tempHeading.get('subheadings') as FormArray).push(tempSubHeading);
        });
        
        (this.checkboxRadioForm.get('headings') as FormArray).push(tempHeading);
      });
    }
  }

  filterTipoIntervento(event: any) {
    this.vincoloService.getTipiIntervento(9).pipe(takeUntil(this.unsubscribe$)).subscribe((res: TipoIntervento[]) => {
      this.tipiIntervento = this.vincoloService.autocompleteFilter(event, res, 'TipoIntervento');
    });
  }

  initForms() {
    this.descrizioneInterventoForm = this.fb.group({
      descrizioneIntervento: [null, [Validators.required]],
      tipoIntervento: [null, [Validators.required]],
      altroTipoIntervento: [null],
      //totaliSuperfici: [null, [Validators.required Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      totaleTotMovimentiTerra: [null, [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      totaleTotMovimentiTerraVincolo: [null, [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      tempoPrevisto: [null, [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO)]],
      presenzaAreeDissesto: [null],
      presenzaAreeEsondazione: [null],
    });
    
    this.checkboxRadioForm = this.fb.group({
      headings: this.fb.array([
        this.fb.group({
          id: null,
          name: '',
          visibility: '',
          type: '',
          subheadings: this.fb.array([
            this.fb.group({
              id: '',
              name: '',
              visibility: '',
              valore: '',
              checked: ''
            })
          ])
        })
      ])
    });
  }

  onSelectCheckbox(fieldName: string, event: any) {
    this.descrizioneInterventoForm.get(fieldName).patchValue(event.target.checked);
  }

  onSelectHeadings(index, subIndex, event) {
    const headingArray = (this.checkboxRadioForm.get('headings') as FormArray).controls[index];
    const subHeadingArray = (headingArray.get('subheadings') as FormArray).controls[subIndex];

    switch(event.target.type) {
      case "checkbox":
        subHeadingArray.get('checked').patchValue(!subHeadingArray.get('checked').value);
        break;
      case "radio":
        (headingArray.get('subheadings') as FormArray).controls.forEach(element => element.get('checked').patchValue(false));
        break;
    }
  }

  save(nextStep?: boolean) {
    //console.log(this.descrizioneInterventoForm.value);
    this.goNextStep = nextStep;
    let vincoloStep3: VincoloStep3 = new VincoloStep3();
    vincoloStep3.descrizioneIntervento = this.descrizioneInterventoForm.get('descrizioneIntervento').value;
    vincoloStep3.tipoIntervento = this.descrizioneInterventoForm.get('tipoIntervento').value['idTipoIntervento'];
    vincoloStep3.altroTipoIntervento = this.descrizioneInterventoForm.get('altroTipoIntervento').value;
    //vincoloStep3.totaliSuperfici = this.descrizioneInterventoForm.get('totaliSuperfici').value;
    vincoloStep3.totaleTotMovimentiTerra = this.descrizioneInterventoForm.get('totaleTotMovimentiTerra').value;
    vincoloStep3.totaleTotMovimentiTerraVincolo = this.descrizioneInterventoForm.get('totaleTotMovimentiTerraVincolo').value;
    vincoloStep3.tempoPrevisto = this.descrizioneInterventoForm.get('tempoPrevisto').value;
    vincoloStep3.presenzaAreeDissesto = this.descrizioneInterventoForm.get('presenzaAreeDissesto').value == null ? false : this.descrizioneInterventoForm.get('presenzaAreeDissesto').value;
    vincoloStep3.presenzaAreeEsondazione = this.descrizioneInterventoForm.get('presenzaAreeEsondazione').value == null ? false : this.descrizioneInterventoForm.get('presenzaAreeEsondazione').value;
    vincoloStep3.headings = this.checkboxRadioForm.get('headings').value;

    this.vincoloService
      .postStep3(vincoloStep3, this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(response => {
        if(response.errorMessage){
          this.showPopUp = true;
          this.msgPopUp = response.errorMessage
        }else{
          if(nextStep) this.nextStepEmitter.emit();
        }
      });
  }

  prosegui(){
    this.showPopUp = false;
    if(this.goNextStep) this.nextStepEmitter.emit();
  }

  closePopUp(){
    this.showPopUp = false;
  }

  getSuperfici(){
    this.vincoloService.getSuperfici(this.editMode)    
    .subscribe((response: any) => {
      this.totaleSuperficieCatastale = (response.totaleSuperficieCatastale 
        || response.totaleSuperficieCatastale == 0)?
      this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieCatastale)) : '';

      this.totaleSuperficieTrasf = (response.totaleSuperficieTrasf 
        || response.totaleSuperficieTrasf == 0)?
      this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieTrasf)) : '';

       this.totaleSuperficieBoscata = (response.totaleSuperficieBoscata 
        || response.totaleSuperficieBoscata == 0)?
       this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieBoscata)) : '';

      this.totaleSuperficieNonBoscata = (response.totaleSuperficieNonBoscata 
        || response.totaleSuperficieNonBoscata == 0)?
      this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieNonBoscata)) : '';

       this.totaleSuperficieInVincolo = (response.totaleSuperficieInVincolo 
        || response.totaleSuperficieInVincolo == 0)?
       this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieInVincolo)) : '';

       this.totaleSuperficieBoscataInVincolo = (response.totaleSuperficieBoscataInVincolo 
        || response.totaleSuperficieBoscataInVincolo == 0)?
       this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieBoscataInVincolo)) : '';

       this.totaleSuperficieNonBoscataInVincolo = (response.totaleSuperficieNonBoscataInVincolo 
        || response.totaleSuperficieNonBoscataInVincolo == 0)?
       this.utilService.format4Decimals(JSON.stringify(response.totaleSuperficieNonBoscataInVincolo)) : '';

    });
  }

}