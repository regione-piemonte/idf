/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable} from "@angular/core";
import { HttpClient} from "@angular/common/http";
import { ConfigService } from "./config.service";
import { Dettaglio, Content, Detail } from "../models/dettaglio";
import { Observable, BehaviorSubject } from "rxjs";
import { Provincia } from "../models/provincia";
import { SelectItem } from "primeng/components/common/selectitem";
import {map} from "rxjs/operators";
import { RouteParams } from "../models/routeParams";
import { DocumentoAllegato } from "../models/documento-allegato";
import { Comuni } from "../models/comune";

@Injectable({
  providedIn: "root",
})
export class PfaSampleService {
  private routeParams = new BehaviorSubject<RouteParams>(null);
  currentRouteParams = this.routeParams.asObservable();

  constructor(private http: HttpClient, private config: ConfigService) {}

  getAgidUrlConfigation(): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/configurazione/agid`
    );
  }

  getAllProvincia(): Observable<SelectItem[]> {
    return this.http
      .get<Provincia[]>(`${this.config.getBERootUrl(false)}/provincia/search`)
      .pipe(
        map((response: Provincia[]) => {
          const provinciaDropdown: SelectItem[] = [];
          response.forEach((item) => {
            provinciaDropdown.push({
              label: item.denominazioneProv,
              value: item.istatProv,
            });
          });

          return provinciaDropdown;
        })
      );
  }

  getComuniByProvincia(istatCode: string): Observable<SelectItem[]> {
    return this.http
      .get<any>(
        `${this.config.getBERootUrl(false)}/comuni/provincia/${istatCode}`
      )
      .pipe(
        map((response: Comuni[]) => {
          const comuneToDropdown: SelectItem[] = [];
          response.forEach((item, i) => {
            comuneToDropdown.push({
              label: item.denominazioneComune,
              value: item.idComune,
            });
          });

          return comuneToDropdown;
        })
      );
  }

  filterDenominazione(istatProv, idComune){
    let queryString = '';
    queryString += istatProv? `&istatProv=${istatProv}` : "";
    queryString += idComune? `&idComune=${idComune}` : "";
    return this.http.get<any>(
      this.config.getBERootUrl(false) +
        `/filter/descrizioni?${queryString}`
    );
  }

  getAllPfa(page: number, limit: number, sortField?: string) {
    let sortQuery: string;
    if (sortField) {
      sortQuery = `&sort=${sortField}`;
    }

    return this.http.get<Content<Dettaglio[]>>(
      this.config.getBERootUrl(false) +
        `/search?page=${page}&limit=${limit}${
          sortQuery ? sortQuery : ""
        }`
      );
    }

  getPfaSearch(objFilter: any, objPaging:any) {
    // provinciaId: string, comuneId: number, page: number, limit: number
    let queryString =`page=${objPaging.page}`;
    queryString += `&limit=${objPaging.limit}`;

    queryString += objPaging.sort? `&sort=${objPaging.sort}` : "";
    queryString += objFilter.provincia? `&istatProv=${objFilter.provincia}` : "";
    queryString += objFilter.comune? `&idComune=${objFilter.comune}` : "";
    queryString += objFilter.denominazione? `&denominazione=${objFilter.denominazione.valore}` : "";
    queryString += objFilter.dataRilevamentoFrom? `&fromDate=${this.dateToString(objFilter.dataRilevamentoFrom)}` : "";
    queryString += objFilter.dataRilevamentoTo? `&toDate=${this.dateToString(objFilter.dataRilevamentoTo)}` : "";

    return this.http
      .get<Content<Dettaglio[]>>(
        `${this.config.getBERootUrl(
          false
        )}/search/?${queryString}`
      )}

  validateData() {
    return this.http.get<any>(
      this.config.getBERootUrl(false) + `/geoPlPfi/home`
    );
  }

  dateToString(data:Date){
    return data.getFullYear() + '-' + this.getTwoDigits(data.getMonth()+1) + '-' 
    + this.getTwoDigits(data.getDate()); 
  }

  getTwoDigits(value){
    if(value < 10){
      return '0' + value;
    }
    return value;
  }

  getAllDocumenti(idGeoPFA: number) {
    return this.http.get<DocumentoAllegato[]>(
      `${this.config.getBERootUrl(false)}/documenti/${idGeoPFA}`
    );
  }

  getCartograficoByIdUrl(idProfiloGeeco:number,idIdfObject:string):  Observable<any> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}/id?idIdfObject=${idIdfObject!=null?idIdfObject:''}`);
  }

  getCartograficoAllUrl(idProfiloGeeco:number):  Observable<any> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}`);
  }

  getCartograficoPointsUrl(idProfiloGeeco:number,idIdfObjects:string[]):  Observable<any> {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}/list`,idIdfObjects);
  }

  reverseDate(date: string) {
    if(!date){
      return null;
    }
    let splitDate = date.split("-");
    let reverseDate = splitDate.reverse();
    let joinDate = reverseDate.join("/");

    return joinDate;
  }

  getDettaglioById(id: number, obj: any) {
    const queryString = Object.keys(obj)
      .filter((key) => obj[key])
      .map((item) => item + "=" + obj[item])
      .join("&");
    return this.http.get<Detail>(
      `${this.config.getBERootUrl(false)}/search/${id}`
    );
  }

}
