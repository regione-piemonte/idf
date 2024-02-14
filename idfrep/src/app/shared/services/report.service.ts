/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ConfigService } from "./config.service";
import { Observable } from "rxjs";
import { SelectItem } from "primeng/components/common/selectitem";
import { map } from "rxjs/operators";
import { p } from "@angular/core/src/render3";



@Injectable({
  providedIn: "root"
})
export class ReportService {
  constructor(private httpClient: HttpClient, private config: ConfigService) {}

  getConfigationByName(configName: string): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }

  getReportByAdsType(adsType:string): Observable<SelectItem[]> {
    return this.httpClient
      .get<any[]>(
        `${this.config.getBERootUrl(false)}/report/csv/list?idAdsType=${adsType}`
      )
      .pipe(
        map((response: any[]) => {
          const reportDropdown: SelectItem[] = [];
          response.forEach(item => {
            reportDropdown.push({
              label: item.nome,
              value: item.id
            });
          });
          return reportDropdown;
        })
      );
  }

  getAdsTypeList(): Observable<SelectItem[]> {
    return this.httpClient
      .get<any[]>(
        `${this.config.getBERootUrl(false)}/report/csv/ads/list`
      )
      .pipe(
        map((response: any[]) => {
          const reportDropdown: SelectItem[] = [];
          response.forEach(item => {
            reportDropdown.push({
              label: item.nome,
              value: item.id
            });
          });
          return reportDropdown;
        })
      );
  }

  getElaborateReportData(adsType:number,idReport:number,filters:any,data:any){
    console.log('service getElaborateReportData call');
    let queryStringFilters = this.getQueryStringFilters(filters);
    console.log('QueryStringFilters: ' + queryStringFilters);
    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/report/csv/elabora?adsType=${adsType}&idReport=${idReport}${queryStringFilters}`,
    data);
  }

  private getQueryStringFilters(filters:JSON){
    let parameters = '';
    for(let key in filters){
      parameters+='&' + key + '=' + filters[key];
    }
    return parameters;
  }

}
