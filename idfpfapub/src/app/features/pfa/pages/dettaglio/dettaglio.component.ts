/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";
import { Dettaglio, Detail } from "src/app/shared/models/dettaglio";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: "app-dettaglio",
  templateUrl: "./dettaglio.component.html",
  styleUrls: ["./dettaglio.component.css"]
})
export class DettaglioComponent implements OnInit {
  @Input() denominazione: Detail;

  unsubscribe$ = new Subject<void>();
  constructor(
    private pfaService: PfaSampleService,
    private activatedRoute: ActivatedRoute,
    private routeService: Router
  ) {}

  ngOnInit() {
  }

  toPfaGestiti() {
    this.routeService.navigate(["pfa", "ricerca"]);
  }

  openMappa(){
    this.pfaService.getCartograficoPointsUrl(17,[''+ this.denominazione.idGeoPlPfa])
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }

  getStringList(list){
    if(list && Array.isArray(list) && list.length > 0){
      let retVal= list[0];
      for(let i=1; i< list.length; i++){
        retVal+= ', ' + list[i];
      }
      return retVal;
    }
    return '';
  }

  formatNumber(value){
    return value || value == 0?(''+ value).replace('.',','):'N/A'
  }
}
