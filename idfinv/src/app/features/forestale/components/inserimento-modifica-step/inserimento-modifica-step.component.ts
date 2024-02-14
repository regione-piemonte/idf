/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { AreaDiSaggio } from 'src/app/shared/models/area-di-saggio';
import { StepsService } from 'src/app/services/steps.service';
import { Observable } from 'rxjs';
import { SelectItem } from 'primeng/components/common/selectitem';
import { e } from '@angular/core/src/render3';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons, DialogIconType } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Router } from '@angular/router';
import { Tipologia } from 'src/app/shared/models/tipoEnum';
import { TipologiaPanelComponent } from 'src/app/shared/components';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';
import { CONST } from 'src/app/shared/constants';

@Component({
  selector: 'app-inserimento-modifica-step',
  templateUrl: './inserimento-modifica-step.component.html',
  styleUrls: ['./inserimento-modifica-step.component.css']
})
export class InserimentoModificaStepComponent implements OnInit {

  firstForm: FormGroup;
  @Input() grayPanel: AreaDiSaggio;
  @Input() id: number;
  sempliceTipologia: number = Tipologia.RELASCOPICA_SEMPLICE;/*Relascopica semplice*/
  completaTipologia: number = Tipologia.RELASCOPICA_COMPLETA/*Relascopica completa*/
  @Output() emitFirstForm = new EventEmitter<void>();
  conteggioAngolareArray: FormArray;
  @Input() specieList: SelectItem[];
  relascopica: any;
  consolidaDialog:boolean;

  constructor(private fb: FormBuilder, private stepsService: StepsService, private dialogService: DialogService, private router: Router) { }

  ngOnInit() {
    this.stepsService.getRelascopica(this.id).subscribe(
      (response: any) => {
        this.relascopica = response;
        this.buildFirstForm(response);
      }
    );
    this.stepsService.dettaglioStep.subscribe(response => { 
      this.sendFirstStep();
    });
  }
  showDialog(){
    this.consolidaDialog=true;
  }
  closeDialog(){
    this.consolidaDialog=false;
  }
  deleteFiledValue(index: number) {
    (this.firstForm.get('plainAdsrelSpecie') as FormArray).removeAt(index);
  }


