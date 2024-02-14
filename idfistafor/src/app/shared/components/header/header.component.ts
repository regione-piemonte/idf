/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ForestaliService } from 'src/app/features/istanze-forestali/services/forestali.service';
import { HomeModel } from '../../models/home.model';
import { SoggettoModel } from '../../models/soggetto.model';
import { environment } from 'src/environments/environment';
import { CONST } from 'src/app/shared/constants';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {

  unsubscribe$ = new Subject<void>();
  user: HomeModel;
  userIniziali: string;
  siforHomeUrl: string;

  constructor(private forestaliService : ForestaliService) { }

  ngOnInit() {
    this.siforHomeUrl = environment.siforPrefix + '/srv/sifor/';
    this.forestaliService.isUtenteAlreadyUpdate()
      .subscribe(
      (response: any) => {
        //console.log('RESPONSE POST', response);
        if(!response['isUpdate']){
          window.location.href = '/idf?redirect=idfistafor';
        }
      }
    );
    this.initSoggettoAndHomeData();
  }
  
  initSoggettoAndHomeData() {
    this.forestaliService.getHomeData()
      .subscribe((homeModel: HomeModel) => {
        if(homeModel){
          this.forestaliService.getSoggettiById(homeModel.fkSoggetto).subscribe((soggetti : SoggettoModel)=>{
            soggetti.ruolo = homeModel.fkProfiloUtente;
            soggetti.fkAccreditamento = homeModel.fkTipoAccreditamento;
            sessionStorage.setItem(CONST.USER_KEY_STORE, JSON.stringify(soggetti));
            this.user = {
              nome: soggetti.nome,
              cognome : soggetti.cognome,
            }
            this.userIniziali = (soggetti.nome.substring(0,1)+soggetti.cognome.substring(0,1)).toUpperCase();
          });
        }
    });
    
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  logout(){
    this.forestaliService.logout()
      .subscribe(
      (response: any) => {
        window.location.href = this.siforHomeUrl;
      }
    );
  }

  openNav(){
    document.getElementById("myNav").style.width = "100%";
  }

  closeNav() {
    document.getElementById("myNav").style.width = "0%";
  }

  showInfo() {
    document.getElementById("headerModalInfo")
  }

  hideInfo() {
    document.getElementById("headerModalInfo")
  }

  showUser() {
    document.getElementById("headerModalInfo")
  }

  hideUser() {
    document.getElementById("headerModalInfo")
  }

}
