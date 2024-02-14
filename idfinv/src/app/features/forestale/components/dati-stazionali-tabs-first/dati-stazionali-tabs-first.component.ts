/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { SelectItem } from 'primeng/components/common/selectitem';
import { ForestaleSampleService } from 'src/app/shared/services/forestale-sample.service';
import { Subject, Observable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { StepsService } from 'src/app/services/steps.service';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { CONST } from 'src/app/shared/constants';
import { Router } from '@angular/router';
import { NullInjector } from '@angular/core/src/di/injector';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';
import { StepErrorsReport } from 'src/app/shared/models/consolida-scheda-error-response';
import { resolve } from 'url';

@Component({
  selector: 'app-dati-stazionali-tabs-first',
  templateUrl: './dati-stazionali-tabs-first.component.html',
  styleUrls: ['./dati-stazionali-tabs-first.component.css']
})
export class DatiStazionaliTabsFirstComponent implements OnInit, OnDestroy {

  @Input() id: number;
  @Input() grayPanel: AreaDiSaggio;

  @Output() emitResetForm: EventEmitter<boolean> = new EventEmitter();
  @Output() emitFirstForm: EventEmitter<boolean> = new EventEmitter();
  @Output() emitFillTipoForestale = new EventEmitter<void>();
  
  @ViewChild("container") container: ElementRef;

  stepsForm: FormGroup;
  categoriaForstale$: Observable<SelectItem[]>;
  tipoForestale$: Observable<SelectItem[]>;
  colturale$: Observable<SelectItem[]>;
  tipoStrutturale$: Observable<SelectItem[]>;
  sviluppo$: Observable<SelectItem[]>;
  specie$: Observable<SelectItem[]>;
  classeDiFertilita$: Observable<SelectItem[]>
  tipoForestaleList: SelectItem[];
  datiStazionaliFirst: AreaDiSaggio;

  componentNames = ["densitaCamp", "raggioArea", "categoriaForestale", "tipoForestale", "assettoEvolutivoColturale", "tipoStrutturale", "stadioDiSviluppo", "idClasseDiFertilita", "nCepaie", "speciePrevalenteRinnovazione", "rinnovazione", "coperturaChiome", "coperturaErbacea", "lettiera", "coperturaCespugli"];

  @Output() datiStazionaliFirstEmitter = new EventEmitter<StepErrorsReport[]>();

  unsubscribe$ = new Subject<void>();

  constructor(private fb: FormBuilder, private forestaleService: ForestaleSampleService, private stepsService: StepsService, private router: Router) { }

  ngOnInit() {
    this.fillDropdowns();
    this.stepsService.getAreaDiSaggio(this.id).subscribe(
      (response: AreaDiSaggio) => {
        this.datiStazionaliFirst = response;
        this.buildFirstForm();
      }
    );
  }

  buildFirstForm() {
    this.stepsForm = this.fb.group({
      densitaCamp: [this.datiStazionaliFirst.densitaCamp ? this.datiStazionaliFirst.densitaCamp : '', [ConsolidaValidator.required(),Validators.maxLength(5), Validators.pattern(CONST.PATTERN_DECIMAL_1)]],
      raggioArea: [this.datiStazionaliFirst.raggioArea ? this.datiStazionaliFirst.raggioArea : '', [ConsolidaValidator.required(),Validators.maxLength(4), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      categoriaForestale: [this.datiStazionaliFirst.categoriaForestale ? this.datiStazionaliFirst.categoriaForestale : '', ConsolidaValidator.required()],
      tipoForestale: [{ value: this.datiStazionaliFirst.tipoForestale ? this.datiStazionaliFirst.tipoForestale : '', disabled: this.datiStazionaliFirst.categoriaForestale ? false : true }, ConsolidaValidator.required()],
      assettoEvolutivoColturale: [this.datiStazionaliFirst.assettoEvolutivoColturale ? this.datiStazionaliFirst.assettoEvolutivoColturale : '', ConsolidaValidator.required()],
      tipoStrutturale: [this.datiStazionaliFirst.tipoStrutturale ? this.datiStazionaliFirst.tipoStrutturale : '', ConsolidaValidator.required()],
      stadioDiSviluppo: [this.datiStazionaliFirst.stadioDiSviluppo ? this.datiStazionaliFirst.stadioDiSviluppo : '', ConsolidaValidator.required()],
      idClasseDiFertilita: [this.datiStazionaliFirst.idClasseDiFertilita ? this.datiStazionaliFirst.idClasseDiFertilita : '', [ConsolidaValidator.required(), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      nCepaie: [this.datiStazionaliFirst.nCepaie ? this.datiStazionaliFirst.nCepaie : '', [ConsolidaValidator.required(), Validators.maxLength(2), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      speciePrevalenteRinnovazione: [this.datiStazionaliFirst.speciePrevalenteRinnovazione ? this.datiStazionaliFirst.speciePrevalenteRinnovazione : '', [ConsolidaValidator.required()]],
      rinnovazione: [this.datiStazionaliFirst.rinnovazione ? this.datiStazionaliFirst.rinnovazione : '', [Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      coperturaChiome: [this.datiStazionaliFirst.coperturaChiome ? this.datiStazionaliFirst.coperturaChiome : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      coperturaErbacea: [this.datiStazionaliFirst.coperturaErbacea ? this.datiStazionaliFirst.coperturaErbacea : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      lettiera: [this.datiStazionaliFirst.lettiera ? this.datiStazionaliFirst.lettiera : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      coperturaCespugli: [this.datiStazionaliFirst.coperturaCespugli ? this.datiStazionaliFirst.coperturaCespugli : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]]
    });
    this.fillTipoForestale();
    this.validateAllFields();
  }

  validateAllFields() {
    if (ConsolidaValidator.valid) {
      this.componentNames.forEach( componentName => {
        if (this.stepsForm.controls[componentName])  {
          this.stepsForm.controls[componentName].markAsTouched();
          this.stepsForm.controls[componentName].markAsDirty();
          this.stepsForm.controls[componentName].updateValueAndValidity();
        }
      });
    }
  }

  fillDropdowns() {
    this.categoriaForstale$ = this.forestaleService.getAllCategoriaForestale();
    this.colturale$ = this.stepsService.getAsseto();
    this.classeDiFertilita$ = this.stepsService.getClasseDiFertilita();
    this.tipoStrutturale$ = this.stepsService.getTipoStrutturale();
    this.sviluppo$ = this.stepsService.getStadioSviluppo();
    this.tipoForestale$ = this.forestaleService.getTipoForestale();
    this.specie$ = this.stepsService.getSpecie();
  }
  
  fillTipoForestale() {
    if(this.stepsForm.get('categoriaForestale').value === null) {
      this.stepsForm.get("tipoForestale").disable();
      this.stepsForm.get("tipoForestale").patchValue(""); 
    } else {
      this.stepsService.getTipoForestaleByCategoria(this.stepsForm.get("categoriaForestale").value)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response: SelectItem[]) => {
        this.tipoForestaleList = response;
        this.stepsForm.get("tipoForestale").enable();
      });
    }
  }

  resetForm() {
    this.buildFirstForm();
    this.container.nativeElement.scrollIntoView({
      behavior: "smooth",
      block: "start"
    });
  }

  sendFirstForm() {
    let firstFormData: AreaDiSaggio = this.stepsForm.getRawValue();
    firstFormData.idgeoPtAds = this.datiStazionaliFirst.idgeoPtAds;
    firstFormData.codiceADS = this.id.toString();
    firstFormData.tipologia = this.grayPanel.descrTipoAds;
    // delete firstFormData.classeDiFertilita;
    this.stepsService.sendDatiStazionaliFirst(firstFormData).subscribe(
      (response) => {
        this.datiStazionaliFirstEmitter.emit(response.payload);
      }
    );
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
    densitaCamp:"Superficie boscata rappresentata dal rilievo", 
    rinnovazione: "Soggetti di novellame d’avvenire (diametro <7,5cm) di specie forestalie"
 }

}


      