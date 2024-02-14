/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { TableHeader } from "src/app/shared/models/table-header";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";
import { PfaConfigComponent } from "../../pfa-config";
import { Subject, from } from "rxjs";
import { ActivatedRoute, Router } from "@angular/router";
import { Interventi } from "src/app/shared/models/interventi";
import { Eventi } from "src/app/shared/models/eventi";
import { DownloadService } from "src/app/shared/services/download.service";
import { NgbTabChangeEvent } from "@ng-bootstrap/ng-bootstrap";
import { ConfirmationService, Message } from "primeng/api";
import { DocumentoAllegato } from "src/app/shared/models/documento-allegato";
import { Detail } from "src/app/shared/models/dettaglio";

@Component({
  selector: "app-pfa-tabs",
  templateUrl: "./pfa-tabs.component.html",
  styleUrls: ["./pfa-tabs.component.css"],
})
export class PfaTabsComponent implements OnInit, OnDestroy {
  headers: TableHeader[];
  denom: Detail;

  interventiRow: number;
  eventiRow: number;
  selectedRow2: number;
  unsubscribe$ = new Subject<void>();
  routeId: number;
  obj: any;
  documentiHeader: TableHeader[];
  documenti: DocumentoAllegato[];

  interventi: Interventi[];
  eventi: Eventi[];
  tableData: any[];

  paging: any;
  eventField: any;
  totalCount: number;
  disabledButton: boolean = true;
  isInterventiTab: boolean = false;
  showTable: boolean = false;
  interventiId: number;
  eventiId: number;
  message: Message[] = [];
  enableButtonElimina: boolean = true;
  enableButtonCompleta: boolean = true;

  activeTabId:string='pfa';

  constructor(
    private pfaService: PfaSampleService,
    private activatedRoute: ActivatedRoute,
    private routeService: Router,
    private downloadService: DownloadService,
    private confirmService: ConfirmationService
  ) {}

  ngOnInit() {  
    this.documentiHeader = PfaConfigComponent.getDocumentiHeader();
    this.activatedRoute.params.subscribe((res) => {
      this.routeId = res.id;
      this.activatedRoute.queryParams.subscribe((params) => {
        this.obj = {
          idComune: params.idComune,
          idPopolamento: params.idPopolamento,
        };

        this.pfaService
          .getDettaglioById(this.routeId, this.obj)
          .subscribe((resp) => (this.denom = resp));
      });
    });
  }

  tabCall($event?: NgbTabChangeEvent) {
    this.disabledButton = true;
    this.activeTabId = $event.nextId;
    if ($event.nextId === "documenti") {
      this.pfaService.getAllDocumenti(this.routeId).subscribe((resp) => {
        this.documenti = resp;
      });
      // } else if ($event.nextId === "interventiEventiTab") {
    }
  }

  getTableDataId(value: any) {
    value && value.idIntervento
      ? ((this.interventiId = value.idIntervento), (this.eventiId = undefined))
      : undefined;
    value && value.idEvento
      ? ((this.eventiId = value.idEvento), (this.interventiId = undefined))
      : undefined;
    this.disabledButton = false;
  }

  getFieldId(index: number) {
    this.selectedRow2 = index;
  }

  toPfaGestiti() {
    this.routeService.navigate(["pfa", "ricerca"]);
  }
  goToChiudi() {
    this.routeService.navigate(
      ["pfa", "chiudi", this.routeId, { idInterventi: this.interventiId }],
      {
        queryParams: this.obj,
      }
    );
  }

  goToDettaglio() {
    if (this.eventiId !== undefined) {
      this.routeService.navigate([
        "pfa",
        "newEvent",
        this.routeId,
        { viewMode: true, idEvento: this.eventiId },
      ]);
    } else if (this.interventiId !== undefined) {
      this.routeService.navigate([
        "pfa",
        "interventoEventoDettaglio",
        this.routeId,
        { idInterventi: this.interventiId, interventi: true },
      ]);
    }
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  documentToDownload(id: number) {
    this.downloadService.downloadPfaDocumenti(id).subscribe((response) => {
      let contentDisposition = response.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
      this.downloadService.saveFile(response, filename);
    });
  }

  openMappa(){
    this.pfaService.getCartograficoPointsUrl(17,[''+ this.routeId])
    .subscribe(
      (response: any) => {
          window.open(response['geecourl'], "_blank");
      }
    );
    return false;
  }

  formatNumber(value){
    if(value){
      return (value + '').replace('.',',');
    }
    return '';
  }
}
