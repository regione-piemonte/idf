/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, OnDestroy, Output, EventEmitter } from '@angular/core';
import { TableHeader } from 'src/app/shared/models/table-header';
import { FormGroup, Validators, FormBuilder, AbstractControl, FormControl } from "@angular/forms";
import { Subject, combineLatest } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { VincoloService } from '../../../services/vincolo.service';
import { EconomicCalculationTableModel } from 'src/app/shared/models/based-economic-value.model';
import { CONST } from 'src/app/shared/constants';
import { SoggettoModel } from 'src/app/shared/models/soggetto.model';
import { ComuneModel } from 'src/app/shared/models/comune.model';
import { StepPosition } from 'src/app/shared/models/step-position.model';
import { UserCompanyDataModel } from 'src/app/shared/models/user-company-data.model';
import { SelectItem } from 'primeng/api';
import { VincoloStep5 } from './vincoloStep5';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';

@Component({
  selector: 'step5-vid',
  templateUrl: './step5-vid.component.html',
  styleUrls: ['./step5-vid.component.css']
})
export class Step5VidComponent implements OnInit, OnDestroy {

  @Input() editMode: number;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;
  @Output() emitNextStep = new EventEmitter<void>();
  compensazioneForm: FormGroup;
  invalid = true;
  unsubscribe$ = new Subject<void>();
  

  coefficiente: number;
  totaleSuperficieTrasf: string='0';
  cmNoBoscEuro: string='0';
  ammontareComplessivo: string='0';

  section1Fields: string[] = ['flagLavori','flagCauzioneVi'] ;
  section2Fields: string[] = ['superficieBosc','superficieNoBosc','cmBoscEuro']; //'cmNoBoscEuro'
  section31Fields: string[] = ['flagArt9A','flagArt9B','flagArt9C'];
  section32Fields: string[] = ['flagArt7A','flagArt7B','flagArt7C','flagArt7D','flagArt7DBis'] 

  superficiOriginali: any={};
  isStep5Saved: boolean = false;

  constructor(private vincoloService: VincoloService,  private fb: FormBuilder,
    private dialogService: DialogService) { }



  ngOnInit() {
    window.scrollTo(0, 0);
    this.initForm();


    //caricamento
    this.getCoefficienteCalcoloImportoSupNonBoscata();
    this.loadData()
  }

  loadValuesStep5(){
    this.vincoloService.getStep5(this.editMode)
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe( res => {
      if(res != null){
        this.compensazioneForm.patchValue({'flagCompensazione': res.flagCompensazione});
        //this.compensazioneForm.patchValue({'flagCauzioneVi': res.flagCauzioneVi});

        if (res.flagCompensazione == 'F'){
          this.compensazioneForm.patchValue({'flagLavori': true});
          this.compensazioneForm.patchValue({'flagCauzioneVi': true});
        } else {
          this.compensazioneForm.patchValue({'flagLavori': false});
          this.compensazioneForm.patchValue({'flagCauzioneVi': false});
        }
        this.compensazioneForm.patchValue({'flagEsenzione': res.flagEsenzione});
        this.changeCompensazione(res.flagCompensazione);
        
        //this.compensazioneForm.patchValue({'cmNoBoscEuro': res.cmNoBoscEuro});
        this.cmNoBoscEuro = res.cmNoBoscEuro.toFixed(2);
        this.compensazioneForm.patchValue({'cmBoscEuro': res.cmBoscEuro});
        this.compensazioneForm.patchValue({'superficieBosc': res.cmSupBosc});
        this.compensazioneForm.patchValue({'superficieNoBosc': res.cmSupNoBosc});

        this.calculate();

        if(res.flagCompensazione){
        this.changeCompensazione(res.flagCompensazione);

        }
        this.compensazioneForm.patchValue({'flagArt9A': res.flagArt9A});
        this.compensazioneForm.patchValue({'flagArt9B': res.flagArt9B});
        this.compensazioneForm.patchValue({'flagArt9C': res.flagArt9C});

        this.compensazioneForm.patchValue({'flagArt7A': res.flagArt7A});
        this.compensazioneForm.patchValue({'flagArt7B': res.flagArt7B});
        this.compensazioneForm.patchValue({'flagArt7C': res.flagArt7C});
        this.compensazioneForm.patchValue({'flagArt7D': res.flagArt7D});
        this.compensazioneForm.patchValue({'flagArt7DBis': res.flagArt7DBis});
      } else {
        this.totaleSuperficieTrasf = (this.superficiOriginali.totaleSuperficieTrasf)?
          JSON.parse(JSON.stringify(this.superficiOriginali.totaleSuperficieTrasf)) : '0';

        let totaleSuperficieBoscata = (this.superficiOriginali.totaleSuperficieBoscata)?
          JSON.parse(JSON.stringify(this.superficiOriginali.totaleSuperficieBoscata)) : '0';
        this.compensazioneForm.patchValue({'superficieBosc': totaleSuperficieBoscata});

        let totaleSuperficieNonBoscata = (this.superficiOriginali.totaleSuperficieNonBoscata)?
          JSON.parse(JSON.stringify(this.superficiOriginali.totaleSuperficieNonBoscata)) : '0';
        this.compensazioneForm.patchValue({'superficieNoBosc': totaleSuperficieNonBoscata});

        this.calculate();
      }
    });
  
  }

