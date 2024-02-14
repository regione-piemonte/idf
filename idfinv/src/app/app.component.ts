/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { LoadingService } from './shared/services/loading.service';
import { ErrorService } from './shared/services/error.service';
import { DialogService } from './shared/services/dialog.service';
import { ErrorCodes } from './shared/models/error-codes';
import { DialogButtons, ButtonType, DialogIconType } from './shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'app';
  unsubscribe$ = new Subject<void>();
  isLoadingOperation: boolean = false;
  errorMsg: string;

  constructor(private loadingService: LoadingService, private errorService: ErrorService, 
    private dialogService: DialogService, private route: ActivatedRoute,private router: Router) {}

  ngOnInit() {
    this.subscribeLoading();
    this.subscribeError();
    let urlParams = new URLSearchParams(window.location.search);
    let idgeoPtAds = urlParams.get('idgeoPtAds');
    if(idgeoPtAds){
      this.router.navigate(["/forestale", "dati-generali", idgeoPtAds]);
    }
  }

  subscribeLoading(): void {
    this.loadingService.loadingObservable
    .pipe(takeUntil(this.unsubscribe$))
    .subscribe(
      (isActiveLoading: boolean) => {
        this.isLoadingOperation = isActiveLoading;
      }
    );
  }

  subscribeError() {
    this.errorService.errorObservable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(
        (errorMsg: string) => {          
          this.dialogService.showDialog(true, errorMsg, 'Error occured', DialogButtons.OK, '', (buttonId: number): void => {
            if (buttonId === ButtonType.OK_BUTTON) {              
            }
          }, DialogIconType.WARNING);
        }
      )
  }

  ngOnDestroy() {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
    this.unsubscribe$.unsubscribe();
  }


}
