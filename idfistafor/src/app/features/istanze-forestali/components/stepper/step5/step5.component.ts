/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnDestroy, Output, EventEmitter } from '@angular/core';
import { TableHeader } from 'src/app/shared/models/table-header';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Subject, combineLatest } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { ForestaliService } from '../../../services/forestali.service';
import { EconomicCalculationTableModel } from 'src/app/shared/models/based-economic-value.model';
import { CONST } from 'src/app/shared/constants';
import { SoggettoModel } from 'src/app/shared/models/soggetto.model';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { StepPosition } from 'src/app/shared/models/step-position.model';
import { UserCompanyDataModel } from 'src/app/shared/models/user-company-data.model';
import { SelectItem } from 'primeng/api';

@Component({
  selector: 'app-step5',
  templateUrl: './step5.component.html',
  styleUrls: ['./step5.component.css']
})
export class Step5Component implements OnInit, OnDestroy {

  @Input() editMode: number;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() emitNextStep = new EventEmitter();
  unsubscribe$ = new Subject<void>();
  userCompanyData: UserCompanyDataModel;
  personalOrCompanyForm: FormGroup;
  valoreRealeForm: FormGroup;
  personaDatiOption = null;
  activeIndex = null;

  codiceFiscaleSelectItem: SelectItem[] = [];
  hrefTabellaPesiAttributi: string;
  sortedColumn = '';
  tableHeaders: TableHeader[] = [];
  tableData: EconomicCalculationTableModel[] = [];

  totaleCalcolato:any;

fields = ['codiceFiscale', 'partitaIva', 'cognome', 'nome', 'comune',
'indirizzo', 'civico', 'cap', 'pec', 'nrTelefonico', 'eMail'];