  sendFirstStep(permanente?: boolean) {
    if(permanente) {
      let sendingObj = this.firstForm.value.plainAdsrelSpecie;
      const idSpecie = sendingObj.map(item => item.idSpecie);
      let sending = false;
      sendingObj.filter(item => {
        if (Object.keys(item).every(function(x) { return item[x]===''||item[x]===null;}) === false) { 
          if(idSpecie.some((item, index) => {
            return idSpecie.indexOf(item) !== index
          })) {
            this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
              if (buttonId === ButtonType.OK_BUTTON) {
              }
            }, DialogIconType.WARNING);
          } else {
            sending = true;
          }
      } else {
          sending = true;
          // this.dialogService.showDialog(true, 'La specie è vuota', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
          //   if (buttonId === ButtonType.OK_BUTTON) {
          //   }
          // }, DialogIconType.WARNING);
      }
      
      })
      if(sending) {
        let dettaglio = this.firstForm.value;
        dettaglio['idgeoPtAds'] = this.id;
        dettaglio['fkTipoStrutturalePrinc'] = 0;
        this.stepsService.postRelascopica(dettaglio).subscribe(response => { 
          this.dialogService.showDialog(true, 'Salvato con successo', 'Success', DialogButtons.OK, '', (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
            }
          }, DialogIconType.SUCCESS);
        });
      }
    } else if(!permanente) {
      this.sendDettaglio();
    }
  }

  sendDettaglio() {
      let sendingObj = this.firstForm.value.plainAdsrelSpecie;
      const idSpecie = sendingObj.map(item => item.idSpecie);
      let sending = false;
      sendingObj.filter(item => {
        if (Object.keys(item).every(function(x) { return item[x]===''||item[x]===null;}) === false) { 
          if(idSpecie.some((item, index) => {
            return idSpecie.indexOf(item) !== index
          })) {
            this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
              if (buttonId === ButtonType.OK_BUTTON) {
              }
            }, DialogIconType.WARNING);
          } else {
            sending = true;
          }
      } else {
          this.dialogService.showDialog(true, 'La specie è vuota ', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
            }
          }, DialogIconType.WARNING);
        }
      });
      if(sending) {
        let dettaglio = this.firstForm.value;
        dettaglio['idgeoPtAds'] = this.id;
        dettaglio['fkTipoStrutturalePrinc'] = 0;
        this.stepsService.emitDettaglioForm(dettaglio);
      }
  }

  resetForm() {
    this.buildFirstForm(this.relascopica);
  }
  navigateToSearch() {
    this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }

  buildFirstForm(dettaglio: any) {
    this.firstForm = this.fb.group({
      fattoreNumerazione: [dettaglio ? dettaglio.fattoreNumerazione : '', [ConsolidaValidator.required(),Validators.maxLength(1), Validators.pattern(CONST.PATTERN_FATTORE_RELASCOPICA)]],
      note: [dettaglio ? dettaglio.note : ''],
      plainAdsrelSpecie: this.fb.array([])
    });
    if(dettaglio.plainAdsrelSpecie) {
      dettaglio.plainAdsrelSpecie.forEach(item => {
        (this.firstForm.get('plainAdsrelSpecie') as FormArray).push(this.createConteggioAngolare(item));
      });
    } else {
      (this.firstForm.get('plainAdsrelSpecie') as FormArray).push(this.createConteggioAngolare());
    }
    
  }

  createConteggioAngolare(item?): FormGroup {
    return this.fb.group({
      idSpecie: [item ? item.idSpecie : '', [Validators.required]],
      nrAlberiSeme: [item ? item.nrAlberiSeme : '', [Validators.maxLength(6), Validators.pattern(CONST.PATTERN_DECIMAL)]],
      nrAlberiPollone: [item ? item.nrAlberiPollone : '', [Validators.maxLength(6), Validators.pattern(CONST.PATTERN_DECIMAL
        )]]
    });
  }

  showError(tipocampione:FormGroup,error:any,name:string, ev:any){
      
    if(tipocampione.get(name).errors && (tipocampione.get(name).touched && tipocampione.get(name).errors.maxlength || 
    tipocampione.get(name).touched && tipocampione.get(name).errors.pattern ||
    tipocampione.get(name).touched && tipocampione.get(name).errors.required)) {
     
     
      error.show(ev);
     } 
   }

  addItem() {
		this.conteggioAngolareArray = this.firstForm.get('plainAdsrelSpecie') as FormArray;
    this.conteggioAngolareArray.push(this.createConteggioAngolare());
  }

  consolidaScheda() {
    this.closeDialog();
    let firstForm = this.firstForm.value;
    const mergeObjects = (a1, a2) => 
    a1.map(item => ({
      ...a2.find((el) => (el.idSpecie === item.idSpecie) && el),
      ...item
    }));
    let sendingObj = {...firstForm};
    // delete sendingObj.plainAdsrelSpecie;
    // delete sendingObj.riepliogo;
    sendingObj.idgeoPtAds = this.id;
    let sending = false;
    // sendingObj['plainAdsrelSpecie '] = mergeObjects(firstForm.plainAdsrelSpecie, firstForm.riepliogo);
    const idSpecieList = this.firstForm.value.plainAdsrelSpecie.map(item => item.idSpecie);
    if(!this.isConteggioAlberiOK()){
      return;
    }
    this.firstForm.value.plainAdsrelSpecie.filter(item => {
      
      if (Object.keys(item).every(function(x) { return item[x]===''|| item[x]===null;}) === false) { 
        if(idSpecieList.some((item, index) => {
          return idSpecieList.indexOf(item,index+1) >= 0;
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

  isConteggioAlberiOK(){
    let conteggi = this.firstForm.value.plainAdsrelSpecie;
    for(var i in conteggi){
      if((conteggi[i]['nrAlberiPollone'] == 0 || conteggi[i]['nrAlberiPollone'] == '' || conteggi[i]['nrAlberiPollone'] == null) 
          && (conteggi[i]['nrAlberiSeme'] == 0 || conteggi[i]['nrAlberiSeme'] == '' || conteggi[i]['nrAlberiSeme'] == null)){
            this.dialogService.showDialog(true, 'Valorizzare almeno uno tra "N. Alberi contati seme" e "N. Alberi contati polloni"', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
              if (buttonId === ButtonType.OK_BUTTON) {
              }
            }, DialogIconType.WARNING);
            return false;
          }
    }
    return true;
  }

  tooltipProperty ={
    fattoreNumerazione:"Inserire il numero fattore numerazione",
    specie:"Seleziona specie list", 
    nrAlberiSeme:"Inserire il numero N. Alberi contati seme",
    nrAlberiPollone:"Inserire il numero N. Alberi contati pollone",
    add:"aggiungi un altro modulo",
    remove:"rimuovi questo modulo"
 }

}
