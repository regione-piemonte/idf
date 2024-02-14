/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { CONST } from "src/app/shared/constants";
import { HomeModel } from "src/app/shared/models/home.model";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  redirectUrl: string;
  hasPermision: boolean;
  user: HomeModel;

  checkPermision(): boolean {
    this.user = JSON.parse(sessionStorage.getItem(CONST.USER_KEY_STORE));
    if (this.user !== null) {
      return true;
    }
    return false;
  }

  currentUser(): HomeModel {
    let user: HomeModel = JSON.parse(
      sessionStorage.getItem(CONST.USER_KEY_STORE)
    );
    return user;
  }
}
