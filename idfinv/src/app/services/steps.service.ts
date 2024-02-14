/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from '../shared/services/config.service';
import { SelectItem } from 'primeng/components/common/selectitem';
import { StadioSviluppo } from '../shared/models/stadio-sviluppo';
import { map } from 'rxjs/operators';
import { Observable, of, BehaviorSubject, Subject } from 'rxjs';
import { TipoStrutturale } from '../shared/models/tipo-strutturale';
import { Destinazione } from '../shared/models/destinazione';
import { TipoIntervento } from '../shared/models/tipo-intervento';
import { Esbosco } from '../shared/models/esbosco';
import { TipoForestale } from '../shared/models/tipo-forestale';
import { Assetto } from '../shared/models/asseto';
import { AreaDiSaggio } from '../shared/models/area-di-saggio';
import { Danno } from '../shared/models/danno';
import { PrioritaIntervento } from '../shared/models/priorita';
import { Specie } from '../shared/models/specie';
import { AlberiCampioneDominante } from '../shared/models/alberi-campione-dominante';
import { ClasseFertilita } from '../shared/models/classe-di-fertilita';

@Injectable({
  providedIn: 'root'
})
export class StepsService {

  constructor(private http: HttpClient, private config: ConfigService) { }

  dettaglioForm = new Subject<any>();
  dettaglioStep = new Subject<void>();

  getTipoStrutturale(): Observable<SelectItem[]> {
    return this.http
      .get<TipoStrutturale[]>(`${this.config.getBERootUrl(false)}/tipoStrutturale/inventari`)
      .pipe(
        map((response: TipoStrutturale[]) => {
          const tipoStrutturaleList: SelectItem[] = [];
          response.forEach(item => {
            tipoStrutturaleList.push({
              label: item.codTipoStrutturale +' - ' + item.descrTipoStrutturale,
              value: item.idTipoStrutturale
            });
          });
          return tipoStrutturaleList;
        })
      );
  }

  getStadioSviluppo(): Observable<SelectItem[]> {
    return this.http
      .get<StadioSviluppo[]>(`${this.config.getBERootUrl(false)}/stadioSviluppo`)
      .pipe(
        map((response: StadioSviluppo[]) => {
          const stadioSviluppoList: SelectItem[] = [];
          response.forEach(item => {
            stadioSviluppoList.push({
              value: item.codStadioSviluppo,
              label: item.codStadioSviluppo + ' - ' + item.descrStadioSviluppo
            });
          });
          return stadioSviluppoList;
        })
      );
  }

  getDestinazione(): Observable<SelectItem[]> {
    return this.http
      .get<Destinazione[]>(`${this.config.getBERootUrl(false)}/destinazione/inventari`)
      .pipe(
        map((response: Destinazione[]) => {
          const destinazioneList: SelectItem[] = [];
          response.forEach(item => {
            destinazioneList.push({
              value: item.idDestinazione,
              label: item.descrDestinazione
            });
          });
          return destinazioneList;
        })
      );
  }

  getTipoIntervento(): Observable<SelectItem[]> {
    return this.http
      .get<TipoIntervento[]>(`${this.config.getBERootUrl(false)}/tipoInterventi/4`)
      .pipe(
        map((response: TipoIntervento[]) => {
          const tipoInterventiList: SelectItem[] = [];
          response.forEach(item => {
            tipoInterventiList.push({
              value: item.idTipoIntervento,
              label: item.descrTipoIntervento
            });
          });
          return tipoInterventiList;
        })
      );
  }

  getEsbosco(): Observable<SelectItem[]> {
    return this.http
      .get<Esbosco[]>(`${this.config.getBERootUrl(false)}/esbosco`)
      .pipe(
        map((response: Esbosco[]) => {
          const esboscoList: SelectItem[] = [];
          response.forEach(item => {
            esboscoList.push({
              value: item.codEsbosco,
              label: item.descrEsbosco
            });
          });
          return esboscoList;
        })
      );
  }

  getTipoForestaleByCategoria(categoria: string): Observable<SelectItem[]> {
    return this.http
      .get<TipoForestale[]>(`${this.config.getBERootUrl(false)}/tipoForestale/inventari/categoria/${categoria}`)
      .pipe(
        map((response: TipoForestale[]) => {
          const tipoForestaleList: SelectItem[] = [];
          response.forEach(item => {
            tipoForestaleList.push({
              label:  item.codiceAmministrativo + ' - ' + item.tipo + ' - ' + item.sottotipo
                      + ' - ' + item.variante, 
              value: item.idTipoForestale
            });
          });
          return tipoForestaleList;
        })
      );
  }

  getAsseto(): Observable<SelectItem[]> {
    return this.http
      .get<Assetto[]>(`${this.config.getBERootUrl(false)}/assetto`)
      .pipe(
        map((response: Assetto[]) => {
          const assettoList: SelectItem[] = [];
          response.forEach(item => {
            assettoList.push({
              label: item.codAssetto + ' - ' + item.descAssetto,
              value: item.idAssetto
            });
          });
          return assettoList;
        })
      );
  }

