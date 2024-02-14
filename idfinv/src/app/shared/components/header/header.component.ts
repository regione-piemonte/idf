/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { TabsService } from 'src/app/services/tabs.service';
import { Observable } from 'rxjs';
import { User } from '../../models/user';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User;
  userIniziali:string;
  unsubscribe$: Observable<User>;
  siforHomeUrl: string;

  constructor(private home : TabsService) { }

  ngOnInit() {
    this.siforHomeUrl = environment.siforPrefix + '/sifor/';
    this.home.getHomeData()
      .subscribe((res: User) => {
        sessionStorage.setItem('user', JSON.stringify(res));
         this.user =  res;
         this.userIniziali = (res.nome.substr(0,1)+res.cognome.substr(0,1)).toUpperCase();
      });
      this.home.isUtenteAlreadyUpdate()
      .subscribe(
      (response: any) => {
        console.log('RESPONSE POST', response);
        if(!response['isUpdate']){
          window.location.href = '/idf?redirect=idfinv';
        }
      }
    ); 
  }

  logout(){
    this.home.logout()
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
