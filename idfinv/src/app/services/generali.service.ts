/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { ConfigService } from "../shared/services/config.service";
import { Proprieta } from "../shared/models/proprieta";
import { map } from "rxjs/operators";
import { SelectItem } from "primeng/components/common/selectitem";
import { Observable } from "rxjs";
import { Esposizione } from "../shared/models/esposizione";
import { TipoStrutturale } from "../shared/models/tipo-strutturale";
import { StadioSviluppo } from "../shared/models/stadio-sviluppo";
import { AreaDiSaggioSave } from "../shared/models/area-di-saggio-save";
import { AreaDiSaggio } from "../shared/models/area-di-saggio";

@Injectable({
  providedIn: "root"
})
export class GeneraliService {
  constructor(private http: HttpClient, private config: ConfigService) {}

  getProprieta(): Observable<SelectItem[]> {
    return this.http
      .get<Proprieta[]>(`${this.config.getBERootUrl(false)}/proprieta/inventari`)
      .pipe(
        map((response: Proprieta[]) => {
          const proprietaList: SelectItem[] = [];
          response.forEach(item => {
            proprietaList.push({
              label: item.codProprieta + ' - ' + item.descrProprieta,
              value: item.idProprieta
            });
          });
          return proprietaList;
        })
      );
  }

  getComuneProvinciaByCoords(x:number,y:number):  Observable<SelectItem[]> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/areaDiSaggio/comuneProvincia/${x}/${y}`);
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

  getEsposizione(): Observable<SelectItem[]> {
    return this.http
      .get<Esposizione[]>(`${this.config.getBERootUrl(false)}/esposizione`)
      .pipe(
        map((response: Esposizione[]) => {
          const esposizioneList: SelectItem[] = [];
          response.forEach(item => {
            if(item.codEsposizione !='A'){
              esposizioneList.push({
                value: item.idEsposizione,
                label: item.descrEsposizione
              });
            }
          });
          return esposizioneList;
        })
      );
  }
  getDatiGenerali(idgeoPtAds: number): Observable<AreaDiSaggio> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/datiGenerali/${idgeoPtAds}`);
  }
  saveDatiGenerali(datiGeneraliForm: AreaDiSaggioSave): Observable<any> {
    if (datiGeneraliForm.idgeoPtAds) {
      return this.http.put<any>(`${this.config.getBERootUrl(false)}/areaDiSaggio`, datiGeneraliForm);
    } else {
      return this.http.post<any>(`${this.config.getBERootUrl(false)}/areaDiSaggio`, datiGeneraliForm);
    }
  }

  createEmptyAreaDiSaggio(idTipoAds: string,codiceADS:string): Observable<any> {
      let datiGeneraliForm: AreaDiSaggioSave={};
      datiGeneraliForm.tipologiaDiRilievo = idTipoAds;
      datiGeneraliForm.codiceADS = codiceADS;
      return this.http.post<any>(`${this.config.getBERootUrl(false)}/areaDiSaggio/insert/empty`, datiGeneraliForm);
  }

  deleteAreaDiSaggio(idgeoPtAds: number): Observable<any> {
    if (idgeoPtAds){
      return this.http.delete(`${this.config.getBERootUrl(false)}/areaDiSaggio/${idgeoPtAds}`);
    }
  }
  
}
