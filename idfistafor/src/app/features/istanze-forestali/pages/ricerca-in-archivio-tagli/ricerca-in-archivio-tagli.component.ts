/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Subject, Observable } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { ForestaliService } from '../../services/forestali.service';
import { Step4Model } from 'src/app/shared/models/step4.model';
import { CheckboxAndRadio } from 'src/app/shared/models/checkbox-and-radio';
import { StepPosition } from 'src/app/shared/models/step-position.model';
import { ShowParcel } from 'src/app/shared/models/particle-cadastral';
import { PlainSestoSezione, DocumentoAllegato } from 'src/app/shared/models/plain-sesto-sezione.model';
import { TableHeader } from 'src/app/shared/models/table-header';
import { IstanzaInviata } from 'src/app/shared/models/view-instance';
import { e } from '@angular/core/src/render3';
import { HomeModel } from 'src/app/shared/models/home.model';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { TipoAccreditamento } from 'src/app/shared/models/tipo-accreditamento.model';
import { CONST } from 'src/app/shared/constants';
import { AuthService } from '../../../../shared/services/auth.service';
import { TagliService } from '../../services/tagli.service';
import { DialogButtons, ButtonType } from 'src/app/shared/error-dialog/error-dialog.component';
import { DialogService } from './../../../../shared/services/dialog.service';


@Component({
  selector: 'app-ricerca-in-archivio-tagli',
  templateUrl: './ricerca-in-archivio-tagli.component.html',
  styleUrls: ['./ricerca-in-archivio-tagli.component.css']
})
export class RicercaInArchivioTagliComponent implements OnInit, OnDestroy {

  editMode: number;
  invalidMessage = 'il modulo non è valido';
  unsubscribe$ = new Subject<void>();
  viewModeOn: boolean = false;
  modificaModeOn: boolean = false;
  boModificaOn:boolean = false;

  instanzaOwnership: any;
  user : HomeModel = {};

  personaOwner: any;
  allProfessionals = [];


  idTIpoIstanza: string = sessionStorage.getItem(CONST.TIPO_ISTANZA_ID_KEY_STORE);
  tipoAccreditamento = null;

  constructor(
    private router: Router,
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    private authService: AuthService,
    private dialogService: DialogService,
    private fb: FormBuilder,
    public route: ActivatedRoute,
  ) {}

  ngOnInit() {
    this.user = this.authService.currentUser();
    this.checkUriParams();


  }


  getFormatDateIT(date : string){
    if(date != null && date.length == 10){
      let today = new Date(date);
      let dd = String(today. getDate()). padStart(2, '0');
      let mm = String(today. getMonth() + 1). padStart(2, '0'); //January is 0!
      let yyyy = today. getFullYear();
      return dd + '/' + mm + '/' + yyyy;
  }
  return "n.d.";
  }




  checkUriParams() {
    const data = this.route.snapshot.params['viewMode'];
    this.viewModeOn = data ? true : false;
    const boModifica = this.route.snapshot.params['boModifica'];
    this.boModificaOn = boModifica ? true : false;
  }

  setIdIstanze(event) {
    if (!this.editMode) {
      this.editMode = event;
    }
  }


  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  goHome() {
    if (this.boModificaOn) {
      this.router.navigate(['']);
    } else {
      this.router.navigate(['']);
    }
  }

  goVisualizza() {
    this.router.navigate(['visualizza-tagli']);
  }

  backward() {
    this.modificaModeOn = !this.modificaModeOn;
  }

  returnToArhiveList(){
    this.router.navigate(['']);
  }

  deleteRow(event) {
    this.dialogService.showDialog(true, 'Confermare la cancellazione dell\'istanza?', 'Attenzione', DialogButtons.OK, '', (buttonId: number): void => {
      if (buttonId === ButtonType.OK_BUTTON) {
        this.deleteConfirm(event);
      }
    });
  }

  deleteConfirm(event) {
    this.tagliService.deleteIntervento(event).subscribe(res => {
      console.log('delete done');
      window.location.reload();
    })
  }

}
