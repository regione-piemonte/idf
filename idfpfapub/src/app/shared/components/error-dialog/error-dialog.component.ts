/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Component, OnInit } from "@angular/core";
import {
  IButton,
  DialogIconType,
  DialogModel
} from "../../models/dialog.model";
import { DialogService } from "../../services/dialog.service";

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
  iconClass: string = DialogIconType.INFORMATION;

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
    });
  }

  btnClicked(btnInfo: IButton) {
    this.display = false;
    if (this.btnFun) {
      console.log("BUTTON IS PRESSED");
      this.btnFun(btnInfo.id);
    }
  }
}
