/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnDestroy, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { SoggettoModel } from '../../models/soggetto.model';
import { ComuneModel } from '../../models/comune.model';
import { SelectItem } from 'primeng/api';
import { CONST } from '../../constants';

@Component({
  selector: 'app-persona-form',
  templateUrl: './persona-form.component.html',
  styleUrls: ['./persona-form.component.css']
})
export class PersonaFormComponent implements OnInit, OnDestroy {
  
  @Input() personalOrCompanyForm: FormGroup;
  @Input() set personaDatiOption(personaDatiOption: string) {
    this.personaOption = personaDatiOption;
    this.personaFisica = (personaDatiOption === 'RF') || (personaDatiOption === 'PF');
    this.personaGiurdica = (personaDatiOption === 'RG') || (personaDatiOption === 'PG');
    this.persona = (personaDatiOption === 'R') || (personaDatiOption === 'P');
    this.checkTipotipoAccreditamento(personaDatiOption);
  }
  @Input() codiceFiscaleSelectItem: SelectItem[];
  @Input() codiceFiscaleSONOIO: SelectItem[];
  @Input()isTecnicoForestale:boolean = false;
  tipoAccreditamento = null;
  comune: ComuneModel[];
  unsubscribe$ = new Subject<void>();
  tableData: SoggettoModel[];
  personaOption = null;
  personaFisica = null;
  personaGiurdica = null;
  persona = null;
  loadFromAnagrafica: boolean = false;
  insNuovoSoggetto: boolean = false;
  @Output() resetFormEmitter = new EventEmitter<void>();
  @Output() clearAllEmitter = new EventEmitter<void>();
  @Output() codiceFiscaleEmitter = new EventEmitter<void>();
  professionista: any[];
  autoComplete : FormGroup = new FormGroup({ autoProf: new FormControl() });
  emptyMessageAC : string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;
  constructor( private forestaliService: ForestaliService ) {}


  ngOnInit() {
    //console.log("PERSONA FORM",this);
    if(this.isTecnicoForestale){
      this.insNuovoSoggetto = true;
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  filterComune(event) {
    this.forestaliService.findComuniByNome(event.query).pipe(takeUntil(this.unsubscribe$)).subscribe((res: ComuneModel[]) => {
      this.comune = this.forestaliService.autocompleteFilter(event, res, 'Comune');
    });
  }

  myself() {
    this.forestaliService.getSoggettiIo()
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: SoggettoModel) => {
      this.personalOrCompanyForm.patchValue(res);
      this.professionista = [];
      this.autoComplete.reset();
      // this.forestaliService.getComuniById(res.fkComune).pipe(takeUntil(this.unsubscribe$))
      //     .subscribe((resComune: ComuneModel) => {
      //       this.personalOrCompanyForm.get('comune').patchValue(resComune);
      //     });
    });
    
  }

  fillFormFields(event) {
    this.forestaliService.getfillFormFields(event.value)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe((res: SoggettoModel) => {
      this.personalOrCompanyForm.reset();
      if(res){
        this.personalOrCompanyForm.patchValue(res);
      }
      this.codiceFiscaleEmitter.emit();
    });

    /* this.personalOrCompanyForm.patchValue(res[0]);
          this.forestaliService.getComuniById(res[0].fkComune).pipe(takeUntil(this.unsubscribe$))
          .subscribe((resComune: ComuneModel) => {
            this.personalOrCompanyForm.get('comune').patchValue(resComune);
          }); */
  }


  /* enabledField() {
    this.personalOrCompanyForm.get('codiceFiscale').enable();
    this.tipoAccreditamento === 'Professionista' ? null : this.personalOrCompanyForm.get('partitaIva').enable();
    this.tipoAccreditamento === 'Professionista' ? null : this.personalOrCompanyForm.get('denominazione').enable();
  } */
  fillFormOnSelect(event){
    this.personalOrCompanyForm.patchValue(event);
  }

  checkTipotipoAccreditamento(personaDatiOption: string) {
    this.tipoAccreditamento = personaDatiOption === 'R' || personaDatiOption === 'RG'? 'Richiedente' : 'Professionista';
    if(personaDatiOption === "R" || personaDatiOption === 'P'){
      this.resetFormEmitter.emit();
    }
  }

  filterProfessionista(event) {
    this.forestaliService.searchAllProfessionista().pipe(takeUntil(this.unsubscribe$)).subscribe((res) => {  
      this.professionista = this.forestaliService.autocompleteFilter(event, res, 'Professionista');
      this.loadFromAnagrafica = true;
    });  }
    
  clearAll(){
    this.autoComplete.reset();
    this.personalOrCompanyForm.reset();
    this.clearAllEmitter.emit();
    this.loadFromAnagrafica = false;
    this.insNuovoSoggetto=  true;
    //this.persona = true;
  }

  resetInsNuovo(){
    this.insNuovoSoggetto=  false;
  }
  
  resetForm() {
    this.autoComplete.reset();
    this.resetFormEmitter.emit(); }
}


