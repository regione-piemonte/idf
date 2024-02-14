/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { ButtonType, DialogButtons, DialogIconType, DialogModel, IButton } from "../models/dialog.model";

@Injectable({
  providedIn: "root"
})
export class DialogService {
  promptObject = new Subject<DialogModel>();

  constructor() {}

  showDialog(
    displayDialog?: boolean,
    message?: string,
    title?: string,
    buttons?: IButton[],
    redirectUrl?: string,
    callback?: Function
  ) {
    const dialog: DialogModel = {
      showDialog: displayDialog,
      message: message,
      title: title,
      buttons: buttons,
      redirectUrl: redirectUrl,
      btnCallback: callback
    };
    return this.promptObject.next(dialog);
  }

  showDialogType(displayDialog?: boolean, message?: string, title?: string, buttons?: IButton[], redirectUrl?: string, callback?: Function, dialogIconClass = "fa fa-info-circle dialog-icon text-warning") {
    const dialog: DialogModel = {
      showDialog: displayDialog,
      message: message,
      title: title,
      buttons: buttons,
      redirectUrl: redirectUrl,
      btnCallback: callback,
      dialogIconClasses: dialogIconClass
    };
    return this.promptObject.next(dialog);
  }

  showDialogMsg(title:string, msg:string,isError:boolean){
    this.showDialogType(
      true, 
      msg, 
      title, 
      DialogButtons.OK, 
      '',
       (buttonId: number): void => {
        if (buttonId === ButtonType.OK_BUTTON) {
          console.log('BUTTON WORKS');
        }
      },
   
    isError?DialogIconType.ERROR:DialogIconType.ERROR
    );
  }
}
