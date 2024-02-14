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
    dialogIconClass?: string;
    btnCallback?: Function;
  }
  