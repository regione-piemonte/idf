/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { ConfigService } from "../shared/services/config.service";
import { TipoAds } from "../shared/models/tipo-ads";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { SelectItem } from "primeng/components/common/selectitem";
import { StatoAds } from "../shared/models/statoAds";


@Injectable({
  providedIn: "root"
})
export class SearchService {
  constructor(private http: HttpClient, private config: ConfigService) {}

  getTipoAds(): Observable<SelectItem[]> {
    return this.http
      .get<TipoAds[]>(`${this.config.getBERootUrl(false)}/tipoAds`)
      .pipe(
        map((response: TipoAds[]) => {
          const obj: SelectItem[] = [];
          response.forEach(item => {
            obj.push({ label: item.descrTipoAds, value: item.idTipoAds });
          });
          return obj;
        })
      );
  }

  getTipoAdsCleaned(): Observable<SelectItem[]> {
    return this.http
      .get<TipoAds[]>(`${this.config.getBERootUrl(false)}/tipoAds`)
      .pipe(
        map((response: TipoAds[]) => {
          const obj: SelectItem[] = [];
          response.forEach(item => {
            if(item.codTipoAds != '0'){
              obj.push({ label: item.descrTipoAds, value: item.idTipoAds });
            }
          });
          return obj;
        })
      );
  }

  downloadExcelFile(codiceAdsList: any) {
    return this.http.post(`${this.config.getBERootUrl(false)}/areaDiSaggio/search`, codiceAdsList, {observe: 'response',	responseType: 'blob'});
  }

  downloadSearchResults2Excel(queryString: string) {    
    return this.http.post(`${this.config.getBERootUrl(false)}/areaDiSaggio/export2excel?${queryString ? queryString : ''}`, {}, {observe: 'response',	responseType: 'blob'});
  }

  downloadSearchResults2ExcelWithoutDetails(queryString: string) {    
    return this.http.post(`${this.config.getBERootUrl(false)}/areaDiSaggio/export2excel/basicinfo?${queryString ? queryString : ''}`, {}, {observe: 'response',	responseType: 'blob'});
  }

  downloadExcelFileDettaglio(codiceAds: number, tipologia: string) {
    if(tipologia === 'Semplice') {
      return this.http.get(`${this.config.getBERootUrl(false)}/areaDiSaggio/excelRelascopicaSemplice/${codiceAds}`, {observe: 'response',	responseType: 'blob'});
    } else if(tipologia === 'Completa') {
      return this.http.get(`${this.config.getBERootUrl(false)}/areaDiSaggio/excelRelascopicaCompleta/${codiceAds}`, {observe: 'response',	responseType: 'blob'});
    }
  }

  downloadExcelFileTabs(codiceAds: number) {
    return this.http.get(`${this.config.getBERootUrl(false)}/areaDiSaggio/export/${codiceAds}`, {observe: 'response',	responseType: 'blob'});
  }

  getStatoScheda(): Observable<SelectItem[]> {
    return this.http
      .get<StatoAds[]>(`${this.config.getBERootUrl(false)}/statoAds`)
      .pipe(
        map((response: StatoAds[]) => {
          const obj: SelectItem[] = [];
          response.forEach(item => {
            obj.push({ label: item.descrStatoAds, value: item.idStatoAds });
          });
          return obj;
        })
      );
  }


}

