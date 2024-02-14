/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TableHeader } from 'src/app/shared/models/table-header';
import { Specie } from 'src/app/shared/models/specie';
import { StepsService } from 'src/app/services/steps.service';
import { SelectItem } from 'primeng/components/common/selectitem';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { CONST } from 'src/app/shared/constants';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons, DialogIconType } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Router } from '@angular/router';
import { ErrorCodes } from 'src/app/shared/models/error-codes';
import { StepErrorsReport } from 'src/app/shared/models/consolida-scheda-error-response';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';
import { Dropdown } from 'primeng/dropdown';

@Component({
  selector: 'app-alberi-cavallettati-step',
  templateUrl: './alberi-cavallettati-step.component.html',
  styleUrls: ['./alberi-cavallettati-step.component.css']
})
export class AlberiCavallettatiStepComponent implements OnInit {

  @Input() id: number;
  @Input() stepsErrors:StepErrorsReport[];
  specieList: SelectItem[][] = [];
  codiceList: SelectItem[][] = [];
  gruppoList: SelectItem[] = [];
  alberiCavallettatiForm: FormGroup;
  alberiArray: FormArray;
  globalSpecie: Specie[];
  alberiCavallettati: any;
  semePolloneArray: SelectItem[] = [{ label: 'P', value: 'P' }, { label: 'S', value: 'S' }];

  @Output() fourthFormEmitter = new EventEmitter<void>();
  @Output() consolidationError = new EventEmitter<StepErrorsReport[]>();
  @Output() consolidationSuccess = new EventEmitter<void>();
  consolidaDialog: boolean =false;
  constructor(private stepsService: StepsService, private fb: FormBuilder, private dialogService: DialogService,private router: Router) { }

  ngOnInit() {
    this.stepsService.getGruppo()
    .subscribe(
      (response => {
        response.forEach(item => {
          this.gruppoList.push({value: item.codGruppo, label: item.codGruppo})
        })
      })
    )
    // this.stepsService.getSpecie(true).subscribe(
    //   (response: SelectItem[]) => {
    //     this.specieList = response;
    //   }
    // );
    // this.stepsService.getSpecieAll(true).subscribe(
    //   (response: Specie[]) => {
    //     this.globalSpecie = response;
    //     response.forEach((item, index) => {
    //       this.specieList[0].push({value: item.idSpecie, label: item.latino}); 
    //       this.codiceList[0].push({value: item.codice, label: item.codice}); 
    //       this.gruppoList.push({value: item.codGruppo, label: item.codGruppo}); 
    //     });
    //   }
    // );
    this.stepsService.getAlberiCavallettatiStep(this.id).subscribe(
      (response: any) => {
        this.alberiCavallettati = response;
        const alberiLength = response.length;
        this.stepsService.getSpecieAll(true).subscribe(
          (res: Specie[]) => {
            this.globalSpecie = res;
            for(let i = 0; i < alberiLength; i++ ) {
              this.specieList[i] = [];
              this.codiceList[i] = [];
              res.forEach((item, index) => {
                this.specieList[i].push({value: item.idSpecie, label: item.codice + ' - ' + item.volgare}); 
                this.codiceList[i].push({value: item.idSpecie, label: item.codice}); 
              });
            }
            this.buildAlberiForm(response);

          }
        );
        
      }
    );
    
  }

  showDialog(){
    this.consolidaDialog=true;
  }
  closeDialog(){
    this.consolidaDialog=false;
  }
  emitEditModal() {
  }

  deleteFiledValue(index: number) {
    
    (this.alberiCavallettatiForm.get('cavallettati') as FormArray).removeAt(index);
    // delete this.codiceList[index];
    // delete this.specieList[index];
    this.codiceList.splice(index, 1);
    this.specieList.splice(index, 1);
  }

