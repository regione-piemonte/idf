/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SelectItem } from 'primeng/components/common/selectitem';
import { CONST } from 'src/app/shared/constants';
import { Router } from '@angular/router';
import { GeneraliService } from "src/app/services/generali.service";
import { Subject } from "rxjs";
import { takeUntil, map } from "rxjs/operators";
import {DialogModule} from 'primeng/dialog';
import { AmbitoRilievo } from 'src/app/shared/models/ambito-rilievo';
import { Logs } from 'selenium-webdriver';
import { ConsolidaValidator } from 'src/app/shared/models/validator-config';
import { ButtonType, DialogButtons, DialogIconType } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { DialogService } from 'src/app/shared/services/dialog.service';

@Component({
  selector: 'app-dati-generali-form',
  templateUrl: './dati-generali-form.component.html',
  styleUrls: ['./dati-generali-form.component.css']
})
export class DatiGeneraliFormComponent implements OnInit {

  @Input() datiGeneraliForm: FormGroup;
  @Input() allDropdownMens: any;
  @Input() comuneDropdown: SelectItem[];
  @Input() dettaglioAmbitoDropdown:SelectItem[];
  @Input()  ambitoSpecifico:any;
  @Output() resetFormEmitter: EventEmitter<boolean> = new EventEmitter();
  @Output() emitDatiGeneraliForm: EventEmitter<any> = new EventEmitter();
  @Output() emitDatiGeneraliBasicForm: EventEmitter<any> = new EventEmitter();
  @Output() emitAmbito: EventEmitter<void>= new EventEmitter();
  @Output() emitAmbitoSpecifico:EventEmitter<any>=new EventEmitter();
  @Output() loadDatiGeneraliEmitter: EventEmitter<void>= new EventEmitter();
  @Output() emitTipologiaDiRilievoChange: EventEmitter<void>= new EventEmitter();

  @ViewChild('container') container: ElementRef;

  @Input() basicInfoSubmitted = false; 
  utmPointNotValid: boolean = false;
  isDisabled: boolean=true;
  idgeoPtAds: number;
  selectedRilievo: number = 0;
  it: any;
  currentYear: number = new Date().getFullYear();
  unsubscribe$: Subject<void> = new Subject<void>();
  withCoordinate:boolean;

  maxDate: Date = new Date();

  constructor(private router: Router, private generaliService: GeneraliService,
    private dialogService: DialogService) {
    this.it = CONST.IT;
   }

  ngOnInit() {
    this.showDialog();
    this.maxDate.setDate(this.maxDate.getDate() -1);
    this.idgeoPtAds = this.datiGeneraliForm.get("idgeoPtAds").value;

    let utmEST = this.datiGeneraliForm.get("utmEST").value;
    let utmNORD = this.datiGeneraliForm.get("utmNORD").value;
    if(utmEST && utmNORD){
      this.utmCoordinateChange();
    }
  }

  isNotTipologiaRelascopica(){
    let val = this.datiGeneraliForm? this.datiGeneraliForm.get('tipologiaDiRilievo').value:0;
    return val != 2 && val !=3;
  }

  tipoRilievoChange(){
    this.emitTipologiaDiRilievoChange.emit();
  }

  withCoordinateChange(val:boolean){
    this.withCoordinate  = val;
  }

  hideBtnMappa(){
    return(false != this.withCoordinate || this.basicInfoSubmitted);
  }

  hideUtm(){
    return this.withCoordinate == true?null:'';
  }

  sendGeneraliForm() {
    if(this.datiGeneraliForm.valid) {
      const datiGeneraliForm = this.datiGeneraliForm.getRawValue();
      this.emitDatiGeneraliForm.emit(datiGeneraliForm);
    }
    ConsolidaValidator.valid=false;
  }

