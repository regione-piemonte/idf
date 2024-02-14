/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export interface IButton {
  id?: number;
  label?: string;
  icon?: string;
}

export class DialogModel {
  showDialog?: boolean;
  message?: string;
  title?: string;
  buttons?: IButton[];
  redirectUrl?: string;
  styleClasses?: string[];
  dialogIconClasses?: string;
  dialogIconClass?: string;
  btnCallback?: Function;
}

export class DialogButtons {
  public static OK_CANCEL: IButton[] = [
    { id: 0, label: "CANCEL" },
    { id: 1, label: "OK" },
  ];
  public static OK: IButton[] = [{ id: 1, label: "OK" }];
}

export enum DialogIconType {
  INFORMATION = "fa fa-info-circle dialog-icon",
  WARNING = "fa fa-exclamation-triangle dialog-icon",
  ERROR = "fa fa-times-circle dialog-icon",
  SUCCESS = "fa fa-check dialog-icon",
}
export enum DialogType {
  INFORMATION,
  WARNING,
  ERROR,
  SUCCESS,
}
export enum ButtonType {
  CANCEL_BUTTON,
  OK_BUTTON,
  CLOSE_BUTTON,
}
