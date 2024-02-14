/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { Router } from "@angular/router";
import { RouteParams } from "src/app/shared/models/routeParams";

@Component({
  selector: "app-disegna-evento",
  templateUrl: "./disegna-evento.component.html",
  styleUrls: ["./disegna-evento.component.css"]
})
export class DisegnaEventoComponent implements OnInit {
  // @Input() routerParam: RouteParams;
  @Output() emitIndex: EventEmitter<void> = new EventEmitter();
  @Output() emitBack: EventEmitter<void> = new EventEmitter();
  @Input() editMode: boolean;
  @Input() eventoId: number;
  drawedGeometryInfo: any;
  constructor(
    private pfaService: PfaSampleService,
    private routerService: Router
  ) {}

  ngOnInit() {
    if(this.eventoId){
      this.pfaService.getEventiDrawedGeometryInfo(this.eventoId)
      .subscribe((response)=> this.drawedGeometryInfo = response);
    }
  }

  backToTabs() {
    // this.pfaService.currentRouteParams.subscribe(resp => {
    //   this.routerParam = resp;
    // });
    // this.routerService.navigate(["pfa", "tabs", this.routerParam.id], {
    //   queryParams: {
    //     idComune: this.routerParam.idComune,
    //     idPopolamento: this.routerParam.idPopolamento
    //   }
    // });
    this.emitBack.emit();
  }

  proceed() {
    this.emitIndex.emit();
  }

  openMappa(){
    //window.scrollTo(0, 0);
    this.pfaService.getCartograficoByIdUrl(9,''+this.eventoId)
    .subscribe(
      (response: any) => {
        if(window.location.href.indexOf('http://localhost:') == -1 ){
          window.location.href = response['geecourl'];
        }else{
          window.open(response['geecourl'], "_blank");
        }
      }
    );
    return false;
  }
}
