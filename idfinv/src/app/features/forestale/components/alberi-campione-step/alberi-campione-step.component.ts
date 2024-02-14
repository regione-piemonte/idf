/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { StepsService } from 'src/app/services/steps.service';
import { AlberiCampioneDominante } from 'src/app/shared/models/alberi-campione-dominante';
import { SelectItem } from 'primeng/components/common/selectitem';
import { Observable } from 'rxjs';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { CONST } from 'src/app/shared/constants';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { DialogButtons, ButtonType, DialogIconType } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';
import { Router } from '@angular/router';
import { StatoAdsEnum } from 'src/app/shared/models/stato-ads.enum';
import { OverlayPanel } from 'primeng/overlaypanel';
import { StepErrorsReport } from 'src/app/shared/models/consolida-scheda-error-response';

@Component({
  selector: 'app-alberi-campione-step',
  templateUrl: './alberi-campione-step.component.html',
  styleUrls: ['./alberi-campione-step.component.css']
})
export class AlberiCampioneStepComponent implements OnInit {

  @Input() id: number;
  @Input() grayPanel: AreaDiSaggio;
  
  @Output() thirdFormEmitter = new EventEmitter<void>();
  @Output() resetAlberiCampione: EventEmitter<void> = new EventEmitter<void>();

  tipoCampione: any[];
  campioneLabels: any;
  campioneData: AlberiCampioneDominante[];
  campioneForm: FormGroup;
  specieList$: Observable<SelectItem[]>;

  qualitaDropdown: SelectItem[] = [{value: 'P', label: 'P'}, {value: 'S', label: 'S'}];

  constructor(private stepsService: StepsService, private fb: FormBuilder, 
      private dialogService: DialogService,private router: Router) { }

  ngOnInit() {
    this.stepsService.getTipoCampione().subscribe(
      (response: any[]) => {
        this.tipoCampione = response;
        this.stepsService.getAlberiCampioneStep(this.id).subscribe(
          (response: any) => {
            this.campioneData = response;
            this.filterByType();
          }
        );
        this.specieList$ = this.stepsService.getSpecie(true);
      }
    );
    
  }

  navigateToSearch() {
    this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }

  sendThirdForm() {
    const campForm: AlberiCampioneDominante[] = [];
    for(const field in this.campioneForm.controls) {
      campForm.push(...this.campioneForm.get(field).value);
    }
    let errors = null;
    campForm.forEach((item, index) => {
      campForm[index]['idgeoPtAds'] = +this.id;
      
      if(!campForm[index].idSpecie 
        && (campForm[index].qualita || campForm[index].diametro 
          || campForm[index].incremento || campForm[index].eta)){
              this.dialogService.showDialog(true, 'Valorizzare il campo "Specie" per "'+ 
              this.getDescriptionTipoCampione(campForm[index].codTipoCampione) + '"!', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
                if (buttonId === ButtonType.OK_BUTTON) {
                }
              }, DialogIconType.WARNING);
              errors = true;
      }
    });
    let idSpecies = campForm.map(item => item.idSpecie);

      
    
    //errors
    // })) {
    //   this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
    //     if (buttonId === ButtonType.OK_BUTTON) {
    //     }
    //   }, DialogIconType.WARNING);
    // }
    if(!errors){
      this.stepsService.sendAlberiCampioneStep(campForm, this.grayPanel.lastCompletedStep === 2 ? true : false).subscribe(
        (response) => {
          this.thirdFormEmitter.emit(response);
        }
      );
      }
   
  }

  getDescriptionTipoCampione(code:string){
    if(code == 'DOM') return 'Dominante';
    else if(code == 'CAM') return 'Campione principale';
    else if(code == 'CS1') return 'Campione S1';
    else if(code == 'CS2') return 'Campione S2';
  }

  filterByType() {
    this.campioneLabels = this.campioneData.reduce((acc, cur) => {
      acc[cur.codTipoCampione] = cur;
      return acc;
    }, {});
    this.buildForm();
  }

  buildForm() {
    this.campioneForm = this.fb.group({
      DOM: this.fb.array([]),
      CAM: this.fb.array([]),
      CS1: this.fb.array([]),
      CS2: this.fb.array([])
    });
    if(this.tipoCampione) {
      this.tipoCampione.forEach(item => {
        (this.campioneForm.get(item.codTipoCampione) as FormArray).push(this.buildTableRows(this.campioneLabels[item.codTipoCampione]));
        (this.campioneForm.get(item.codTipoCampione) as FormArray).patchValue([{codTipoCampione: item.codTipoCampione}]);
      });
    }
  }

  buildTableRows(item) {
    return this.fb.group({
      idSpecie: [item ? item.idSpecie : '', [ConsolidaValidator.required()]],
      qualita: [item ? item.qualita : '', [ConsolidaValidator.required()]],
      diametro: [item ? item.diametro : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_ZERO)]],
      altezza: [item ? item.altezza : '', [ConsolidaValidator.required(),Validators.maxLength(2), Validators.pattern(CONST.PATTERN_ZERO)]],
      incremento: [item ? item.incremento : '', [ConsolidaValidator.required(),Validators.maxLength(2), Validators.pattern(CONST.PATTERN_ZERO)]],
      eta : [item ? item.eta : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_ZERO)]],
      codTipoCampione: [item ? item.codTipoCampione : '', [ConsolidaValidator.required(), Validators.pattern(CONST.PATTERN_ZERO)]]
    });
  } 
  tooltipProperty ={
    specieList:"Seleziona specie list", 
    codTipoCampione:"Seleziona cod tipo campione", 
    qualita:"Seleziona qualita", 
    diametro:"Inserire numero diametro",
    tipoCampione:"Inserire numero tipo campione",
    incremento:"Inserire numero incremento",
    eta:"Inserire numero eta"
 }


showError(tipocampione:FormGroup,error:any,name:string, ev:any){
  if(tipocampione.get(name).errors && (tipocampione.get(name).touched && tipocampione.get(name).errors.maxlength || 
  tipocampione.get(name).touched && tipocampione.get(name).errors.pattern ||
  tipocampione.get(name).touched && tipocampione.get(name).errors.required)) {
   
   
    error.show(ev);
   } 
 }
 
}
