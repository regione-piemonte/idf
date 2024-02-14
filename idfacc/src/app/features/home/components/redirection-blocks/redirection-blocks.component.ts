/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from "@angular/core";
import { environment } from "src/environments/environment";
import { HomeService } from "../../services/home.service";
import { CONST } from '../../../../shared/constants'
import { ConfigUtente } from "src/app/shared/models/test/user-info";

@Component({
  selector: "app-redirection-blocks",
  templateUrl: "./redirection-blocks.component.html",
  styleUrls: ["./redirection-blocks.component.css"]
})
export class RedirectionBlocksComponent implements OnInit {

  redirectionUrl: string;
  showDialog: boolean = false;

  constructor(private homeService: HomeService) {
    this.redirectionUrl = `${environment.redirectionUrl}${environment.baseHref}`
  }

  ngOnInit() {
    console.log('redirecton url', this.redirectionUrl);
    
  }

  checkUserAndRedirect(){
    this.homeService.getHomeData()
      .subscribe((res: ConfigUtente) => {
        if(res && res.fkProfiloUtente === CONST.GESTORE_ACCESS_PFA){
         //window.location.href = this.redirectionUrl + '/idfpfa';
         window.open(this.redirectionUrl + '/idfpfa');
        } else {
          this.showDialog = true;
        }
      });
      return false;
  }

  closeModal(){
    this.showDialog = !this.showDialog;
  }

}