  constructor(private forestaliService: ForestaliService,  private fb: FormBuilder) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.forestaliService.getConfigationByName('URL_COMPENSAZIONE').subscribe((res: any) => { 
      this.hrefTabellaPesiAttributi = res.value;
    })
    if(this.boMode){
      this.boModeOnPatchValues();
    }else{
      this.getIo();
      this.restartForm();
      combineLatest(this.forestaliService.getAdpfor(), this.forestaliService.getStep5(this.editMode))
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(([res, result]) => {
          result.totale = this.fillTwoDecimal(result.totale);
          if(result.valoreReale){
            result.valoreReale = this.fillTwoDecimal(result.valoreReale);
          }
          this.totaleCalcolato = result.totale
          this.initValoreRealeForm(result);
        if(result.soggettoProfess){
          this.userCompanyData = result.soggettoProfess;
          this.restartForm();
          if(result['soggettoProfess']['fkComune']){
            this.forestaliService.getComuniById(result['soggettoProfess']['fkComune'])
              .pipe(takeUntil(this.unsubscribe$))
              .subscribe((resComune: ComuneModel) => {
                this.personalOrCompanyForm.get('comune').patchValue(resComune);
              });
            }
        }  
        this.personaDatiOption = res.fkTipoAccreditamento === 'Richiedente' ? 'P' : 'P';
        this.tableHeaders = [
          {field: 'nome', header: 'Valore economico base (Euro/ha)'},
          {field: 'valore', header: '' + result.baseValue}
        ];
        result.sceltiParametri.forEach((element, index) => {
          if(element.valore.toString().includes('.')){
            element.valore = element.valore.toString().replace('.',',')
          }
          this.tableData.push(element);
        });
        this.tableData.unshift({ nome: 'Superficie (ha)', valore: result.superficie.toString().includes('.')? result.superficie.toString().replace('.',','):result.superficie });
        this.tableData.push({ nome: 'Valore teorico calcolato in euro', valore:  this.totaleCalcolato.toString().includes('.')?this.totaleCalcolato.toString().replace('.',','):this.totaleCalcolato });
        this.forestaliService.getStepNumber(this.editMode).pipe(takeUntil(this.unsubscribe$)).subscribe((response: StepPosition) => {
            this.activeIndex = response.nextStep;
            if (this.editMode && res.tipoIstanzaId && this.activeIndex !== 4) {
              this.personalOrCompanyForm.patchValue(result['soggettoProfess']);
              if(result['soggettoProfess']['fkComune']){
                this.forestaliService.getComuniById(result['soggettoProfess']['fkComune'])
                .pipe(takeUntil(this.unsubscribe$))
                .subscribe((resComune: ComuneModel) => {
                  this.personalOrCompanyForm.get('comune').patchValue(resComune);
                });
              }
            }
          });
      });
    }
  }

  fillTwoDecimal(num:any){
    num = num + '';
    num = num.replace(',','.');
    let idx = num.indexOf('.');
    if(idx == -1){
      return num + '.00'
    }else if(idx == num.length - 1){
      return num + '00'
    }else {
      idx = num.length - idx;
      if(idx < 3){
        return num + '0'
      }
    }
    return num;
  }

  initValoreRealeForm(values: any){
    let valoreReale = values ? values.valoreReale?values.valoreReale : this.totaleCalcolato : this.totaleCalcolato
    this.valoreRealeForm = this.fb.group({
      valoreReale: [{ value: valoreReale, disabled: false },
        [Validators.required,Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL)]],
      note: [{ value: values ? values.note : '', disabled: false} , 
      valoreReale != this.totaleCalcolato?[Validators.required,Validators.minLength(20)]:[]]
    })
  }

  onValoreRealeLostFocus(){
    let valoreReale = this.valoreRealeForm.get('valoreReale').value;
    let valoreDeciaml = this.fillTwoDecimal(valoreReale);
    if(valoreReale != valoreDeciaml){
      this.valoreRealeForm.get('valoreReale').patchValue(valoreDeciaml);
      this.onValoreRealeChange();
    }
  }

  onValoreRealeChange(){
    let valoreReale = this.valoreRealeForm.get('valoreReale').value;
    if(valoreReale.indexOf(',') > -1){
      this.valoreRealeForm.get('valoreReale').patchValue(valoreReale.replace(',','.'));
    }
    this.initValoreRealeForm(this.valoreRealeForm.value);
  }

  boModeOnPatchValues() {
    this.forestaliService.getStep5(this.editMode).subscribe(response => {
      this.userCompanyData = response.soggettoProfess;
      this.personaDatiOption = "P";
      this.restartForm();
      this.tableHeaders = [
        { field: 'nome', header: 'Valore economico base (Euro/ha)' },
        { field: 'valore', header: '' + response.baseValue }
      ];
      response.sceltiParametri.forEach((element, index) => {
        this.tableData.push(element);
      });
      this.tableData.unshift({ nome: 'Superficie (ha)', valore: response.superficie });
      this.tableData.push({ nome: 'Valore teorico calcolato in euro', valore: response.totale });
    });
  }

  private initForm(userCompanyData) {
    this.personalOrCompanyForm = this.fb.group({
      idSoggetto: [{ value: userCompanyData ? userCompanyData.idSoggetto : null, disabled: false }],
      codiceFiscale: [{ value: userCompanyData ? userCompanyData.codiceFiscale : null, disabled: false },
        [Validators.required, Validators.maxLength(16)]],
      partitaIva: [{ value: userCompanyData ? userCompanyData.partitaIva : null, disabled: false },
        [Validators.required, Validators.maxLength(11)]],
      comune: [{ value: userCompanyData ? userCompanyData.comune : null, disabled: true }],
      cap: [{ value: userCompanyData ? userCompanyData.cap : null, disabled: true },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]],
      indirizzo: [{ value: userCompanyData ? userCompanyData.indirizzo : null, disabled: ( this.personaDatiOption === 'P')? true : false },
        [Validators.required, Validators.maxLength(50)]],
      civico: [{ value: userCompanyData ? userCompanyData.civico : null, disabled: ( this.personaDatiOption === 'P')? true : false },
        [Validators.required, Validators.maxLength(20)]],
      pec: [{ value: userCompanyData ? userCompanyData.pec : null, disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(200)]],
      eMail: [{ value: userCompanyData ? userCompanyData.eMail : null, disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(100)]],
      nrTelefonico: [{ value: userCompanyData ? userCompanyData.nrTelefonico : null, disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.maxLength(20)]],
      nome: [{ value: userCompanyData ? userCompanyData.nome : null, disabled: false },
        [Validators.required, Validators.maxLength(50)]],
      cognome: [{ value: userCompanyData ? userCompanyData.cognome : null, disabled: false },
        [Validators.required, Validators.maxLength(100)]]
    });
    
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  getRowId(event) {}
  getUpdatedTable(event) {}
  deleteRow(event) {}

  getCodiceFiscale() { }

  restartForm() {
    this.initForm(this.userCompanyData);
  }

  clearAll(event: any){
    console.log("PULISCI CLICKED"); 
    this.initForm(event);
  }

  continue() { this.save(true); }

  save(nextStep?: boolean) {
    if (this.personalOrCompanyForm.valid && this.valoreRealeForm.valid) {
    let step5 = this.valoreRealeForm.value;
    const soggettoProfess: SoggettoModel = this.personalOrCompanyForm.getRawValue();
    step5['soggettoProfess'] = soggettoProfess;
    if(this.personalOrCompanyForm.get('comune').value){
      soggettoProfess.fkComune = this.personalOrCompanyForm.get('comune').value.idComune;
    }
    delete soggettoProfess['comune'];
    this.forestaliService.postStep5(step5, this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(response => {
        nextStep && this.emitNextStep.emit();
    });
    }
  }
  getIo(){
    this.forestaliService.getSoggettiIo()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: SoggettoModel) => {
      this.personalOrCompanyForm.patchValue(res);
      this.personaDatiOption = "RF"
    });
  }
}
