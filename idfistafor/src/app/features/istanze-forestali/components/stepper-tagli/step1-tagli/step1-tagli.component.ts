/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, combineLatest } from 'rxjs';
import { CONST } from 'src/app/shared/constants';

import { UtilService } from 'src/app/shared/services/util.service';
import { ForestaliService } from '../../../services/forestali.service';

import { TagliService } from './../../../services/tagli.service';

import { TipoIntervento } from 'src/app/shared/models/tipo-intervento';
import { takeUntil } from 'rxjs/operators';
import { CategoriaSelvicolturale } from './../../../../../shared/models/categoria-selvicolturale.model';
import { Proprieta } from './../../../../../shared/models/proprieta.model';
import { Trasformazione } from 'src/app/shared/models/trasformazione.model';
import { TagliStep1 } from './tagli-step1.model';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { ButtonType, DialogButtons } from 'src/app/shared/error-dialog/error-dialog.component';



@Component({
  selector: 'step1-tagli',
  templateUrl: './step1-tagli.component.html',
  styleUrls: ['./step1-tagli.component.css']
})
export class Step1TagliComponent implements OnInit, OnDestroy {

  @Input() editMode: number | null;
  @Input() boMode: boolean;
  @Input() isIstanzaInviata: boolean;

  @Output() emitNextStep = new EventEmitter();
  @Output() emitIdIstanze = new EventEmitter<number>();


  datiGeneraliForm: FormGroup;
  emptyMessageAC: string = CONST.AUTOCOMPLETE_EMPTY_MESSAGE;

  showPopUp: boolean = false;
  msgPopUp: string = '';
  goNextStep: boolean = false;

  unsubscribe$ = new Subject<void>();
  invalidForm: boolean = true;
  invalidHeadings: boolean;

  categorieSelvicolturali: CategoriaSelvicolturale[];
  proprietaSelvicolturali: Proprieta[];
  trasformazioni: Trasformazione[];
  trasformazione: Trasformazione;

  //La logica prevede che fino al 31 Agosto venga proposto year-1/year e dal 1 Settembre year/year+1
  currentYear: number = new Date().getMonth() <= 7 ? new Date().getFullYear() -1 : new Date().getFullYear(); 
  silvanaInit: string = this.currentYear + "/" + (this.currentYear + 1);
  it: any = CONST.IT;
  step1: TagliStep1;
  minDateVal = new Date();



  constructor(
    private fb: FormBuilder,
    private forestaliService: ForestaliService,
    private dialogService: DialogService,
    private tagliService: TagliService
  ) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    combineLatest(this.tagliService.getCategorieSelvicolturali(), this.tagliService.getProprietaSelvicolturali())
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(([resCategorie, resProprieta]) => {
        this.categorieSelvicolturali = resCategorie;
        this.proprietaSelvicolturali = resProprieta;
    });
    this.initForms();

    if(this.editMode) {
      this.tagliService.getStep1(this.editMode)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((result) => {
        this.step1 = result;
        if (this.step1.trasformazSelvicolturale) {
          let t = this.step1.trasformazSelvicolturale;
          t.displayItem = `N: ${t.nrIstanza} / ${t.anno} / ${t.richiedente} / ${t.comune} / ${t.compensazione}`;
          this.step1.trasformazSelvicolturale = t;
        }
        this.fillForms();
      });
    }
  }


  private initForms() {
    this.datiGeneraliForm = this.fb.group({
      categoriaIntervento: [null, [Validators.required]],
      proprieta: [null, [Validators.required]],
      annataSilvana: [this.silvanaInit, [Validators.required]],
      dataPresaInCarico: ['', [Validators.required]],
      statoIntervento: [CONST.STATO_INTERVENTO.descrStatoIntervento],
      interventoCompensativo: [null],
    });
  }

  private fillForms() {
    this.datiGeneraliForm.patchValue({
      categoriaIntervento:  this.step1.categoriaSelvicolturale,
      proprieta:  this.step1.proprieta,
      annataSilvana: this.step1.annataSilvana,
      dataPresaInCarico: new Date(this.step1.dataPresaInCarico),
      interventoCompensativo: this.step1.trasformazSelvicolturale
    });
  }


  private searchTrasformazioni(event: any) {
    this.tagliService.searchTrasformazioni(event.query)
      .subscribe((res: Trasformazione[]) => {
        let transf = res;
        transf.map(t => t.displayItem = `N: ${t.nrIstanza} / ${t.anno} / ${t.richiedente} / ${t.comune} / ${t.compensazione}`);
        this.trasformazioni = transf;
    });
  }




  save(nextStep?: boolean) {
    this.goNextStep = nextStep;

    let step1 = new TagliStep1();
    step1.annataSilvana = this.datiGeneraliForm.get('annataSilvana').value;
    step1.dataPresaInCarico = this.forestaliService.formatDate(this.datiGeneraliForm.get('dataPresaInCarico').value);

    let tipoIstanzaId: string = sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE);
    step1.tipoIstanzaId = Number(tipoIstanzaId);

    step1.categoriaSelvicolturale = this.datiGeneraliForm.get('categoriaIntervento').value;
    step1.proprieta = this.datiGeneraliForm.get('proprieta').value;
    step1.trasformazSelvicolturale = this.datiGeneraliForm.get('interventoCompensativo').value;


    if(this.editMode) {
      this.tagliService
        .putStep1(step1, this.editMode)
        .pipe(takeUntil(this.unsubscribe$))
        .subscribe(res => {
          nextStep && this.emitNextStep.emit();
        });

    } else {
        this.tagliService
          .postStep1(step1)
          .pipe(takeUntil(this.unsubscribe$))
          .subscribe(
            response => {
              if (response.errorMessage) {
                this.showPopUp = true;
                this.msgPopUp = response.errorMessage
              } else {
                if (!this.editMode) {
                  this.editMode = response.istanzaId;

                  this.forestaliService.getPraticaInviata(this.editMode)
                  .subscribe(
                      res =>  {
                        const message = `<span class="text-success"><strong>Istanza generata correttamente con nr : ${new Date(res.dataInserimento).getFullYear()}/${res.nrIstanzaForestale}</strong> </span>`;
                        this.showDialogMsg("Attenzione", message);    
                        this.emitIdIstanze.emit(response.istanzaId);
                        nextStep && this.emitNextStep.emit();
                      }
                    )

                }
              }
            },
            err => console.log('HTTP Error', err),
            () => console.log('HTTP request completed.')
          );
    }
  }

  continue() {
    this.save(true);
  }

  prosegui() {
    this.showPopUp = false;
    if (this.goNextStep) this.emitNextStep.emit();
  }

  closePopUp() {
    this.showPopUp = false;
  }


  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  showDialogMsg(msgType:string, msg: string){
    this.dialogService.showDialog(true, msg, msgType, DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {}
    });
  }


}