  sendFourthForm() {
    if(this.checkFormOk()){
      const cavallettatiArray = [...this.alberiCavallettatiForm.value.cavallettati];
      cavallettatiArray.forEach((item, i) => {
        delete item.gruppo;
        delete item.codice;
      });
      
      this.stepsService.sendAlberiCavallettatiStep(cavallettatiArray).subscribe(res => {
        this.showDialogMsg('Salvato con successo', 'Success',true);
      });
  }
  }
  checkErrorPreviousSteps(){
    for(let i in this.stepsErrors){
      if(this.stepsErrors[i].noOfErrors > 0){
        this.showDialogMsg('Per consolidare è neccessario risolvere gli errori sulle altre schede!', 'Error occured',false);
        return false;
      }
    }
    return true;
  }
  checkFormOk(){
    let sendingObj = this.alberiCavallettatiForm.value.cavallettati;
    // if(sendingObj.length == 0){
    //   this.showDialogMsg('Inserire almeno un elemento!','Error occured');
    //   return false;
    // }
    for(let i = 0; i<sendingObj.length; i++){
      if(sendingObj[i]['idSpecie'] == '' || sendingObj[i]['flgPolloneSeme'] == '' || sendingObj[i]['diametro'] == ''){
        this.showDialogMsg('Valorizzare tutti i campi alla riga ' + (i+1) + '!','Error occured',false);
        return false;
      }
    }
    for(let i = 0; i<sendingObj.length; i++){
      for(let j = i+1; j<sendingObj.length; j++){
        if(sendingObj[i]['idSpecie'] == sendingObj[j]['idSpecie'] && 
          sendingObj[i]['flgPolloneSeme'] == sendingObj[j]['flgPolloneSeme'] && sendingObj[i]['diametro'] == sendingObj[j]['diametro']){
            this.showDialogMsg('Non sono consentiti elementi dupplicati. Verificare riga ' 
              + (i+1) + ' e riga ' + (j+1) + '.','Error occured',false);
          return false;
        }
      }
    }
    return true;
  }

