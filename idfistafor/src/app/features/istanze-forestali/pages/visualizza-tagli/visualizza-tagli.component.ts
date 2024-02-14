/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";

import { TableHeader } from "src/app/shared/models/table-header";
import { ForestaliService } from "../../services/forestali.service";
import {
  ViewInstance,
  ViewInstanceTable,
  Content,
} from "src/app/shared/models/view-instance";
import { DialogService } from "src/app/shared/services/dialog.service";
import {
  ButtonType,
  DialogButtons,
} from "src/app/shared/error-dialog/error-dialog.component";
import { HomeModel } from "src/app/shared/models/home.model";
import { AuthService } from "./../../../../shared/services/auth.service";
import { ProfilioUtente } from "src/app/shared/models/profilio-utente.model";
import { TipoAccreditamento } from "src/app/shared/models/tipo-accreditamento.model";
import { TagliService } from "./../../services/tagli.service";
import { UserChoiceModel } from "src/app/shared/models/user-choice.model";

@Component({
  selector: "app-visualizza-tagli",
  templateUrl: "./visualizza-tagli.component.html",
  styleUrls: ["./visualizza-tagli.component.css"],
})
export class VisualizzaTagliComponent implements OnInit, OnDestroy {
  unsubscribe$ = new Subject<void>();
  sortedColumn = "numeroIstanza";
  tableData = [];

  /* tableHeaders: TableHeader[] = [
  {field: 'numeroIstanza', header: 'Numero istanza'},
  {field: 'dataInvio', header: 'Data invio'},
  {field: 'richiedente', header: 'Richiedente'},
  {field: 'comune', header: 'Comune'},
  {field: 'stato', header: 'Stato'}
];
 */
  tableHeaders: TableHeader[] = [
    { field: "nrIstanza", header: "Numero istanza" },
    { field: "dataInserimento", header: "Data inserimento" },
    { field: "descrizioneTipoIstanza", header: "Tipo Istanza" },
    { field: "descrizioneIntervento", header: "Descrizione" },
    { field: "tipoIntervento", header: "Tipo Intervento" },
    { field: "richiedente", header: "Richiedente" },
    { field: "comune", header: "Comune" },
    { field: "stato", header: "Stato" },
  ];

  paging: any;
  totalCount: number;
  sortField: any;
  user: HomeModel;
  ProfiloUtente = ProfilioUtente;
  userChoice: UserChoiceModel = null;
  TipoAccreditamentoEnum: any = TipoAccreditamento;

  constructor(
    private forestaliService: ForestaliService,
    private tagliService: TagliService,
    private authService: AuthService,
    private router: Router,
    private dialogService: DialogService
  ) {}

  ngOnInit() {
    this.forestaliService.getAdpfor().subscribe((res: UserChoiceModel) => {
      this.userChoice = res;
    });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }

  goToHome() {
    this.router.navigate([""], { queryParams: { modificaSelezione: true } });
  }

  getUpdatedTable(event) {
    this.paging = {
      page: event.page,
      limit: event.limit,
    };
    this.sortField = event.sortField;
    this.loadPlans(this.paging, event.field);
  }

  loadPlans(paging: any, sortField?: string) {
    this.tableData.length = 0;
    this.tagliService
      .getArrayOfInstance(paging.page, paging.limit, sortField)
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((res: any) => {
        if (res.totalElements !== 0) {
          this.totalCount = res.totalElements;
          res.content.forEach((element, index) => {
            if (!element.dataInvio) {
              element["dataInvio"] = {
                dayOfMonth: 0,
                month: "",
                year: 0,
              };
            }
          });

          res.content.forEach((element, index) => {
            this.tableData.push({
              idIntervento: element.idIntervento,
              nrIstanza: element.nrIstanza,
              idTipoIstanza: element.idTipoIstanza,
              dataInserimento:
                element.dataInserimento.dayOfMonth +
                "/" +
                element.dataInserimento.monthValue +
                "/" +
                element.dataInserimento.year,
              richiedente: element.richiedente,
              comune: element.comune.denominazioneComune,
              stato: element.stato,
              idStato: element.idStato,
              descrizioneTipoIstanza: element.descrizioneTipoIstanza,
              descrizioneIntervento: element.descrizioneIntervento,
              tipoIntervento: element.tipoIntervento,
            });
            if (element.dataInserimento.year === 0) {
              this.tableData[index].dataInserimento = "N/A";
            }
          });
        }
      });
  }

  getRowId(event) {}

  deleteRow(event) {
    this.dialogService.showDialog(
      true,
      "Confermare la cancellazione dell'istanza?",
      "Attenzione",
      DialogButtons.OK,
      "",
      (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          this.deleteConfirm(event);
        }
      }
    );
  }

  deleteConfirm(event) {
    this.tagliService.deleteIntervento(event).subscribe((res) => {
      console.log("delete done");
      this.loadPlans(this.paging, this.sortField);
    });
  }

  editRow(event) {
    this.router.navigate(["modifica-tagli/" + event]);
  }

  viewRow(event: any) {
    const viewMode: boolean = true;
    this.router.navigate(["modifica-tagli/" + event, { viewMode: viewMode }]);
  }
}
