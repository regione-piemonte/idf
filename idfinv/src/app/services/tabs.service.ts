/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from '../shared/services/config.service';
import { AlberiCampioneDominante } from '../shared/models/alberi-campione-dominante';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { RelascopicaSemplice } from '../shared/models/relascopica-semplice';
import { Content } from '../shared/models/table-object';
import { User } from '../shared/models/user';

@Injectable({
  providedIn: 'root'
})
export class TabsService {

  constructor(private http: HttpClient, private config: ConfigService) { }

  getChampionTrees(codiceAds: number): Observable<AlberiCampioneDominante[]> {
    return this.http.get<AlberiCampioneDominante[]>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/campione/${codiceAds}`).pipe(
      map(
        (response: AlberiCampioneDominante[]) => {
          const obj: AlberiCampioneDominante[] = response;
          response.forEach((item, index) => {
            obj[index].semePollone = `${item.alberiSeme}/${item.alberiPollone}`; 
          });
          return obj;
        }
      )
    );
  }
  
  getDettaglioTableDataSemplice(codiceAds: string): Observable<RelascopicaSemplice[]> {
    return this.http.get<RelascopicaSemplice[]>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/relascopicaSemplice/${codiceAds}`);
  }

  getDettaglioTableDataCompleta(codiceAds: string): Observable<Content<RelascopicaSemplice[]>> {
    return this.http.get<Content<RelascopicaSemplice[]>>(
      `${this.config.getBERootUrl(
        false
      )}/areaDiSaggio/search/relascopicaCompleta/${codiceAds}`
    );
  }

  getDettaglioCavallettatiCompletaTable(codiceAds: string, page: number, limit: number, sortField: string): Observable<Content<any[]>> {
    let sortQuery: string;
    if(sortField) {
      sortQuery = `&sort=${sortField}`;
    }
    return this.http.get<Content<any[]>>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/relascopicaCompletaCAV/${codiceAds}?page=${page}&limit=${limit}${
      sortQuery ? sortQuery : ""}`);
  }

  getAlberiCavalletattiTable(codiceAds: string, limit: number, page: number, sortField: string): Observable<Content<AlberiCampioneDominante[]>> {
    let sortQuery: string;
    if(sortField) {
      sortQuery = `&sort=${sortField}`;
    }
    return this.http.get<Content<AlberiCampioneDominante[]>>(`${this.config.getBERootUrl(false)}/areaDiSaggio/search/alberiCAV/${codiceAds}?page=${page}&limit=${limit}${
      sortQuery ? sortQuery : ""}`);
  }
  getHomeData() {
    return this.http.get<User>(this.config.getBERootUrl(false) + `/utenti`);
   }

   isUtenteAlreadyUpdate(): Observable<User> {
    return this.http.get<any>(this.config.getBERootUrl(false) + `/utenti/isUpdate`);
  } 

  logout(){
    return this.http.get<any>(this.config.getBERootUrl(false) + `/utenti/logout`);
  }
}