  initForm() {
    console.log('totaleSuperficieBoscata' + this.superficiOriginali.totaleSuperficieBoscata);
    console.log('totaleSuperficieNonBoscata' + this.superficiOriginali.totaleSuperficieNonBoscata);
      
    this.compensazioneForm = new FormGroup({
      flagCompensazione: new FormControl('', [Validators.required]),
      flagLavori: new FormControl(false),
      flagCauzioneVi: new FormControl(false),
      superficieNoBosc: new FormControl(0, [Validators.required, 
        Validators.pattern(CONST.PATTERN_NUMERIC_4DECIMAL), Validators.max(this.superficiOriginali.totaleSuperficieNonBoscata)]),
      superficieBosc: new FormControl(0, [Validators.required, 
        Validators.pattern(CONST.PATTERN_NUMERIC_4DECIMAL), Validators.max(this.superficiOriginali.totaleSuperficieBoscata)]),
      //cmNoBoscEuro: new FormControl(0, [Validators.required, Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL)]),
      cmBoscEuro: new FormControl(0, [Validators.required, 
        Validators.pattern(CONST.PATTERN_NUMERIC_DECIMAL)]),
    
      flagEsenzione: new FormControl(''),
      flagArt9A: new FormControl(false),
      flagArt9B: new FormControl(false),
      flagArt9C: new FormControl(false),
    
      flagArt7A: new FormControl(false),
      flagArt7B: new FormControl(false),
      flagArt7C: new FormControl(false),
      flagArt7D: new FormControl(false),
      flagArt7DBis: new FormControl(false)
    });
    this.disableFields([...this.section1Fields, ...this.section2Fields, ...this.section31Fields, ...this.section32Fields, 'flagEsenzione']);
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  changeCompensazione(index: string){
    this.compensazioneForm.patchValue({'flagLavori': false});
    this.compensazioneForm.patchValue({'flagCauzioneVi': false});
    this.compensazioneForm.patchValue({'flagArt9A': false});
    this.compensazioneForm.patchValue({'flagArt9B': false});
    this.compensazioneForm.patchValue({'flagArt9C': false});
    this.compensazioneForm.patchValue({'flagArt7A': false});
    this.compensazioneForm.patchValue({'flagArt7B': false});
    this.compensazioneForm.patchValue({'flagArt7C': false});
    this.compensazioneForm.patchValue({'flagArt7D': false});
    this.compensazioneForm.patchValue({'flagArt7DBis': false});
  
    switch(index){
      case 'F':{
        this.disableFields([...this.section1Fields, ...this.section2Fields, ...this.section31Fields, ...this.section32Fields, 'flagEsenzione']);
        //this.enableFields(this.section1Fields);
        this.compensazioneForm.patchValue({'flagLavori': true});
        this.compensazioneForm.patchValue({'flagCauzioneVi': true});
        break;
      }
      case 'M':{
        this.disableFields([...this.section1Fields, ...this.section2Fields, ...this.section31Fields, ...this.section32Fields, 'flagEsenzione']);
        this.enableFields(this.section2Fields);
        break;
      }
      case 'N':{
        this.disableFields([...this.section1Fields, ...this.section2Fields, ...this.section31Fields, ...this.section32Fields, 'flagEsenzione']);
        this.enableFields(['flagEsenzione']);
        let numVal: number = parseInt(this.compensazioneForm.get('flagEsenzione').value);
        this.changeEsenzione(numVal);
        break;
      }
    }
  }

  changeEsenzione(index: number){
    
    this.compensazioneForm.patchValue({'flagArt9A': false});
    this.compensazioneForm.patchValue({'flagArt9B': false});
    this.compensazioneForm.patchValue({'flagArt9C': false});
    this.compensazioneForm.patchValue({'flagArt7A': false});
    this.compensazioneForm.patchValue({'flagArt7B': false});
    this.compensazioneForm.patchValue({'flagArt7C': false});
    this.compensazioneForm.patchValue({'flagArt7D': false});
    this.compensazioneForm.patchValue({'flagArt7DBis': false});
    switch(index){
      case 1:{
        this.disableFields([...this.section31Fields, ...this.section32Fields]);
        this.enableFields(this.section31Fields)
        break;
      }
      case 2:{
        this.disableFields([...this.section31Fields, ...this.section32Fields]);
        this.enableFields(this.section32Fields)
        break;
      }
    }
  }



  disableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.compensazioneForm.get(item).disable();
    });
  }

  enableFields(fields: string[]) {
    fields.forEach((item, i) => {
      this.compensazioneForm.get(item).enable();
    });
  }

  loadData(){
    this.vincoloService.getSuperfici(this.editMode)    
    .subscribe((response: any) => {
      this.superficiOriginali = response;
      this.initForm();
      this.loadValuesStep5();
    });
  }

  getCoefficienteCalcoloImportoSupNonBoscata(){
    this.vincoloService.getCoefficienteCalcoloImportoSupNonBoscata()    
    .subscribe((response: any) => {
      this.coefficiente = response;
    });
  }

  calculate(){
    this.compensazioneForm.get('superficieBosc').patchValue(
      this.getNumber(this.compensazioneForm.get('superficieBosc').value));
    this.compensazioneForm.get('superficieNoBosc').patchValue(
      this.getNumber(this.compensazioneForm.get('superficieNoBosc').value));
    this.compensazioneForm.get('cmBoscEuro').patchValue(
      this.getNumber(this.compensazioneForm.get('cmBoscEuro').value));
    
    this.totaleSuperficieTrasf = (parseFloat(this.compensazioneForm.get('superficieBosc').value)+parseFloat(this.compensazioneForm.get('superficieNoBosc').value)).toFixed(4);
    this.cmNoBoscEuro = (this.coefficiente * parseFloat(this.compensazioneForm.get('superficieNoBosc').value)).toFixed(2);
    this.ammontareComplessivo = (parseFloat(this.cmNoBoscEuro) + parseFloat(this.compensazioneForm.get('cmBoscEuro').value)).toFixed(2);
  }

  getNumber(value:string){
    if(value.length == 0 || isNaN(parseFloat(value))){
      return '0';
    }
    return value;
  }

  continue() { this.save(true); }

  save(nextStep?: boolean) {
    if(this.compensazioneForm.valid) {
      let vincolo = new VincoloStep5();
      vincolo.flagCompensazione = this.compensazioneForm.get('flagCompensazione').value;

      //vincolo.flagCauzioneVi = this.compensazioneForm.get('flagCauzioneVi').value;
      vincolo.cmSupNoBosc = this.compensazioneForm.get('superficieNoBosc').value;
      vincolo.cmSupBosc = this.compensazioneForm.get('superficieBosc').value;
      vincolo.cmNoBoscEuro = parseFloat(this.cmNoBoscEuro);
      vincolo.cmBoscEuro = this.compensazioneForm.get('cmBoscEuro').value;
    
      vincolo.flagEsenzione = this.compensazioneForm.get('flagEsenzione').value;

      vincolo.flagArt9A = this.compensazioneForm.get('flagArt9A').value;
      vincolo.flagArt9B = this.compensazioneForm.get('flagArt9B').value;
      vincolo.flagArt9C = this.compensazioneForm.get('flagArt9C').value;
    
      vincolo.flagArt7A = this.compensazioneForm.get('flagArt7A').value;
      vincolo.flagArt7B = this.compensazioneForm.get('flagArt7B').value;
      vincolo.flagArt7C = this.compensazioneForm.get('flagArt7C').value;
      vincolo.flagArt7D = this.compensazioneForm.get('flagArt7D').value;
      vincolo.flagArt7DBis = this.compensazioneForm.get('flagArt7DBis').value;

      this.vincoloService
        .postStep5(vincolo, this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(() => {
          if(nextStep) this.emitNextStep.emit();
        });
    }else{
      if(!this.compensazioneForm.get('flagCompensazione').value){
        this.showDialogMsg('Attenzione!','Selezionare almeno una delle voci presenti');
      }
    }
  }

  showDialogMsg(msgType:string, msg: string){
    this.dialogService.showDialog(true, msg, msgType, DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {}
    });
  }
 
}
