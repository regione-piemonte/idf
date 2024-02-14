/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router } from "@angular/router";
import { Subject } from "rxjs";
import { LoadingService } from "./shared/services/loading.service";
import { takeUntil } from "rxjs/operators";
import { ErrorService } from "./shared/services/error.service";
import { DialogService } from "./shared/services/dialog.service";
import { ErrorCode } from "./shared/models/error.model";
import { DialogButtons, ButtonType } from "./shared/models/dialog.model";
import { PfaSampleService } from "src/app/shared/services/pfa-sample.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit, OnDestroy {
  title = "app";
  unsubscribe$ = new Subject<void>();
  isLoadingOperation: boolean = false;
  errorMsg: string;

  idPfa: number;

  giveMeJustNumberToDisplayHeader: number;

  constructor(
    private router: Router,
    private pfaService: PfaSampleService,
    private loadingService: LoadingService,
    private errorService: ErrorService,
    private dialogService: DialogService
  ) {}
  ngOnInit() {
    this.changeHeader();
    this.subscribeLoading();
    this.subscribeError();
    /*if (window.location.href.indexOf("/idfistafor/?return=") > -1) {
      let urlParams = new URLSearchParams(window.location.search);
      let idIntervento = urlParams.get("return");
      this.router.navigate(["return-modifica/" + idIntervento]);
    } else if (window.location.href.indexOf("/idfistafor/?returnvid=") > -1) {
      let urlParams = new URLSearchParams(window.location.search);
      let idIntervento = urlParams.get("returnvid");
      this.router.navigate(["return-modifica-vid/" + idIntervento]);
    } else if (window.location.href.indexOf("/idfistafor/?returntagli=") > -1) {
      let urlParams = new URLSearchParams(window.location.search);
      let idIntervento = urlParams.get("returntagli");
      this.router.navigate(["return-modifica-tagli/" + idIntervento]);
    } else if (window.location.href.indexOf("/idfistafor/?returnIntervento=") > -1) {
      let urlParams = new URLSearchParams(window.location.search);
      let idIntervento = urlParams.get("returnIntervento");
      this.router.navigate(["return-modifica-tagli/" + idIntervento]);
    }*/
  }

  subscribeLoading(): void {
    this.loadingService.loadingObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((isActiveLoading: boolean) => {
        this.isLoadingOperation = isActiveLoading;
      });

    let urlParams = new URLSearchParams(window.location.search);
    if (window.location.href.indexOf("/idfpfa/?returnEvento=") > -1) {
      let idEvento = urlParams.get("returnEvento");

      this.pfaService.getIdPfaByIdEvento(idEvento).subscribe((resp: number) => {
        this.router.navigate([
          "pfa",
          "newEvent",
          resp,
          { idEvento: idEvento, viewMode: true },
        ]);
      });

      this.pfaService
        .ricalcolaSuperficiEventoFromGeeco(idEvento)
        .subscribe((res) =>
          console.log("done: ricalcolaSuperficiEventoFromGeeco")
        );
    }
   
    if (window.location.href.indexOf("/idfpfa/?returnIntervento=") > -1) {
      console.log("estas Aqui index  ");
      let idIntervento = urlParams.get("returnIntervento");
      this.pfaService.getStatoByIdIntervento(idIntervento).subscribe((res) => {
        console.log("estas aqui index:____",res);
        if (res.stato.idStatoIntervento == 1) {
          this.router.navigate([
            "pfa",
            "return-modifica-tagli",
            idIntervento
          ]);
        } else if (res.stato.idStatoIntervento > 1) {
          this.idPfa = res.idPfa;
          this.pfaService
            .ricalcolaParticelleFromGeeco(+idIntervento)
            .subscribe((res) => {
              this.router.navigate(
                ["pfa", "chiudi", this.idPfa, { idInterventi: idIntervento }],
                {
                  queryParams: {},
                }
              );
            });
        }
      });
    }
    //console.log("estas Aqui ");
    let idPfa = urlParams.get("returnPfa");
    if (idPfa) {
      this.router.navigate(
        ["pfa", "tabs", idPfa]
        // , {
        //   queryParams: {
        //     idComune: this.selectedRow.idComune,
        //     idPopolamento: this.selectedRow.idPopolamento,
        //   },
        // }
      );
    }
  }

  subscribeError() {
    this.errorService.errorObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((response: string) => {
        switch (response) {
          case ErrorCode.E00:
            this.errorMsg = "Si è verificato un errore generico.";
            break;
          case ErrorCode.E01:
            this.errorMsg = "Mancano i dati.";
            break;
          case ErrorCode.E02:
            this.errorMsg = "Errore dati.";
            break;
          case ErrorCode.E03:
            this.errorMsg = "Errore di convalida.";
            break;
          default:
            this.errorMsg = "Si è verificato un errore generico.";
            break;
        }
        console.log("ERROR MSG: ", this.errorMsg);
        this.dialogService.showDialog(
          true,
          this.errorMsg,
          "Error occured",
          DialogButtons.OK,
          "",
          (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              console.log("BUTTON WORKS");
            }
          }
        );
      });
  }

  changeHeader() {
    if (this.router.url.startsWith("/personali", 4)) {
      this.giveMeJustNumberToDisplayHeader = 1;
      return this.giveMeJustNumberToDisplayHeader;
    } else if (this.router.url.startsWith("/choose", 4)) {
      this.giveMeJustNumberToDisplayHeader = 2;
      return this.giveMeJustNumberToDisplayHeader;
    }
    return 0;
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
