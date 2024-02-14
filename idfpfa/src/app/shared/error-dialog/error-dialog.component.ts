/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit, Input } from "@angular/core";
import { IButton, DialogModel } from "../models/dialog.model";
import { DialogService } from "../services/dialog.service";

@Component({
  selector: "app-error-dialog",
  templateUrl: "./error-dialog.component.html",
  styleUrls: ["./error-dialog.component.css"]
})
export class ErrorDialogComponent implements OnInit {
  display: boolean;
  showDialog?: boolean;
  message?: string;
  title?: string;
  buttons?: IButton[];
  redirectUrl: string;
  styleClasses: string[];
  dialogIconClasses: string[];
  btnFun: Function;
  iconClass: string;


  constructor(private dialogService: DialogService) {}

  ngOnInit() {
    this.dialogService.promptObject.subscribe((response: DialogModel) => {
      this.display = response.showDialog;
      this.message = response.message;
      this.title = response.title;
      this.buttons = response.buttons;
      // this.setDialogCssClasses(response.dialogType);
      this.btnFun = response.btnCallback;
      this.redirectUrl = response.redirectUrl;
      this.iconClass = response.dialogIconClass;
    });
  }

  btnClicked(btnInfo: IButton) {
    this.display = false;
    if(this.btnFun) {
      //console.log('BUTTON IS PRESSED');
      this.btnFun(btnInfo.id);
    }
  }
}

export class DialogButtons {
  public static OK_CANCEL: IButton[] = [
    { id: 0, label: 'CANCEL' },
    { id: 1, label: 'OK' }
  ];
  public static OK: IButton[] = [
    { id: 1, label: 'OK' }
  ];
  // public static OK_CANCEL_CHIUDI: IButton[] = [
  //   { id: 3, label: 'BUTTONS.CHIUDI_BUTTON' },
  //   { id: 0, label: 'BUTTONS.CANCEL_BUTTON' },
  //   { id: 1, label: 'BUTTONS.OK_BUTTON' }
  // ];
  // public static CHIUDI: IButton[] = [{ id: 3, label: 'BUTTONS.CHIUDI_BUTTON' }];
  // public static CONFERMA_CHIUDI: IButton[] = [
  //   { id: 3, label: 'BUTTONS.CHIUDI_BUTTON' },
  //   { id: 1, label: 'BUTTONS.CONFERMA_BUTTON' }
  // ];
}
export enum DialogIconType {
  INFORMATION = 'fa fa-info-circle dialog-icon text-warning',
  WARNING = 'fa fa-exclamation-triangle dialog-icon text-danger',
  ERROR = 'fa fa-times-circle dialog-icon',
  SUCCESS = 'fa fa-check dialog-icon'
}
export enum DialogType {
  INFORMATION,
  WARNING,
  ERROR,
  SUCCESS
}
export enum ButtonType {
  CANCEL_BUTTON,
  OK_BUTTON,
  CLOSE_BUTTON
}