  createEmptyAreaDiSaggio(){
    let idTipoAds = this.datiGeneraliForm.get("tipologiaDiRilievo").value;
    let codiceADS = this.datiGeneraliForm.get("codiceADS").value;
    if(!idTipoAds || idTipoAds == '' || codiceADS ==''){
      this.dialogService.showDialog(true, 'Valorizzare i campi "Tipologia di rilievo" e "N. Ads"!', 'Attenzione', 
      DialogButtons.OK, '', (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {}
      }, DialogIconType.WARNING);
      return;
    }
    this.generaliService.createEmptyAreaDiSaggio(idTipoAds,codiceADS)
    .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (response: any) => {
          if (!this.idgeoPtAds) {
            this.idgeoPtAds = response;
            this.datiGeneraliForm.get("idgeoPtAds").patchValue(this.idgeoPtAds);
          }
          this.openMappa();
        });
  }

  saveBasicInfo() {
    if(this.datiGeneraliForm.valid) {
      const datiGeneraliForm = this.datiGeneraliForm.getRawValue();
      this.emitDatiGeneraliBasicForm.emit(datiGeneraliForm);
    }
  }

  openMappa(){
    if(!this.idgeoPtAds || ('' + this.idgeoPtAds) == ''){
      this.createEmptyAreaDiSaggio();
    }else{
      this.generaliService.getCartograficoByIdUrl(2,''+this.idgeoPtAds)
      .subscribe(
        (response: any) => {
          if(window.location.href.indexOf('http://localhost:') == -1 ){
            window.location.href = response['geecourl'];
          }else{
            window.open(response['geecourl'], "_blank");
          }
        }
      );
    }
  }

  resetForm() {
    this.resetFormEmitter.emit(true);
    this.container.nativeElement.scrollIntoView({
      behavior: 'smooth',
      block: 'start'
    });
  }
  enableAmbito:boolean=true;

  checkDropdown(event) {
    
    this.selectedRilievo = event.value;
    
    if(this.selectedRilievo == -1){
      this.datiGeneraliForm.get("ambitoSpecifico").enable();
      this.enableAmbito=false;
    }else{
      this.datiGeneraliForm.get("ambitoSpecifico").setValue(null);
      this.datiGeneraliForm.get("ambitoSpecifico").disable();
      this.enableAmbito=true;
      }
  }

  navigateToSearch() {
    this.router.navigate(['/forestale', 'modifica-consolida'], { queryParams: { recoverPrevious: true }, queryParamsHandling: 'merge' });
  }

  display:boolean = false;
  enableUTM:boolean = false;
  
  showDialog(){
    this.display = true;
}
  manual(){
  this.display = false;
 }

automate(){
  this.display = false;
  this.datiGeneraliForm.controls['utmEST'].disable();
  this.datiGeneraliForm.controls['utmNORD'].disable()
}
// enableFields(){
//   this.datiGeneraliForm.get('tipologiaDiRilievo').enable();
//   this.datiGeneraliForm.get('ambitoDiRilevamento').enable();
//   this.datiGeneraliForm.get('dataRilevamento').enable();
//   this.datiGeneraliForm.get('rilevatore').enable();
//   this.datiGeneraliForm.get('quota').enable();
//   this.datiGeneraliForm.get('espozione').enable();
//   this.datiGeneraliForm.get('inclinazione').enable();
//   this.datiGeneraliForm.get('particellaForestale').enable();
//   this.datiGeneraliForm.get('proprieta').enable();
// }

  onKeyUpOnlyNumber(event){
    if(!this.isNumeric(event.target.value)){
      event.target.value='';
    }
  }

  utmCoordinateChange(){    
    let utmEST = this.datiGeneraliForm.get("utmEST").value;
    let utmNORD = this.datiGeneraliForm.get("utmNORD").value;
    this.utmPointNotValid = false;
    if(utmEST && utmNORD){
      let comuneProv = this.generaliService.getComuneProvinciaByCoords(utmEST,utmNORD)
      .pipe(takeUntil(this.unsubscribe$))
        .subscribe((response: SelectItem[]) => {
           if(response['idComune']){
            //this.allDropdownMens['provincia']=[{ value: response['istatProvincia'],label: response['denomProvincia']}];
            this.comuneDropdown = [{ value: response['idComune'],label: response['denomComune']}];
            this.datiGeneraliForm.get("comune").setValue(response['idComune']);
            this.datiGeneraliForm.get("provincia").setValue(response['istatProvincia']);
          }
          else{
            this.datiGeneraliForm.get("comune").setValue('');
            this.datiGeneraliForm.get("provincia").setValue('');
            this.utmPointNotValid = true;
          }
        });
      // console.log(comuneProv);
     
    }
  }

  isNumeric(str){
    return !isNaN(str) && !isNaN(parseFloat(str));
  }

  fillDettaglioAmbito(){
    this.emitAmbito.emit();
    this.datiGeneraliForm.get("ambitoSpecifico").disable();
    this.selectedRilievo = null;
    let dettaglioAmbito =  this.datiGeneraliForm.get('idDettaglioAmbito');
    //dettaglioAmbito.    touched = false;
    dettaglioAmbito.setValue(null);
    this.enableAmbito=true;
  }
  enableAmbitoSpecifico(){
    this.emitAmbitoSpecifico.emit();
  }
  tooltipProperty ={
    utmEast:"Coordinata est UTM 32N WGS84", 
    utmNord:"Coordinata nord UTM 32N WGS84"
 }
}
