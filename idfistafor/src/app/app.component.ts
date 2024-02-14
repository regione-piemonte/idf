/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";

import { LoadingService } from "./shared/services/loading.service";
import { ErrorService } from "./shared/services/error.service";
import { ErrorCode } from "../app/shared/models/error.model";
import { DialogService } from "./shared/services/dialog.service";
import {
  DialogButtons,
  ButtonType,
} from "./shared/error-dialog/error-dialog.component";
import { Router } from "@angular/router";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
})
export class AppComponent implements OnInit, OnDestroy {
  title = "app";
  unsubscribe$ = new Subject<void>();
  isLoadingOperation: boolean = false;
  errorMsg: string;

  constructor(
    private loadingService: LoadingService,
    private errorService: ErrorService,
    private dialogService: DialogService,
    private router: Router
  ) {}

  ngOnInit() {
    this.subscribeLoading();
    this.subscribeError();
    if (window.location.href.indexOf("/idfistafor/?return=") > -1) {
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
    } else if (window.location.href.indexOf("/idfistafor/#/")) {
      //this.thirdFormData$ = this.tagliService.getStep3(this.editMode)
      console.log(".....> ", localStorage.getItem("particellaGuardada"));
      localStorage.removeItem("particellaGuardada");
    }
  }

  subscribeLoading(): void {
    this.loadingService.loadingObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((isActiveLoading: boolean) => {
        this.isLoadingOperation = isActiveLoading;
      });
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
          case ErrorCode.BAD_REQUEST:
            this.errorMsg =
              "Errore. Valori non validi. Ricontrollare i dati caricati";
            break;
          default:
            this.errorMsg = "Si è verificato un errore generico.";
            break;
        }
        //console.log('ERROR MSG: ', this.errorMsg);
        this.dialogService.showDialog(
          true,
          this.errorMsg,
          "Error occured",
          DialogButtons.OK,
          "",
          (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {
              //console.log('BUTTON WORKS');
            }
          }
        );
      });
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
