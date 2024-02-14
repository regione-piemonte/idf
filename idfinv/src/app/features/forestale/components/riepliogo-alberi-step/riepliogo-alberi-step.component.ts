/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { StepsService } from 'src/app/services/steps.service';
import { SelectItem } from 'primeng/components/common/selectitem';
import { CONST } from 'src/app/shared/constants';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons, DialogIconType } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Router } from '@angular/router';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';

@Component({
  selector: 'app-riepliogo-alberi-step',
  templateUrl: './riepliogo-alberi-step.component.html',
  styleUrls: ['./riepliogo-alberi-step.component.css']
})
export class RiepliogoAlberiStepComponent implements OnInit {

  secondStepForm: FormGroup;
  @Input() specieList: SelectItem[];
  dettaglioFormData: any;
  @Input('dettaglioFormInput')
  set dettaglioFormInput(val) {
    this.dettaglioFormData = val;
    this.secondStepForm && this.filterCavalettatiForm();
  }
  @Input() id: number;
  @Input() test: SelectItem[];
  @Output() emitRemoveRow: EventEmitter<number> =  new EventEmitter();
  @Output() emitAddSecondItem: EventEmitter<void> =  new EventEmitter();
  @Output() emitSecondForm = new EventEmitter<void>();
  filteredSpecieList: SelectItem[];
  riepliogoArray: FormArray;
  tipologiaDropdown: SelectItem[] = [{value: 'S', label: 'S'}, {value: 'P', label: 'P'}];
  consolidaDialog:boolean;

  constructor(private stepsService: StepsService, private fb: FormBuilder, private dialogService: DialogService,private router: Router) { }


  riepliogoForm(item?) {
    return this.fb.group({
      idSpecie: [item ? item.idSpecie : '', [Validators.required]],
      flgPolloneSeme: [item ? item.flgPolloneSeme : '', [Validators.required]],
      diametro: [item ? item.diametro : '', [Validators.required,Validators.maxLength(3), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      altezza: [item ? item.altezza : '', [Validators.required,Validators.maxLength(2), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      incremento: [item ? item.incremento : '', [Validators.maxLength(2), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]]
    });
  }


  ngOnInit() {
   this.stepsService.getRelascopica(this.id).subscribe(
      (response: any) => {
        this.buildSecondForm(response);
      }
    ); 
  }
showDialog(){
  this.consolidaDialog=true;
}
closeDialog(){
  this.consolidaDialog=false;
}
  navigateToSearch() {
    this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }
  filterCavalettatiForm() {
    let noOfDeleted = 0;
    const secondFormValue = {...this.secondStepForm.value};
    secondFormValue.riepliogo.forEach((item, index) => {
      const ind = this.dettaglioFormData.plainAdsrelSpecie.findIndex(el => {
        return el.idSpecie === item.idSpecie;
      });

      if (ind === -1) {
        this.deleteFiledValue(index-noOfDeleted);
        noOfDeleted++;
      }
    });
    
  }


  buildSecondForm(dettaglioForm: any) {
    this.secondStepForm = this.fb.group({
      riepliogo : this.fb.array([], [Validators.required])
    });
    if(dettaglioForm.plainAdsrelSpecie) {
      dettaglioForm.plainAdsrelSpecie.forEach(item => {
        if(item.diametro !== 0 || item.altezza !== 0 || item.incremento !== 0 || item.flgPolloneSeme) {
          (this.secondStepForm.get('riepliogo') as FormArray).push(this.riepliogoForm(item));
        }
      });
    } else {
      (this.secondStepForm.get('riepliogo') as FormArray).push(this.riepliogoForm());
    }
  }

  addSecondItem() {
    this.riepliogoArray = this.secondStepForm.get('riepliogo') as FormArray;
    this.riepliogoArray.push(this.riepliogoForm()); 
  };

  deleteFiledValue(index: number) {
    (this.secondStepForm.get('riepliogo') as FormArray).removeAt(index);
  }

  sendSecondForm() {
    let firstForm = this.dettaglioFormData;
    let secondForm = this.secondStepForm.value;
    const mergeObjects = (a1, a2) => 
    a1.map(item => ({
      ...a2.find((el) => (el.idSpecie === item.idSpecie) && el),
      ...item
    }));
    let sendingObj = {...firstForm, ...secondForm};
    delete sendingObj.plainAdsrelSpecie;
    delete sendingObj.riepliogo;
    let sending = false;
    sendingObj['plainAdsrelSpecie'] = mergeObjects(firstForm.plainAdsrelSpecie, secondForm.riepliogo);
    const idSpecie = this.secondStepForm.value.riepliogo.map(item => item.idSpecie);
    this.secondStepForm.value.riepliogo.filter(item => {
      if (Object.keys(item).every(function(x) { return item[x]===''||item[x]===null;}) === false) { 
        if(idSpecie.some((item, index) => {
          return idSpecie.indexOf(item) !== index
        })) {
          sending = false;
          this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
            }
          }, DialogIconType.WARNING);
        } else {
          sending = true;
        }
    } else {
        this.dialogService.showDialog(true, 'La specie è vuota', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
          if (buttonId === ButtonType.OK_BUTTON) {
          }
        }, DialogIconType.WARNING);
      }
    });
    
    sending && this.stepsService.postRelascopica(sendingObj).subscribe(response => {
      this.dialogService.showDialog(true, 'Salvato con successo', 'Success', DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
        }
      }, DialogIconType.SUCCESS);
    });
  }

  consolidaScheda() {
    ConsolidaValidator.valid=true;
    let firstForm = this.dettaglioFormData;
    let secondForm = this.secondStepForm.value;
    const mergeObjects = (a1, a2) => 
    a1.map(item => ({
      ...a2.find((el) => (el.idSpecie === item.idSpecie) && el),
      ...item
    }));
    let sendingObj = {...firstForm, ...secondForm};
    delete sendingObj.plainAdsrelSpecie;
    delete sendingObj.riepliogo;
    let sending = false;
    sendingObj['plainAdsrelSpecie'] = mergeObjects(firstForm.plainAdsrelSpecie, secondForm.riepliogo);
    const idSpecie = this.secondStepForm.value.riepliogo.map(item => item.idSpecie);
    this.secondStepForm.value.riepliogo.filter(item => {
      if (Object.keys(item).every(function(x) { return item[x]===''||item[x]===null;}) === false) { 
        if(idSpecie.some((item, index) => {
          return idSpecie.indexOf(item) !== index
        })) {
          sending = false;
          this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
            }
          }, DialogIconType.WARNING);
        } else {
          sending = true;
        }
    } else {
        this.dialogService.showDialog(true, 'La specie è vuota', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
          if (buttonId === ButtonType.OK_BUTTON) {
          }
        }, DialogIconType.WARNING);
      }
    });
    
    sending && this.stepsService.consolidaRelascopica(sendingObj).subscribe(response => {
      this.dialogService.showDialog(true, 'Scheda consolidata correttamente', 'Consolida', DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          if(sessionStorage.getItem('lastSearch')){
            this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
          }else{
            this.router.navigate(['/forestale', 'links']);
          }
        }
      }, DialogIconType.SUCCESS);
    });
    this.closeDialog();
  }
  showError(tipocampione:FormGroup,error:any,name:string, ev:any){
      
    if(tipocampione.get(name).errors && (tipocampione.get(name).touched && tipocampione.get(name).errors.maxlength || 
    tipocampione.get(name).touched && tipocampione.get(name).errors.pattern ||
    tipocampione.get(name).touched && tipocampione.get(name).errors.required)) {
     
     
      error.show(ev);
     } 
   }
  
}
