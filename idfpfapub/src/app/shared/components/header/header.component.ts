/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/user';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Input() header: number;
  user: User;
  userIniziali:string;
  unsubscribe$: Observable<User>;
  siforHomeUrl: string;

  constructor() { }

  ngOnInit() {
    
    this.siforHomeUrl = environment.siforPrefix + '/sifor/';
  
      
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
