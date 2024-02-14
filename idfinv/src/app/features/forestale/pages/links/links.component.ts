/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { map } from "rxjs/operators";
import { GeneraliService } from 'src/app/services/generali.service';

@Component({
  selector: 'app-links',
  templateUrl: './links.component.html',
  styleUrls: ['./links.component.css']
})
export class LinksComponent implements OnInit {

  originUrl:any = window.location.origin;
  rilieviEndometriciOptions:string = null;

  constructor(private router: Router, private generaliService: GeneraliService) { }

  ngOnInit() {
    console.log("originUrl: " + this.originUrl);
    console.log("rilieviEndometriciOptions: " + this.rilieviEndometriciOptions);
    sessionStorage.removeItem('lastSearch');
  }

  navigateToAds() {
    this.router.navigate(['forestale', 'personali']);
  }

  clickOpenCartografia(){
    this.generaliService.getCartograficoAllUrl(1)
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }

  clickRilieviEndometriciOptions(){
    if(this.rilieviEndometriciOptions){
      this.rilieviEndometriciOptions = null;
    }else{
      this.rilieviEndometriciOptions = 'show';
    }
    console.log("run clickRilieviEndometriciOptions: " + this.rilieviEndometriciOptions)
    return false;
  }

}
