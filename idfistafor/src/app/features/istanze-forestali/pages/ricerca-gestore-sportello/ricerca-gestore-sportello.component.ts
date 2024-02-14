/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { HomeModel } from 'src/app/shared/models/home.model';
import { CONST } from 'src/app/shared/constants';
import { Router, ActivatedRoute } from '@angular/router';
import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { TagliService } from 'src/app/features/istanze-forestali/services/tagli.service';
import { AuthService } from 'src/app/shared/services/auth.service';
import { DialogService } from 'src/app/shared/services/dialog.service';
import { FormBuilder } from '@angular/forms';
import { DialogButtons, ButtonType } from 'src/app/shared/error-dialog/error-dialog.component';

@Component({
  selector: 'app-ricerca-gestore-sportello',
  templateUrl: './ricerca-gestore-sportello.component.html',
  styleUrls: ['./ricerca-gestore-sportello.component.css']
})
export class RicercaGestoreSportelloComponent implements OnInit {

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