  getClasseDiFertilita(): Observable<SelectItem[]> {
    return this.http
      .get<ClasseFertilita[]>(`${this.config.getBERootUrl(false)}/classeFertilita`)
      .pipe(
        map((response: ClasseFertilita[]) => {
          const classeFertilitaList: SelectItem[] = [];
          response.forEach(item => {
            classeFertilitaList.push({
              label: item.descrClasseFertilita,
              value: item.idClasseFertilita
            });
          });
          return classeFertilitaList;
        })
      );
  }

  getDannoPrevalente(): Observable<SelectItem[]> {
    return this.http
      .get<Assetto[]>(`${this.config.getBERootUrl(false)}/danno`)
      .pipe(
        map((response: Danno[]) => {
          const dannoList: SelectItem[] = [];
          response.forEach(item => {
            dannoList.push({
              label: item.descrDanno,
              value: item.idDanno
            });
          });
          return dannoList;
        })
      );
  }

  sendDatiStazionaliFirst(areaDiSaggio: AreaDiSaggio) {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/areaDiSaggio/datiStazionali1/create`, areaDiSaggio);
  }

  getAreaDiSaggio(idgeoPtAds: number): Observable<AreaDiSaggio> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/datiStazionali1/${idgeoPtAds}`);
  }

  getDatiStazionaliSecond(idgeoPtAds: number): Observable<AreaDiSaggio> {
    return this.http.get<AreaDiSaggio>(`${this.config.getBERootUrl(false)}/areaDiSaggio/datiStazionali2/${idgeoPtAds}`);
  }

  sendDatiStazionaliSecond(areaDiSaggio: AreaDiSaggio) {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/areaDiSaggio/datiStazionali2/create`, areaDiSaggio);
  }

  getPrioritaIntervento() {
    return this.http
      .get<any[]>(`${this.config.getBERootUrl(false)}/priorita/inventari`)
      .pipe(
        map((response: PrioritaIntervento[]) => {
          const prioritaList: SelectItem[] = [];
          response.forEach(item => {
            prioritaList.push({
              label: item.descrPriorita,
              value: item.idPriorita
            });
          });
          return prioritaList;
        })
      );
  }

  getSpecie(idSpecie?: boolean): Observable<SelectItem[]> {
    return this.http.get<Specie[]>(`${this.config.getBERootUrl(false)}/specie/inventari`)
    .pipe(
      map(
        (response: Specie[]) => {
          const specieDropdown: SelectItem[] = [];
            response.forEach((item, i) => {
              specieDropdown.push({
                label: `${item.codice} - ${item.volgare}`,
                value: idSpecie ? item.idSpecie : item.codice
              });
            });
          return specieDropdown;
        }
      )
    );
  }

  getSpecieAll(idSpecie?: boolean): Observable<Specie[]> {
    return this.http.get<Specie[]>(`${this.config.getBERootUrl(false)}/specie/inventari`)
  }

  getTipoCampione(): Observable<any[]> {
    return this.http.get<any[]>(`${this.config.getBERootUrl(false)}/tipoCampione`);
  }

  getAlberiCampioneStep(idgeoPtAds: number): Observable<any> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCAMeDOM/${idgeoPtAds}`);
  }

  sendAlberiCampioneStep(campioneForm: AlberiCampioneDominante[],newPlan: boolean): Observable<any> {
    return newPlan ? 
      this.http.post<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCAMeDOM`, campioneForm) :
      this.http.put<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCAMeDOM`, campioneForm);
  }

  getDettaglioStepData(idgeoPtAds: number): Observable<any> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCAV/${idgeoPtAds}`);
  }

  getRelascopica(idgeoPtAds: number): Observable<any> {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/relascopica/${idgeoPtAds}`);
  }
  
  postRelascopica(dettaglioForm: any): Observable<any> {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/relascopica`, dettaglioForm);
  }

  consolidaRelascopica(dettaglioForm: any): Observable<any> {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/relascopica/consolida`, dettaglioForm);
  }

  emitDettaglioForm(dettaglioForm: any) {
    this.dettaglioForm.next(dettaglioForm);
  }

  emitDettaglioStep() {
    this.dettaglioStep.next();
  }

  sendAlberiCavallettatiStep(cavalettatiForm: any) {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCav`, cavalettatiForm);
  }

  consolidateAlberiCavallettatiStep(cavalettatiForm: any) {
    return this.http.post<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCav/consolida`, cavalettatiForm);
  }

  getGruppo() {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/specie/gruppo`);
  }
  
  getAlberiCavallettatiStep(idgeoPtAds: number) {
    return this.http.get<any>(`${this.config.getBERootUrl(false)}/adsrelSpecie/alberiCAV/${idgeoPtAds}`);
  }
}
