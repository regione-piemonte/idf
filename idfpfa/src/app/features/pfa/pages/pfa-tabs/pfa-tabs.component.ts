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
import { takeUntil } from "rxjs/operators";
import { ActivatedRoute, Router } from "@angular/router";
import { Content, Detail } from "src/app/shared/models/dettaglio";
import { Documenti } from "src/app/shared/models/documenti";
import { Interventi } from "src/app/shared/models/interventi";
import { Eventi } from "src/app/shared/models/eventi";
import { DownloadService } from "src/app/shared/services/download.service";
import { NgbTabChangeEvent } from "@ng-bootstrap/ng-bootstrap";
import { ConfirmationService, Message } from "primeng/api";
import { CONST } from "../../../../shared/constants";
import { StatoIntervento } from "src/app/shared/models/stato-intervento";
import { StatoInterventoEnum } from "src/app/shared/enums";
import { DocumentoAllegato } from "src/app/shared/models/documento-allegato";

@Component({
  selector: "app-pfa-tabs",
  templateUrl: "./pfa-tabs.component.html",
  styleUrls: ["./pfa-tabs.component.css"],
})
export class PfaTabsComponent implements OnInit, OnDestroy {
  [x: string]: any;
  eventsHeader: TableHeader[];
  interventiHeader: TableHeader[];
  headers: TableHeader[];
  // dataMock$: Observable<any>;
  denom: Detail;
descrIntervento: String;
  interventiRow: number;
  eventiRow: number;
  selectedRow2: number;
  unsubscribe$ = new Subject<void>();
  routeId: number; // routeId = idGeoPlpfa
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

  activeTabId: string = "pfa";

  constructor(
    private pfaService: PfaSampleService,
    private activatedRoute: ActivatedRoute,
    private routeService: Router,
    private downloadService: DownloadService,
    private confirmService: ConfirmationService
  ) {}

