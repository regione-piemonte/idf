/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { SelectItem } from 'primeng/components/common/selectitem';
import { StepsService } from 'src/app/services/steps.service';
import { Observable, Subject } from 'rxjs';
import { ForestaleSampleService } from 'src/app/shared/services/forestale-sample.service';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { takeUntil } from 'rxjs/operators';
import { CONST } from 'src/app/shared/constants';
import { Router } from '@angular/router';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';

@Component({
  selector: 'app-dati-stazionali-tabs-second',
  templateUrl: './dati-stazionali-tabs-second.component.html',
  styleUrls: ['./dati-stazionali-tabs-second.component.css']
})
export class DatiStazionaliTabsSecondComponent implements OnInit, OnDestroy {

  @Input() id: number;
  @Input() grayPanel: AreaDiSaggio;

  @ViewChild("container") container: ElementRef;

  datiStazionaliSecondForm: AreaDiSaggio;
  secondStepsForm: FormGroup;
  destinazione$: Observable<SelectItem[]>;
  intervento$: Observable<SelectItem[]>;
  tipologiaDiEsbosco$: Observable<SelectItem[]>;
  prioritaIntervento$: Observable<SelectItem[]>;
  dannoPrevalente$: Observable<SelectItem[]>;
  componentNames = ["codiceADS", "destinazione", "intervento", "priorita", "esbosco", "mdp", "dannoPrevalente", "intesitaDanni", "nPianteMorte", "defogliazione", "ingiallimento", "pascolamento"];

  @Output() datiStazionaliSecondEmitter = new EventEmitter<void>();


  unsubscribe$: Subject<void> = new Subject<void>();

  constructor(private fb: FormBuilder, private stepsService: StepsService, private forestaleService: ForestaleSampleService,private router: Router) { }

  ngOnInit() {
    this.fillDropdowns();
    this.stepsService.getDatiStazionaliSecond(this.id)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe((response: AreaDiSaggio) => {
      this.datiStazionaliSecondForm = response;
      this.buildSecondForm();
      
      
    });
    }

  resetForm() {
    this.buildSecondForm();
    this.container.nativeElement.scrollIntoView({
      behavior: "smooth",
      block: "start"
    });
  }

  sendSecondForm() {
    let secondFormData: AreaDiSaggio = this.secondStepsForm.getRawValue();
    secondFormData.idgeoPtAds = this.id;
    this.stepsService.sendDatiStazionaliSecond(secondFormData)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (response) => {
        this.datiStazionaliSecondEmitter.emit(response.payload);
      }
    );
  }

  
  buildSecondForm() {
    this.secondStepsForm = this.fb.group({
      codiceADS: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm.codiceADS : '', ConsolidaValidator.required()],
      destinazione: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm.destinazione : '', [ConsolidaValidator.required(), Validators.maxLength(4)]],
      intervento: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['tipoIntervento'] : '', [ConsolidaValidator.required()]],
      priorita: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm.priorita : '', [ConsolidaValidator.required()]],
      esbosco: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['codEsbosco'] : '', [ConsolidaValidator.required()]],
      defp: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['distEsboscoFuoriPista'] : '', [Validators.maxLength(4) , Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      desp: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['distEsboscoSuPista'] : '', [Validators.maxLength(4), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      mdp: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['minDistanzaPlanimetrica'] : '', [ConsolidaValidator.required(),Validators.maxLength(4), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      dannoPrevalente: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['danno'] : '', [ConsolidaValidator.required()]],
      intesitaDanni: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['danniPerc'] : '', [ConsolidaValidator.required(), Validators.maxLength(3),Validators.pattern(CONST.PATTERN_ZERO)]],
      nPianteMorte: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['nrPianteMorte'] : '', [ConsolidaValidator.required(), Validators.maxLength(2), Validators.pattern(CONST.PATTERN_ZERO)]],
      defogliazione: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['percDefogliazione'] : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_ZERO)]],
      ingiallimento: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['percIngiallimento'] : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_ZERO)]],
      pascolamento: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['flgPascolamento'] : '', [ConsolidaValidator.required(), Validators.pattern(CONST.PATTERN_ZERO)]],
      note: [this.datiStazionaliSecondForm ? this.datiStazionaliSecondForm['note'] : '']
    });
    this.validateAllFields();
  }

  fillDropdowns() {
    this.destinazione$ = this.stepsService.getDestinazione();
    this.intervento$ = this.stepsService.getTipoIntervento();
    this.tipologiaDiEsbosco$ = this.stepsService.getEsbosco();
    this.prioritaIntervento$ = this.stepsService.getPrioritaIntervento();
    this.dannoPrevalente$ = this.stepsService.getDannoPrevalente();
  }
  navigateToSearch() {
    this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }
  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  tooltipProperty ={
    defp:"Distanza di esbosco fuori pista",
    desp:"Distanza di esbosco su pista",
    mdp:"Minima distanza planimetrica"
 }

 validateAllFields() {
  if (ConsolidaValidator.valid) {
    this.componentNames.forEach( componentName => {
      if (this.secondStepsForm.controls[componentName])  {
        this.secondStepsForm.controls[componentName].markAsTouched();
        this.secondStepsForm.controls[componentName].markAsDirty();
        this.secondStepsForm.controls[componentName].updateValueAndValidity();
      }
    });
  }
}
}
