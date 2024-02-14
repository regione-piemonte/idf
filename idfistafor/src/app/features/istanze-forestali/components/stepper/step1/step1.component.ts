/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Subject, combineLatest } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { SelectItem } from 'primeng/api';

import { CONST } from 'src/app/shared/constants';
import { HomeModel } from 'src/app/shared/models/home.model';
import { UserChoiceModel } from 'src/app/shared/models/user-choice.model';
import { ForestaliService } from '../../../services/forestali.service';
import { UserCompanyDataModel } from 'src/app/shared/models/user-company-data.model';
import { ComuneModel } from 'src/app/shared/models/comune.model';

@Component({
  selector: 'app-step1',
  templateUrl: './step1.component.html',
  styleUrls: ['./step1.component.css']
})
export class Step1Component implements OnInit, OnDestroy {
  @Input() editMode: number | null;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() emitNextStep = new EventEmitter();
  @Output() emitIdIstanze = new EventEmitter<number>();

  userData: UserCompanyDataModel;
  companyData: UserCompanyDataModel;
  userCompanyData: UserCompanyDataModel;
  personaForm: FormGroup;
  companyForm: FormGroup;
  delegatoForm: FormGroup;
  personalOrCompanyForm: FormGroup;
  unsubscribe$ = new Subject<void>();
  cfOwner:string;
  loading: boolean = false;

  companies: UserCompanyDataModel[] = [];
  tipoAccreditamento = null; // Richiedente or Professionista
  personaDatiOption: string = null; // RF, RG, PF or PG
  qui = false;
  selectedCompany = false;
  codiceFiscaleSelectItem: SelectItem[] = [];
  codiceFiscaleSONOIO: SelectItem[] = [];
  codiceFiscaleDelegati: SelectItem[] = [];
  ownerOfInstance: string = sessionStorage.getItem('codiceFiscale');
  companyInfoIVA: string = '';
  companyInfoDenominazione: string = '';
  onlyAzienda: boolean = false;
  personaFields = ['codiceFiscale', 'cognome', 'nome', 'comune',
  'indirizzo', 'civico', 'cap', 'nrTelefonico', 'eMail'];
  companyFields = ['codiceFiscale', 'partitaIva', 'denominazione', 'comune', 'cap',
  'indirizzo', 'civico', 'pec', 'eMail', 'nrTelefonico'];