  showDialogMsg(msg,msgType,isSuccess){
    this.dialogService.showDialog(true, msg, msgType, DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
      }
    }, isSuccess?DialogIconType.SUCCESS:DialogIconType.WARNING);
  }

  // sendFourthFormOld() {
  //   let sendingObj = this.alberiCavallettatiForm.value.cavallettati;
  //   const idSpecie = sendingObj.map(item => item.idSpecie);
  //   let sending = false;
  //   sendingObj.filter(item => {
  //   if (Object.keys(item).every(function(x) { 
  //       return item[x]===''||item[x]===null;
  //     }) === false) { 
  //     if(idSpecie.some((item, index) => {
  //       return idSpecie.indexOf(item) !== index
  //     })) {
  //       this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
  //         if (buttonId === ButtonType.OK_BUTTON) {
  //         }
  //       }, DialogIconType.WARNING);
  //     } else {
  //       sending = true;
  //   //     let dettaglio = this.firstForm.value;
  //   // dettaglio['idgeoPtAds'] = this.id;
  //   // dettaglio['fkTipoStrutturalePrinc'] = 0;
  //   // this.stepsService.postRelascopica(dettaglio).subscribe(
  //   //   (response => {
        
  //   //   })
  //   // );
  //     }
  //   } else {
  //       this.dialogService.showDialog(true, 'La specie è vuota', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
  //         if (buttonId === ButtonType.OK_BUTTON) {
  //         }
  //       }, DialogIconType.WARNING);
  //     }
  //   });
  //   if(sending) {
  //     const cavallettatiArray = [...this.alberiCavallettatiForm.value.cavallettati];
  //     cavallettatiArray.forEach((item, i) => {
  //       delete item.gruppo;
  //       delete item.codice;
  //     });
      
  //     this.stepsService.sendAlberiCavallettatiStep(cavallettatiArray).subscribe(res => {
  //       this.dialogService.showDialog(true, 'Salvato con successo', 'Success', DialogButtons.OK, '', (buttonId: number): void => {
  //         if (buttonId === ButtonType.OK_BUTTON) {
  //         }
  //       }, DialogIconType.SUCCESS);
  //     });
  //   }
  // }

  /* Scheda will be submitied and consolidated */
  // consolidaSchedaOld() {
  //   ConsolidaValidator.valid = true;
  //   let sendingObj = this.alberiCavallettatiForm.value.cavallettati;
  //   const idSpecie = sendingObj.map(item => item.idSpecie);
  //   let sending = false;
  //   sendingObj.filter(item => {
  //   if (Object.keys(item).every(function(x) { return item[x]===''||item[x]===null;}) === false) { 
  //     if(idSpecie.some((item, index) => {
  //       return idSpecie.indexOf(item) !== index
  //     })) {
  //       this.dialogService.showDialog(true, 'Non sono consentiti duplicati', 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
  //         if (buttonId === ButtonType.OK_BUTTON) {
  //         }
  //       }, DialogIconType.WARNING);
  //     } else {
  //       sending = true;
  //   //     let dettaglio = this.firstForm.value;
  //   // dettaglio['idgeoPtAds'] = this.id;
  //   // dettaglio['fkTipoStrutturalePrinc'] = 0;
  //   // this.stepsService.postRelascopica(dettaglio).subscribe(
  //   //   (response => {
        
  //   //   })
  //   // );
  //     }
  //   } else {
  //       this.dialogService.showDialog(true, 'La specie è vuota', 'C\'è stato un\'errore', DialogButtons.OK, '', (buttonId: number): void => {
  //         if (buttonId === ButtonType.OK_BUTTON) {
  //         }
  //       }, DialogIconType.WARNING);
  //     }
  //   });
  //   if(sending) {
  //     const cavallettatiArray = [...this.alberiCavallettatiForm.value.cavallettati];
  //     cavallettatiArray.forEach((item, i) => {
  //       delete item.gruppo;
  //       delete item.codice;
  //     });
      
  //     this.stepsService.consolidateAlberiCavallettatiStep(cavallettatiArray).subscribe(res => {
  //       if (res.codice == ErrorCodes.ADS_CONSOLIDA_VALIDATION_ERROR) {
  //           this.consolidationError.emit(res.payload);
  //       } else {
  //         this.consolidationSuccess.emit();
  //         this.dialogService.showDialog(true, 'Scheda consolidata correttamente', 'Consolida', DialogButtons.OK, '', (buttonId: number): void => {
  //           if (buttonId === ButtonType.OK_BUTTON) { } }, DialogIconType.SUCCESS);

            
  //       }
        
  //     });
  //   }
  //   this.closeDialog();
    
  // }

  consolidaScheda() {
    ConsolidaValidator.valid = true;
    if(this.checkFormOk() && this.checkErrorPreviousSteps()) {
      const cavallettatiArray = [...this.alberiCavallettatiForm.value.cavallettati];
      cavallettatiArray.forEach((item, i) => {
        delete item.gruppo;
        delete item.codice;
      });
      this.stepsService.consolidateAlberiCavallettatiStep(cavallettatiArray).subscribe(res => {
        if (res.codice == ErrorCodes.ADS_CONSOLIDA_VALIDATION_ERROR) {
            this.consolidationError.emit(res.payload);
            this.showDialogMsg('Per consolidare è neccessario risolvere gli errori sulle altre schede!', 'Error occured',false);
        } else {
          this.consolidationSuccess.emit();
          this.dialogService.showDialog(true, 'Scheda consolidata correttamente', 'Consolida', DialogButtons.OK, '', (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              if(sessionStorage.getItem('lastSearch')){
                this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
              }else{
                this.router.navigate(['/forestale', 'links']);
              }
            }
          }, DialogIconType.SUCCESS);
        }        
      });
    }
    this.closeDialog();  
  }

  resetForm() {
    this.buildAlberiForm(this.alberiCavallettati);
  }

  buildAlberiForm(alberiData?: any) {
    this.alberiCavallettatiForm = this.fb.group({
      cavallettati: this.fb.array([])
    });
    if(alberiData) {
      alberiData.forEach((item, index) => {
        (this.alberiCavallettatiForm.get('cavallettati') as FormArray).push(this.pushAlberiItem(item));
        if((this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(index).get('idSpecie').value) {
          const specie = (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(index).get('idSpecie').value;
          this.globalSpecie.forEach(item => {
            if(specie === item.idSpecie) {
              (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(index).get('gruppo').patchValue(item.codGruppo);
            }
          });
        }
      });
    } else {
      (this.alberiCavallettatiForm.get('cavallettati') as FormArray).push(this.pushAlberiItem());

    }
  }

  pushAlberiItem(item?: any) {
    ConsolidaValidator.valid = true;
    return this.fb.group({
      idgeoPtAds: [item ? item.idgeoPtAds : this.id, [ConsolidaValidator.required()]],
      idSpecie: [item ? item.idSpecie : '', [ConsolidaValidator.required()]],
      gruppo: [item ? item.gruppo : '', [ConsolidaValidator.required()]],
      diametro: [item ? item.diametro : '', [ConsolidaValidator.required(),Validators.maxLength(3), Validators.pattern(CONST.PATTERN_NUMERIC_POSITIVE)]],
      flgPolloneSeme: [item ? item.flgPolloneSeme : '', [ConsolidaValidator.required()]],
      codTipoCampione: 'CAV',
      codice: [item ? item.idSpecie : '', ConsolidaValidator.required()]
    });
  }

  returnDropdowns(specie: boolean, codice: boolean, gruppo: boolean, index?) {
    if(specie) {
      this.specieList[index].length = 0;
    }
    if(codice) {
      this.codiceList[index].length = 0;
    }
    if(gruppo) {
      this.gruppoList.length = 0;
    }
    this.globalSpecie.forEach(item => {
      if(specie) {
        this.specieList[index].push({value: item.idSpecie, label: item.codice + ' - ' + item.volgare}); 
      }
      if(codice) {
        this.codiceList[index].push({value: item.idSpecie, label: item.codice}); 
      }
      if(gruppo) {
        this.gruppoList.push({value: item.codGruppo, label: item.codGruppo}); 
      }
    });
  }

  changeCodice(i) {
    const codice = (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('codice').value;
    if(codice) {
      this.globalSpecie.forEach(item => {
        if(codice === item.idSpecie) {
          (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('idSpecie').patchValue(item.idSpecie);
          (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('gruppo').patchValue(item.codGruppo);
        }
      });
    } else {
      this.returnDropdowns(true, false, true, i);
      (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('idSpecie').reset();
      (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('gruppo').reset();
    }
  }

  changeSpecie(i) {
    const specie = (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('idSpecie').value;
    if(specie) {
      this.globalSpecie.forEach(item => {
        if(specie === item.idSpecie) {
          (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('codice').patchValue(item.idSpecie);
          (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('gruppo').patchValue(item.codGruppo);
        }
      });
    } else {
      this.returnDropdowns(false, true, true, i);
      (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('codice').reset();
      (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('gruppo').reset();
    }
  }

  changeGruppo(i) {
    this.specieList[i].length = 0;
    this.codiceList[i].length = 0;
    const gruppo = (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('gruppo').value;
    if(gruppo) {
      this.globalSpecie.forEach(item => {
        if(gruppo === item.codGruppo) {
          this.specieList[i].push({value: item.idSpecie, label: item.codice + ' - ' + item.volgare}); 
          this.codiceList[i].push({value: item.idSpecie, label: item.codice}); 
        } else {
          (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('idSpecie').reset();
          (this.alberiCavallettatiForm.get('cavallettati') as FormArray).at(i).get('codice').reset();
        }
      });
    } else {
      this.returnDropdowns(true, true, false, i);
    }
    
  }

  navigateToSearch() {
    this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }

  addItem() {
		this.alberiArray = this.alberiCavallettatiForm.get('cavallettati') as FormArray;
    this.alberiArray.push(this.pushAlberiItem());
    this.specieList.push([]);
    this.codiceList.push([]);
    this.globalSpecie.forEach(item => {
      this.specieList[this.specieList.length-1].push({value: item.idSpecie, label: item.codice + ' - ' + item.volgare});
      this.codiceList[this.codiceList.length-1].push({value: item.idSpecie, label: item.codice});
    })
    }
    clear:boolean;
    dropdown:Dropdown;
    clearDropdown(event:any){
      this.dropdown.clear(event);
    }

    showError(tipocampione:FormGroup,error:any,name:string, ev:any){
      
      if(tipocampione.get(name).errors && (tipocampione.get(name).touched && tipocampione.get(name).errors.maxlength || 
      tipocampione.get(name).touched && tipocampione.get(name).errors.pattern ||
      tipocampione.get(name).touched && tipocampione.get(name).errors.required )) {
       
      
      error.show(ev);
        
        
       } 
     }

    tooltipProperty ={
      codiceList:"Seleziona codice list", 
      specieList:"Seleziona specie list", 
      gruppoList:"Seleziona gruppo list", 
      semePollone:"Seleziona seme/pollone",
      diametro:"Inserire il numero diametro"
   }

}