  ngOnInit() {
    this.documentiHeader = PfaConfigComponent.getDocumentiHeader();
    this.eventsHeader = PfaConfigComponent.getEventsHeader();
    this.interventiHeader = PfaConfigComponent.getInterventiHeader();

    this.activatedRoute.params.subscribe((res) => {
      if (res.tab == "eventi") {
        this.activeTabId = "interventiEventiTab";
        this.choseButtons(1);
      } else if (res.tab == "interventi") {
        this.activeTabId = "interventiEventiTab";
        this.choseButtons(2);
      }
      this.routeId = res.id;
     const tabIndex: any = res.id
      localStorage.setItem("tabIndex", tabIndex);
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
    } else if ($event.nextId === "eventiTab") {
    } else if ($event.nextId === "interventiTab") {
    }
  }
  getUpdatedTableInterventi(event) {
    this.paging = {
      page: event.page,
      limit: event.limit,
    };

    this.eventField = event.field;
    this.getInterventiData(this.paging, this.eventField);
  }

  getUpdatedTableEventi(event) {
    this.paging = {
      page: event.page,
      limit: event.limit,
    };

    this.eventField = event.field;
    this.getEventData(this.paging, this.eventField);
  }

  getInterventiData(paging: any, eventField: any) {
    this.pfaService
      .getInterventi(this.routeId, paging.page, paging.limit, eventField)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((resp: Content<Interventi[]>) => {
        this.tableData = resp.content;
        this.totalCount = resp.totalElements;
        if (this.tableData) {
          this.tableData.forEach((item, index) => {
            item.dataInizio
              ? (this.tableData[
                  index
                ].dataInizioString = `${item.dataInizio.dayOfMonth}/${item.dataInizio.monthValue}/${item.dataInizio.year}`)
              : "";
            item.dataFine
              ? (this.tableData[
                  index
                ].dataFineString = `${item.dataFine.dayOfMonth}/${item.dataFine.monthValue}/${item.dataFine.year}`)
              : "";
          });
        }
      });
  }

  getEventData(paging: any, eventField: any) {
    this.pfaService
      .getEventi(this.routeId, paging.page, paging.limit, eventField)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((resp: Content<Eventi[]>) => {
        this.tableData = resp.content;
        this.totalCount = resp.totalElements;
        if (this.tableData) {
          this.tableData.forEach((item, index) => {
            this.tableData[index].dataEvento = this.tableData[
              index
            ].dataEvento.replace(/-/g, "/");
            // });
          });
        }
      });
  }

  getIdInterventi(value: any) {
    this.interventiRow = value;
  }

  getIdEventi(value: any) {
    this.eventiRow = value;
  }

  getTableDataId(value: any) {
    value && value.idIntervento
      ? ((this.interventiId = value.idIntervento, this.descrIntervento = value.descrStatoIntervento), (this.eventiId = undefined))
      : undefined;
    value && value.idEvento
      ? ((this.eventiId = value.idEvento), (this.interventiId = undefined))
      : undefined;
    this.disabledButton = false;
  }

  completa(data: Interventi[]) {
    data.forEach((intervento) => {
      if (intervento.idIntervento === this.interventiId) {
        switch (intervento.descrStatoIntervento) {
          case StatoInterventoEnum.Eseguito:
            this.enableButtonElimina = false;
            this.enableButtonCompleta = true;
            break;
          case StatoInterventoEnum.Concluso:
            this.enableButtonElimina = false;
            this.enableButtonCompleta = true;
            break;
          case StatoInterventoEnum.Programmato:
            this.enableButtonElimina = true;
            this.enableButtonCompleta = false;
            break;
          case StatoInterventoEnum.Incorso:
            this.enableButtonElimina = false;
            this.enableButtonCompleta = true;
            break;
          case StatoInterventoEnum.InCorsoTrasmissione:
            this.enableButtonElimina = false;
            this.enableButtonCompleta = false;
            break;
        }
      }
    });
  }

  deleteEventi(eventiId: number) {
    this.confirmService.confirm({
      message: CONST.CONFIRM_DELETE_EVENTI,
      accept: () => {
        this.pfaService.deleteEventi(eventiId).subscribe((res) => {
          this.getEventData(this.paging, this.eventField);
          this.message = [
            {
              severity: "success",
              summary: "Eliminato",
              detail: "Cancellazione avvenuta con successo!",
            },
          ];
        });
      },
    });
    this.eventiId = undefined;
    this.disabledButton = true;
  }

  deleteInterventi(interventiId: number) {
    this.confirmService.confirm({
      message: CONST.CONFIRM_DELETE_INTERVENTI,
      accept: () => {
        this.pfaService.deleteInterventi(interventiId).subscribe((res) => {
          this.getInterventiData(this.paging, this.eventField);
          this.message = [
            {
              severity: "success",
              summary: "Eliminato",
              detail: "Cancellazione avvenuta con successo!",
            },
          ];
        });
      },
    });
    this.interventiId = undefined;
    this.disabledButton = true;
  }

  deleteEventiOrInterventi() {
    if (this.eventiId !== undefined) {
      this.deleteEventi(this.eventiId);
    } else if (this.interventiId !== undefined) {
      this.deleteInterventi(this.interventiId);
    }
  }

  addIntervento() {
    ///FIX GP cambiar la ruta
    const idIntervento = 0;
    console.log("addIntervento: ");
    let tabIndex;
    this.activatedRoute.params.subscribe((params) => {
      tabIndex = params["id"]; // Obtendrá el valor "1"
      localStorage.setItem("tabIndex", tabIndex);
      console.log(tabIndex); // Muestra el valor en la consola
    });
    /* this.pfaService.creaIntervento(tabIndex).subscribe((res) => {
      console.log("interventi: ",res)
      if (res["ERROR"]) {
        this.dialogService.showDialogMsg("Errore", res["ERROR"], true);
        console.log("interventi ERROR: ", res);
        this.idIntervento = res.istanzaId;
      } else {
        this.idIntervento = res.istanzaId;
        console.log("interventi: ", this.idIntervento )
        localStorage.setItem("interventoNew",  this.idIntervento );
        this.routeService.navigate([
          "pfa",
          "inserisciNuovaPfa",
          this.idIntervento,
        ]);
        //this.openMappa();
      }
    }); */
    //console.log("interventi: ", this.idIntervento )
    this.routeService.navigate(["pfa", "inserisciNuovaPfa"])
  }

  addEvent() {
    // const ruteObj = this.obj;
    // ruteObj.id = this.routeId;
    // this.pfaService.setRouteParams(ruteObj);

    this.routeService.navigate(["pfa", "newEvent", this.routeId], {
      queryParams: this.obj,
    });
  }

  getFieldId(index: number) {
    this.selectedRow2 = index;
  }

  toPfaGestiti() {
    this.routeService.navigate(["pfa", "ricerca"]);
  }
  goToChiudi() {
    if(this.descrIntervento == StatoInterventoEnum.Concluso){
      this.routeService.navigate(
        ["pfa", "chiudi", this.routeId, { idInterventi: this.interventiId, viewMode: true }],
        {
          queryParams: this.obj,
        }
      );
    }else {
      this.routeService.navigate(
        ["pfa", "chiudi", this.routeId, { idInterventi: this.interventiId }],
        {
          queryParams: this.obj,
        }
      );
    }
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
      if(this.descrIntervento == "Programmato"){
        this.routeService.navigate([
          "pfa",
          "dettaglio",
          this.interventiId
        ]);
      } else {
        this.routeService.navigate([
          "pfa",
          "dettaglio",
          this.interventiId, {viewMode:true}
        ]);
      }
    }
    // this.routeId in this method is changed with  this.interventiRow
    //   this.routeService.navigate(
    //     ["pfa", "interventoEventoDettaglio",  this.routeId ],
    //     {

    // queryParams:[this.obj, this.interventiRow] ,

    //     }
    //   );
  }

