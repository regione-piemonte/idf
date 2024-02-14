/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { Router } from "@angular/router";
import { TableHeader } from "src/app/shared/models/table-header";
import { Dettaglio } from "src/app/shared/models/dettaglio";
import { DownloadService } from "src/app/shared/services/download.service";
import { takeUntil } from "rxjs/operators";
import { Subject } from "rxjs";

@Component({
  selector: "app-gestiti-pfa",
  templateUrl: "./gestiti-pfa.component.html",
  styleUrls: ["./gestiti-pfa.component.css"],
})
export class GestitiPfaComponent implements OnInit {
  @Input() cols: TableHeader[];
  @Input() pfas: Dettaglio[];
  @Input() totalItems: number;
  selectedRow: any;
  unsubscribe$ = new Subject<void>();
  @Output() emitLoadPfa: EventEmitter<any> = new EventEmitter();

  // pfaId: number;
  constructor(
    private pfaService: PfaSampleService,
    private routerService: Router,
    private downloadService: DownloadService
  ) {}

  ngOnInit() {}

  // getFieldId(dataId: number, index: number) {
  //   this.selectedRow = index;

  //   this.pfaId = dataId;
  // }

  getUpdatedTable(event) {
    // this.paging = {
    //   page: event.page,
    //   limit: event.limit
    // };
    // this.emitLoadPfa.emit({ paging: this.paging, event: event.field });

    this.emitLoadPfa.emit(event);
  }

  getRowId(value: any) {
    this.selectedRow = value;
    this.visualizzaDettaglio();
  }

  visualizzaDettaglio() {
    this.routerService.navigate(["pfa", "tabs", this.selectedRow.id], {
      queryParams: {
        idComune: this.selectedRow.idComune,
        idPopolamento: this.selectedRow.idPopolamento,
      },
    });
  }

  downloadExcel() {
    let exportExcel = {};
    exportExcel["pfaExcelDto"] = this.pfas.map((value) => {
      const obj: any = {};

      obj.idGeoPfa = value.idGeoPlPfa;
      return obj;
    });

    this.downloadService
      .downloadExcelFile(exportExcel)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((resp) => {
        let contentDisposition = resp.headers.get("content-disposition");
        let filename = contentDisposition
          .split(";")[1]
          .split("filename")[1]
          .split("=")[1];
        this.downloadService.saveFile(resp, filename);
      });
  }

  back() {
    // this.routerService.navigate(["pfa", "choose"]);
  }

  openMappa(){
    let ids:string[] = [];
    let content = this.pfas;
    for(let i in content){
      ids.push(''+content[i]['idGeoPlPfa']);
    }
    this.pfaService.getCartograficoPointsUrl(13,ids)
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }
}