  constructor(private forestaliService: ForestaliService, private fb: FormBuilder) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    if(this.boMode === false){
      combineLatest(this.forestaliService.getAdpfor(), this.forestaliService.getDropdownCodiceFiscale())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(([resultAdpfor, resultDropdownCodiceFiscale]) => {
        this.tipoAccreditamento = resultAdpfor.fkTipoAccreditamento;
        resultDropdownCodiceFiscale.forEach((element, index) => {
          if (element !== null && (element === this.ownerOfInstance || this.tipoAccreditamento == "Professionista")) {
            //  if(element === this.ownerOfInstance){
            //    this.ownerOfInstance = element;
            //  }
            this.codiceFiscaleSelectItem.push({value: element, label: element});
          }
        });
        if (this.editMode) {
          this.forestaliService.getStep1(this.editMode)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe((result) => {
            if (result.personaDatiOption === 'RF') {
              this.codiceFiscaleSONOIO[0] = {value: result.codiceFiscale, label: result.codiceFiscale};
            }
            (result.personaDatiOption === 'RF' || result.personaDatiOption === 'PF') ? this.userData = result : this.companyData = result;
            this.cfOwner = result.ownerCodiceFiscale;
            this.option(result.personaDatiOption);
          });
        }
      });
    } else{
      this.forestaliService.getStep1(this.editMode).subscribe(result =>{
        if (result.personaDatiOption === 'RF') {
          this.codiceFiscaleSONOIO[0] = {value: result.codiceFiscale, label: result.codiceFiscale};
        }
        (result.personaDatiOption === 'RF' || result.personaDatiOption === 'PF') ? this.userData = result : this.companyData = result;
        this.cfOwner = result.ownerCodiceFiscale;
        this.option(result.personaDatiOption);
        if(result.personaDatiOption === 'RF' || result.personaDatiOption === 'RG'){
          this.tipoAccreditamento = 'Richiedente';
        }else{
          this.tipoAccreditamento = 'Professionista';
        }
      })
    }

  }

  initPersonalForm(personaData) {
    console.log('111 personaDatiOption', this.personaDatiOption);
    this.personaForm = this.fb.group({
      tipoIstanzaId: [{ value: personaData ? personaData.tipoIstanzaId : '', disabled: false }],
      tipoIstanzaDescr: [{ value: personaData ? personaData.tipoIstanzaDescr : '', disabled: false }],
      codiceFiscale: [{ value: personaData ? personaData.codiceFiscale : '',
        disabled: (this.personaDatiOption === 'RF') ? true : false },
        [Validators.required, Validators.maxLength(16)]],
      comune: [{ value: personaData ? personaData.comune : '', disabled: false }, Validators.required],
      cap: [{ value: personaData ? personaData.cap : '', disabled: false }, [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]],
      indirizzo: [{ value: personaData ? personaData.indirizzo : '', disabled: false }, [Validators.required, Validators.maxLength(50)]],
      civico: [{ value: personaData ? personaData.civico : '', disabled: false }, [Validators.required, Validators.maxLength(20)]],
      eMail: [{ value: personaData ? personaData.eMail : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(100)]],
      nrTelefonico: [{ value: personaData ? personaData.nrTelefonico : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.maxLength(20)]],
      nome: [{ value: personaData ? personaData.nome : '',
        disabled: (this.personaDatiOption === 'RF') ? true : false }, [Validators.required, Validators.maxLength(50)]],
      cognome: [{ value: personaData ? personaData.cognome : '',
        disabled: (this.personaDatiOption === 'RF') ? true : false }, [Validators.required, Validators.maxLength(100)]]
    });
    console.log('1 personaForm', this.personaForm);
  }
  initCompanyForm(companyData) {
    this.companyForm = this.fb.group({
      tipoIstanzaId: [{ value: companyData ? companyData.tipoIstanzaId : '', disabled: false }],
      tipoIstanzaDescr: [{ value: companyData ? companyData.tipoIstanzaDescr : '', disabled: false }],
      codiceFiscale: [{ value: companyData ? companyData.codiceFiscale : '', disabled: false },
        [Validators.required, Validators.maxLength(16)]],
      partitaIva: [{ value: companyData ? companyData.partitaIva : '', disabled: false },
        [Validators.required, Validators.maxLength(11)]],
      denominazione: [{ value: companyData ? companyData.denominazione : '', disabled: false },
        [Validators.required, Validators.maxLength(200)]],
      comune: [{ value: companyData ? companyData.comune : '', disabled: false }, Validators.required],
      cap: [{ value: companyData ? companyData.cap : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_CAP)]],
      indirizzo: [{ value: companyData ? companyData.indirizzo : '', disabled: false },
        [Validators.required, Validators.maxLength(50)]],
      civico: [{ value: companyData ? companyData.civico : '', disabled: false },
        [Validators.required, Validators.maxLength(20)]],
      pec: [{ value: companyData ? companyData.pec : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(100)]],
      eMail: [{ value: companyData ? companyData.eMail : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_MAIL), Validators.maxLength(200)]],
      nrTelefonico: [{ value: companyData ? companyData.nrTelefonico : '', disabled: false },
        [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_WITH_ZERO), Validators.maxLength(20)]]
    });
    console.log('222 FINISH', this.companyForm);
  }

  initDelegatiForm(delegato:string){
    this.delegatoForm = this.fb.group({
      delegatoId: [{value: delegato, disabled: false }]
    })
  }

  option(personaDatiOption) {
    this.qui = this.editMode ? true : false;
    this.personaDatiOption = personaDatiOption;
    // if (this.editMode) {
    //   this.forestaliService.getStep1(this.editMode)
    //     .pipe(takeUntil(this.unsubscribe$))
    //     .subscribe((result) => {
    //       // (result.personaDatiOption === 'RF' || result.personaDatiOption === 'PF') ? this.userData = result : this.companyData = result;
    //       // this.restartForm(personaDatiOption);
    //       this.checkEditModePersonaOptions(result);
    //     });
    // } else {
      if (personaDatiOption === 'RF') {
        this.forestaliService.getSoggettiIo()
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe(res => {
            this.codiceFiscaleSONOIO[0] = { value: res.codiceFiscale, label: res.codiceFiscale };
            this.userData = res;
            this.restartForm(personaDatiOption);
          });
      } else if( personaDatiOption === "RG") {
        if(this.companyData){
          this.initCompanyForm(this.companyData);
        }
        this.forestaliService.getSoggettiIo().pipe(takeUntil(this.unsubscribe$)).subscribe(res => {
          this.codiceFiscaleSONOIO[0] = { value: res.codiceFiscale, label: res.codiceFiscale };
          this.userData = res;
          if(!this.boMode){
            this.loading = true;
            this.forestaliService.getMyAziende(res.codiceFiscale).pipe(takeUntil(this.unsubscribe$)).subscribe(response => {
              this.codiceFiscaleSelectItem = [];
              this.companies = response.body;
              this.companies.forEach((element, index) => {
                if (element !== null) { this.codiceFiscaleSelectItem.push({value: element.codiceFiscale, label: element.codiceFiscale}); }
              });
              this.loading = false;
            });
          }
        });
      } else if(personaDatiOption === "PG"){
          this.codiceFiscaleDelegati = this.codiceFiscaleSelectItem;

          //this.initDelegatiForm(this.cfOwner);

          if(this.companyData){
            this.initCompanyForm(this.companyData);
          }

          // if(this.cfOwner){
          //   this.fillAzienda({value:this.cfOwner});
          // }
        // this.forestaliService.getMyAziende(piva?piva:this.ownerOfInstance).pipe(takeUntil(this.unsubscribe$)).subscribe(response => {
        //   this.codiceFiscaleSelectItem = [];
        //   this.companies = response.body;
        //   if(piva){
        //     if(this.companies.length>0){
        //       this.fillFormForCodicaFiscale(this.companies[0].codiceFiscale);
        //     }
        //   }
        //   this.companies.forEach((element, index) => {
        //     if (element !== null) {
        //       this.codiceFiscaleSelectItem.push({value: element.codiceFiscale, label: element.codiceFiscale});
        //       if(this.ownerOfInstance === element.codiceFiscale){
        //         this.onlyAzienda = true;
        //       }
        //     }
        //   });
        // });
      } else {
        this.restartForm(personaDatiOption);
      }
    //}
  }

  checkEditModePersonaOptions(result: UserCompanyDataModel) {
    if (result.cognome === undefined) {
      this.companyData = result;
      this.personaDatiOption = "RG";
      this.restartForm(this.personaDatiOption);
    } else if(result.cognome !== undefined){
      this.userData = result;
      this.personaDatiOption = "RF";
      this.restartForm(this.personaDatiOption);
   }
  }

  restartForm(personaDatiOption) {
    switch (personaDatiOption) {
      case 'RF':
        this.initPersonalForm(this.editMode ? this.userData : (personaDatiOption === 'RF' ? this.userData : null));
        this.codiceFiscaleSONOIO[0] = {value: this.userData.codiceFiscale, label: this.userData.codiceFiscale};
      break;
      case 'PF':
        // this.codiceFiscaleSelectItem = [];
        // this.codiceFiscaleSelectItem.push({value: this.ownerOfInstance, label: this.ownerOfInstance});
        this.initPersonalForm(this.editMode ? this.userData : (personaDatiOption === 'RF' ? this.userData : null));
      break;
      case 'RG':
        if(this.editMode){
          this.companies.push(this.companyData);
          if(this.companyData){
            this.codiceFiscaleSelectItem.push({value: this.companyData.codiceFiscale, label: this.companyData.codiceFiscale});
          }

          this.initCompanyForm(this.editMode ? this.companyData : (personaDatiOption === 'RG' ? this.companyData : null));
        } else {
          this.initCompanyForm(this.editMode ? this.companyData : (personaDatiOption === 'RG' ? this.companyData : null));
          this.companyForm.reset();
        }
        break;
      case 'PG':
        this.initDelegatiForm(this.cfOwner);
        this.initCompanyForm(this.editMode ? this.companyData : null);
        //this.companyForm.get('codiceFiscale').patchValue(this.ownerOfInstance);
      break;
    }
  }

  getCodiceFiscale() { }

  continue() { this.save(true); }

  save(nextStep?: boolean) {
    if (this.personaDatiOption === 'RF' || this.personaDatiOption === 'PF') {
      this.personalOrCompanyForm = this.personaForm;
    }
    if (this.personaDatiOption === 'RG' || this.personaDatiOption === 'PG') {
      this.personalOrCompanyForm = this.companyForm;
    }
    if (this.personalOrCompanyForm.valid) {
      this.forestaliService.getAdpforByIstanzaId(sessionStorage.getItem("tipoIstanzaId"))
      .pipe(takeUntil(this.unsubscribe$)).subscribe(res => {
          this.userCompanyData = this.personalOrCompanyForm.getRawValue();
          this.userCompanyData.personaDatiOption = this.personaDatiOption;
          if(this.personaDatiOption == 'PG' && this.cfOwner) {
            this.userCompanyData.ownerCodiceFiscale = this.cfOwner;
          }
          /* if (this.personaDatiOption !== 'RF') {
          //   delete this.userCompanyData.comune["dataInizioValidita"];
          // delete this.userCompanyData.comune["dataFineValidita"];
          }
          if (this.personaDatiOption === 'RF') {
            this.userCompanyData.nome = this.forestaliService.user3.nome;
            this.userCompanyData.cognome = this.forestaliService.user3.cognome;
            this.userCompanyData.codiceFiscale = this.forestaliService.user3.codiceFiscale;
          } */
          this.userCompanyData.tipoIstanzaId = res.tipoIstanzaId;
          this.userCompanyData.tipoIstanzaDescr = res.tipoIstanzaDescr;
        if (this.editMode) {
          this.forestaliService
            .putStep1(this.userCompanyData, this.editMode).pipe(takeUntil(this.unsubscribe$)).subscribe(res => {
              nextStep && this.emitNextStep.emit();
            });
        } else {
          this.codiceFiscaleSONOIO.length !== 0 ?  this.userCompanyData.ownerCodiceFiscale = this.codiceFiscaleSONOIO[0].value : this.userCompanyData.ownerCodiceFiscale = this.ownerOfInstance;
          this.forestaliService.postStep1(this.userCompanyData).pipe(takeUntil(this.unsubscribe$)).subscribe(res => {
            if (!this.editMode) {
              this.editMode = res.istanzaId;
              this.emitIdIstanze.emit(res.istanzaId);
              nextStep && this.emitNextStep.emit();
            }
          });
        }
      });
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  enterCompanyData() {
    if(this.qui === false){
      this.qui = !this.qui;
    }
    this.restartForm(this.personaDatiOption);
  }

  chooseCompany(event){
    if(this.personaDatiOption === 'PG'){
      console.log('choseCompany PG')
      this.forestaliService.getAzienda(event.value).pipe(takeUntil(this.unsubscribe$)).subscribe(response => {
        this.companies = [response.body];
        this.loading = false;
        this.fillFormForCodicaFiscale(event.value);
      });
    }else{
    this.fillFormForCodicaFiscale(event.value)
    }
  }

  chooseDelegato(event){
    if(this.companyForm){
      this.companyForm.reset();
    }
    this.cfOwner = event.value;
    this.fillAzienda(event);
  }

  fillAzienda(event){
    this.loading = true;
    this.forestaliService.getMyAziende(event.value).pipe(takeUntil(this.unsubscribe$)).subscribe(response => {
      this.codiceFiscaleSelectItem = [];
      this.companies = response.body;
      this.companies.forEach((element, index) => {
        if (element !== null) {
          this.codiceFiscaleSelectItem.push({value: element.codiceFiscale, label: element.codiceFiscale});
        }
      });
      this.loading = false;
    });
  }

  fillFormForCodicaFiscale(codiceFiscale: string) {
    this.companyData = this.companies.find(comp => comp.codiceFiscale === codiceFiscale);
    if(this.companyData){
      this.companyInfoIVA = this.companyData.partitaIva;
      this.companyInfoDenominazione= this.companyData.denominazione;
      if(this.companyData.fkComune){
        this.forestaliService.getComuniById(this.companyData.fkComune).pipe(takeUntil(this.unsubscribe$))
          .subscribe((resComune: ComuneModel) => {
            this.companyForm.get('comune').patchValue(resComune);
          })
      }
    }
    this.initCompanyForm(this.companyData);
    if(this.qui === false){
      this.qui = !this.qui;
    }
  }

  companyFillFields(index: number) {
    /* this.qui = true;
    this.selectedCompany = true;
    this.disabledField();
    this.forestaliService
    .getCompanyData(index)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: HomeModel) => {
        this.personalOrCompanyForm.patchValue(res);
        this.changeValidators(this.companyFields);
      }); */
  }


}

/* onDateChange(): void {
  this.searchForm.get('dataRilevamentoFrom').valueChanges.subscribe({
    next: response => {
      if(!response) {
        this.searchForm.get('dataRilevamentoTo').disable();
      } else {
        this.searchForm.get('dataRilevamentoTo').enable();
      }
    }
  })
} */
