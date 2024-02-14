/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from "@angular/core";
import { Subject } from "rxjs";
import { takeUntil } from "rxjs/operators";
import { LoadingService } from "./shared/services/loading.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit, OnDestroy {
  title = "app";
  unsubscribe$ = new Subject<void>();
  isLoadingOperation = false;
  errorMsg: string;

  constructor(private loadingService: LoadingService) {}

  ngOnInit(): void {
    this.subscribeLoading();
  }

  subscribeLoading(): void {
    this.loadingService.loadingObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((isActiveLoading: boolean) => {
        this.isLoadingOperation = isActiveLoading;
      });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }
}
