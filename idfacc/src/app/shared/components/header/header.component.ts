/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { HomeService } from 'src/app/features/home/services/home.service';
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

  constructor(private accService : HomeService) { }

  ngOnInit() {
    this.siforHomeUrl = environment.siforPrefix + '/srv/sifor/';
    this.accService.getUtenti().subscribe( (res : User) =>{
      this.user = res;
      this.userIniziali = (res.nome.substr(0,1)+res.cognome.substr(0,1)).toUpperCase();
    });
  }

  logout(){
    this.accService.logout()
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