  downloadEventiExcel() {
    let eventiExcel = {};
    eventiExcel["idGeoPfaEventi"] = this.routeId;

    this.downloadService
      .downloadEventiExcel(eventiExcel)
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

  downloadInterventiExcel() {
    let interventiExcel = {};
    interventiExcel["idGeoPfaInterventi"] = this.routeId;

    this.downloadService
      .downloadInterventiExcel(interventiExcel)
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

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  choseButtons(choice: number) {
    this.disabledButton = true;
    switch (choice) {
      case 1:
        if (this.showTable === true) {
          this.paging = { page: 1, limit: 5 };
          this.getEventData(this.paging, this.eventField);
        }
        this.isInterventiTab = false;
        this.showTable = true;
        this.headers = this.eventsHeader;
        break;
      case 2:
        if (this.showTable === true) {
          this.paging = { page: 1, limit: 5 };
          this.getInterventiData(this.paging, this.eventField);
        }
        this.showTable = true;
        this.isInterventiTab = true;
        this.headers = this.interventiHeader;
        break;
      default:
        break;
    }
  }

  getUpdateTable(event: any) {
    if (this.isInterventiTab === false) {
      this.getUpdatedTableEventi(event);
    } else {
      this.getUpdatedTableInterventi(event);
    }
  }

  InserisciNuovo() {
    if (this.isInterventiTab === false) {
      this.addEvent();
    } else if (this.isInterventiTab === true) {
      this.addIntervento();
    }
  }

  downloadExcel() {
    if (this.isInterventiTab === false) {
      this.downloadEventiExcel();
    } else if (this.isInterventiTab === true) {
      this.downloadInterventiExcel();
    }
  }
  documentToDownload(id: number) {
    this.pfaService.downloadPfaDocumenti(id).subscribe((response) => {
      let contentDisposition = response.headers.get("content-disposition");
      let filename = contentDisposition
        .split(";")[1]
        .split("filename")[1]
        .split("=")[1];
      this.downloadService.saveFile(response, filename);
    });
  }

  openMappa() {
    let geecoConf = 0;
    let id = 0;
    if (this.eventiId !== undefined) {
      geecoConf = 11;
      id = this.eventiId;
    } else if (this.interventiId !== undefined) {
      geecoConf = 10;
      id = this.interventiId;
    }
    this.pfaService
      .getCartograficoByIdUrl(geecoConf, "" + id)
      .subscribe((response: any) => {
        window.open(response["geecourl"], "_blank");
      });
    return false;
  }

  _openMappa() {
    let ids: string[] = [];
    let content = this.tableData;
    for (let i in content) {
      if (content[i]["idIntervento"]) {
        ids.push("" + content[i]["idIntervento"]);
      } else if (content[i]["idEvento"]) {
        ids.push("" + content[i]["idEvento"]);
      }
    }
    this.pfaService
      .getCartograficoPointsUrl(this.isInterventiTab ? 10 : 11, ids)
      .subscribe((response: any) => {
        window.open(response["geecourl"], "_blank");
      });
    return false;
  }

  formatNumber(value) {
    if (value) {
      return (value + "").replace(".", ",");
    }
    return "";
  }
}
