/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ConfigService } from "src/app/config.service";
import { User } from "src/app/shared/models/user";
import { Observable } from "rxjs";
import { UserInfo, ConfigUtente } from "src/app/shared/models/test/user-info";

@Injectable({
  providedIn: "root"
})
export class HomeService {
  baseUrl: string = "";

  constructor(private http: HttpClient, private config: ConfigService) {}

  postUser(user: User) {
    return this.http.get<any>(`${this.config.getBERootUrl(
      false
    )}/utenti?codiceFiscale=${user.codiceFiscale}&nome=${user.nome}&cognome=${
      user.cognome
    }&ruolo=${user.ruolo}&provider=${user.provider}
    `);
  }

  logout(){
    return this.http.get<any>(this.config.getBERootUrl(false) + `/utenti/logout`);
  }

  getUserData(userId: number): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/soggetti/${userId}`
    );
  }

  changeUser(user: User): Observable<User> {
    if (user.idSoggetto === 0 || user.idSoggetto === null) {
      return this.http.post<User>(
        `${this.config.getBERootUrl(false)}/utenti`,
        user
      );
    } else {
      return this.http.put<User>(
        `${this.config.getBERootUrl(false)}/utenti`,
        user
      );
    }
  }

  getConfigationByName(configName: string): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }
  
  getHomeData() : Observable<ConfigUtente> {
    return this.http.get<ConfigUtente>(this.config.getBERootUrl(false) + `/geoPlPfi/home`);
   }

  getUtenti(): Observable<User> {
    return this.http.get<User>(this.config.getBERootUrl(false) + `/utenti`);
  } 

  isUtenteAlreadyUpdate(): Observable<User> {
    return this.http.get<User>(this.config.getBERootUrl(false) + `/utenti/isUpdate`);
  } 
}
