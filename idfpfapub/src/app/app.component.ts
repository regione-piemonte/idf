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
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit, OnDestroy {
  title = "app";
  unsubscribe$ = new Subject<void>();
  isLoadingOperation: boolean = false;
  errorMsg: string;

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
  }

  subscribeLoading(): void {
    this.loadingService.loadingObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((isActiveLoading: boolean) => {
        this.isLoadingOperation = isActiveLoading;
      });

    let urlParams = new URLSearchParams(window.location.search);
    let idPfa = urlParams.get('returnPfa');
    if(idPfa){
      this.router.navigate(["pfa", "tabs", idPfa]
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
