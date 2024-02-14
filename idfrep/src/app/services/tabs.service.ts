/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from '../shared/services/config.service';

import { User } from '../shared/models/user';

@Injectable({
  providedIn: 'root'
})
export class TabsService {

  constructor(private http: HttpClient, private config: ConfigService
    ) { }

  getHomeData() {
    return this.http.get<User>(this.config.getBERootUrl(false) + `/utenti`);
   }
   
  isUtenteAlreadyUpdate(){
    return this.http.get<any>(this.config.getBERootUrl(false) + `/utenti/isUpdate`);
  }

  logout(){
    return this.http.get<any>(this.config.getBERootUrl(false) + `/utenti/logout`);
  }
}
