/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Injectable, EventEmitter } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { ConfigService } from "./config.service";
import { Dettaglio, Content, Detail } from "../models/dettaglio";
import { Soggetto } from "../models/soggetto";
import { Observable, from, BehaviorSubject } from "rxjs";
import { Provincia } from "../models/provincia";
import { SelectItem } from "primeng/components/common/selectitem";
import {
  map,
  timeout,
  retryWhen,
  catchError,
  tap,
  filter,
} from "rxjs/operators";
import { handleError, selectiveRetryStrategy } from "src/app/service.utils";
import { Comune, Comuni } from "../models/comune";
import { Documenti } from "../models/documenti";
import { RouteParams } from "../models/routeParams";
import { PfaSearch } from "../models/pfa-search";
import { Interventi } from "../models/interventi";
import { Eventi } from "../models/eventi";
import { ProvinciaModel } from "../models/provincia.model";
import {
  ComuneModel,
  ComuniModel,
  ParticelleCatastali,
} from "../models/comune.model";
import { ParticleCadastral, ShowParcel } from "../models/particle-cadastral";
import { SecondForm } from "../models/secondForm";
import { TipoEventi } from "../models/tipoEventi";
import { EventoCorrelato } from "../models/progressiviNomi";
import { DestLegnami } from "../models/destLegnami";
import { FinalitaTaglie } from "../models/finalTaglie";
import { TipoInterventi } from "../models/tipoInterventi";
import { Governi } from "../models/governi";
import { Esbosco } from "../models/esbosco";
import { UsoViabilita } from "../models/viabilita";
import { FasciaAltimetrica } from "../models/fascia-altimetrica";
import { Specie } from "../models/specie";
import { Priorita } from "../models/priorita";
import { DatiTehnici } from "../models/dati-tehnici";
import { StatoIntervento } from "../models/stato-intervento";
import { SpecieForm } from "../models/specie-form";
import { TipoAllegato } from "../models/tipo-allegato";
import { analyzeAndValidateNgModules } from "@angular/compiler";
import {
  DataTaglio,
  UtilizzatoreDetails,
  PropCatastos,
} from "../models/data-taglio";
import { SoggettoModel } from "../models/soggetto-model";
import {
  TipoInterventoDettaglio,
  PropAndRicandeza,
  SpeciesAndTaglio,
} from "../models/dettaglio-intervento";
import { DocumentoAllegato } from "../models/documento-allegato";
import { InterventoUtilizzatore } from "../models/intervento-utilizzatore";
import { ShootingMirrorModel } from "../models/shooting-mirror";
import { InterventoRiepilogo } from "../models/interventoRiepilogo";
import { StepErrors } from "../components/steps-with-errors/steps-with-errors.component";
@Injectable({
  providedIn: "root",
})
export class PfaSampleService {
  private routeParams = new BehaviorSubject<RouteParams>(null);
  currentRouteParams = this.routeParams.asObservable();

  constructor(private http: HttpClient, private config: ConfigService) {}

  setRouteParams(query: RouteParams) {
    this.routeParams.next(query);
  }

  getConfigationByName(configName: string): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }

  getDettaglio(id: number) {
    return this.http.get<Dettaglio>(
      `${this.config.getBERootUrl(false)}/geoPlPfi/search/${id}`
    );
  }

  getIdPfaByIdIntervento(idIntervento: any) {
    return this.http.get<number>(
      `${this.config.getBERootUrl(false)}/interventi/pfaId/${idIntervento}`
    );
  }

  getIdPfaByIdEvento(idEvento: any) {
    return this.http.get<number>(
      `${this.config.getBERootUrl(false)}/eventi/pfaId/${idEvento}`
    );
  }

  getDettaglioById(id: number, obj: any) {
    const queryString = Object.keys(obj)
      .filter((key) => obj[key])
      .map((item) => item + "=" + obj[item])
      .join("&");

    // const queryString2 = Object.keys(params).filter(key =>
    // );

    console.log(
      `${this.config.getBERootUrl(false)}/test?${
        queryString ? queryString : ""
      }`
    );

    return this.http.get<Detail>(
      `${this.config.getBERootUrl(false)}/geoPlPfi/search/${id}`
    );
  }

  getAllPfa(page: number, limit: number, sortField?: string) {
    let sortQuery: string;
    if (sortField) {
      sortQuery = `&sort=${sortField}`;
    }

    return this.http.get<Content<Dettaglio[]>>(
      this.config.getBERootUrl(false) +
        `/geoPlPfi/search?page=${page}&limit=${limit}${
          sortQuery ? sortQuery : ""
        }`
    );
  }

  getPfaSearch(provinciaId: string, comuneId: number) {
    const comuneString = comuneId !== null ? `&idComune=${comuneId}` : "";
    return this.http
      .get<Dettaglio[]>(
        `${this.config.getBERootUrl(
          false
        )}/geoPlPfi/search/?istatProve=${provinciaId}${comuneString}`
      )
      .pipe(
        map((resp: Dettaglio[]) => {
          const obj: Dettaglio[] = [];
          resp.forEach((item, index) => {
            obj.push(item);
            // obj[
            //   index
            // ].dataApprovazioneString = `${item.dataApprovazione.dayOfMonth}/${item.dataApprovazione.monthValue}/${item.dataApprovazione.year}`;

            // obj[
            //   index
            // ].dataFineValiditaString = `${item.dataFineValidita.dayOfMonth}/${item.dataFineValidita.monthValue}/${item.dataFineValidita.year}`;
          });
          return obj;
        })
      );
  }

  getUserData(userId: number) {
    return this.http.get<Soggetto>(
      `${this.config.getBERootUrl(false)}/soggetti/${userId}`
    );
  }

  getProvincia() {
    return this.http.get<ProvinciaModel[]>(
      this.config.getBERootUrl(false) + `/provincia/search`
    );
  }

  getComuneByProvincia(istatProv) {
    return this.http.get<ComuneModel[]>(
      this.config.getBERootUrl(false) + `/comuni/provincia/${istatProv}`
    );
  }

  getComuneById(idComune: number) {
    return this.http.get<ComuneModel>(
      this.config.getBERootUrl(false) + `/comuni/${idComune}`
    );
  }

  getComuniList() {
    return this.http.get<ComuneModel[]>(
      this.config.getBERootUrl(false) + `/comuni`
    );
  }

  getAllComuni(): Observable<ComuneModel[]> {
    return this.http.get<ComuneModel[]>(
      `${this.config.getBERootUrl(false)}/comuni`
    );
  }
  getComuniForPfaID(pfaID: number): Observable<ComuneModel[]> {
    return this.http.get<ComuneModel[]>(
      `${this.config.getBERootUrl(false)}/comuni/pfa/${pfaID}`
    );
  }

  getCatastiByPfaAndComune(
    pfaID: number,
    comuneId: number
  ): Observable<PropCatastos[]> {
    return this.http.get<PropCatastos[]>(
      `${this.config.getBERootUrl(false)}/catasti/${pfaID}/${comuneId}`
    );
  }

  getStep2(
    comune: string,
    sezione: string,
    foglio: number,
    particella: string
  ) {
    return this.http.get<ParticleCadastral>(
      this.config.getBERootUrl(false) +
        `/sezioni/secondo/particella?istatComune=${comune}&sezione=${sezione}&foglio=${foglio}&particella=${particella}`
    );
  }

  sendList(particelleForm: ParticelleCatastali) {
    return this.http.post(
      `${this.config.getBERootUrl(false)}/sezioni/secondo/elencoParticelle`,
      particelleForm
    );
  }

  insericiElenco(
    istatComune: string,
    sezione: string,
    foglio: number,
    particella: string
  ) {
    return this.http.get(
      `${this.config.getBERootUrl(
        false
      )}/sigmater/particella?istatComune=${istatComune}&sezione=${sezione}&foglio=${foglio}&particella=${particella}`
    );
  }

  creaIntervento(idGeoPlPfa: number) {
    console.log(
      "LIbk para inres new intre",
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idGeoPlPfa}/interventoNew`
    );
    return this.http.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idGeoPlPfa}/interventoNew`
    );
  }

  ricalcolaParticelleFromGeeco(idIntervento: number) {
    return this.http.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/localizzaLottoFromGeeco`
    );
  }

  ricalcolaSuperficiEventoFromGeeco(idEvento) {
    return this.http.put<any>(
      `${this.config.getBERootUrl(
        false
      )}/eventi/${idEvento}/ricalcolaSuperficiFromGeeco`,
      {}
    );
  }

  insericiInElenco(idIntervento: number, particella: ParticleCadastral) {
    return this.http.post<any>(
      `${this.config.getBERootUrl(
        false
      )}/sezioni/${idIntervento}/inserisciParticelle`,
      particella
    );
  }

  deleteParticelleCadastral(idIntervento: number, idGeoPlPropCatasto: number) {
    return this.http.delete(
      `${this.config.getBERootUrl(
        false
      )}/catasti/pfa/${idIntervento}?idGeoPlPropCatasto=${idGeoPlPropCatasto}`
    );
  }
  // catasti/427?idGeoPlPropCatasto=439

  getSecondStepTable(idIntervento: number): Observable<any> {
    return this.http.get<SecondForm>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/secondo`
    );
  }

  // saveStep1(secondStepForm: any, idGeoPlPfa: any): Observable<any> {
  //   return this.http.post<any>(
  //     `${this.config.getBERootUrl(false)}/interventi/pfa/${idGeoPlPfa}/prima`,
  //     secondStepForm
  //   );
  // }

  localizzaLottoSave(
    localizzaLottoForm: any,
    idIntervento: number,
    idGeoPlPfa: number
  ): Observable<any> {
    return this.http.post<any>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/localizzaLottoNEW?idGeoPlPfa=${idGeoPlPfa}`,
      localizzaLottoForm
    );
  }
  localizzaLottoUpdate(
    localizzaLottoForm: any,
    idIntervento: number,
    idGeoPlPfa: number
  ): Observable<any> {
    return this.http.put<any>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/updateLocalizzaLottoNEW?idGeoPlPfa=${idGeoPlPfa}`,
      localizzaLottoForm
    );
  }
  getLocalizzaLottoForEdit(idIntervento: number) {
    return this.http.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/localizzaLottoNEW`
    );
  }

  getSecondTableData(idIntervento: number): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/catasti/pfa/elencoRicadenze?idIntervento=${idIntervento}`
    );
  }

  postGeecoCall(idIntervento: number): Observable<any> {
    return this.http.post<any>(
      `${this.config.getBERootUrl(false)}/catasti/geeco/${idIntervento}`,
      []
    );
  }

  // createIdIntervento(userCompanyData: UserCompanyDataModel) {
  //   return this.httpClient.post<any>(
  //     this.config.getBERootUrl(false) + `/istanze/prima`,
  //     userCompanyData);
  // }
  getAllProvincia(): Observable<SelectItem[]> {
    return this.http
      .get<Provincia[]>(`${this.config.getBERootUrl(false)}/provincia/search`)
      .pipe(
        // timeout(50000),
        // retryWhen(
        //   selectiveRetryStrategy({
        //     scalingDuration: 10,
        //     excludedStatusCodes: [302, 0],
        //     maxRetryAttempts: 2
        //   })
        // ),
        map((response: Provincia[]) => {
          const provinciaDropdown: SelectItem[] = [];
          response.forEach((item) => {
            provinciaDropdown.push({
              label: item.siglaProv,
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

  getAllDocumenti(idGeoPFA: number) {
    return this.http.get<DocumentoAllegato[]>(
      `${this.config.getBERootUrl(false)}/documenti/pfa/${idGeoPFA}`
    );
  }
  getAllDocumentiByIntervento(idIntervento: number) {
    return this.http.get<DocumentoAllegato[]>(
      `${this.config.getBERootUrl(false)}/documenti/interventi/${idIntervento}`
    );
  }

  getInterventi(
    idGeoPFA: number,
    page: number,
    limit: number,
    sortField?: string
  ) {
    let sortQuery: string;
    if (sortField) {
      sortQuery = `&sort=${sortField}`;
    }
    return this.http
      .get<Content<Interventi[]>>(
        `${this.config.getBERootUrl(
          false
        )}/interventi/pfa/${idGeoPFA}?page=${page}&limit=${limit}${
          sortQuery ? sortQuery : ""
        }`
      )
      .pipe(
        map((resp: Content<Interventi[]>) => {
          return resp;
        })
      );
  }

  getEventiById(idEvento: number) {
    return this.http.get<Eventi>(
      `${this.config.getBERootUrl(false)}/eventi/${idEvento}`
    );
  }

  getEventi(idGeoPFA: number, page: number, limit: number, sortField?: string) {
    let sortQuery: string;
    if (sortField) {
      sortQuery = `&sort=${sortField}`;
    }
    return this.http
      .get<Content<Eventi[]>>(
        `${this.config.getBERootUrl(
          false
        )}/eventi/pfa/${idGeoPFA}?&page=${page}&limit=${limit}${
          sortQuery ? sortQuery : ""
        }`
      )
      .pipe(
        map((resp: Content<Eventi[]>) => {
          return resp;
          // const obj: Eventi[] = [];

          // resp.forEach((value, index) => {
          //   obj.push(value);

          //   obj[index].dataEvento = obj[index].dataEvento.replace(/-/g, "/");
          // });
          // return obj;
        })
      );
  }

  deleteEventi(idEventi: number) {
    return this.http.delete(
      `${this.config.getBERootUrl(false)}/eventiWrapper/${idEventi}`
    );
  }

  deleteInterventi(idInterventi: number) {
    return this.http.delete(
      `${this.config.getBERootUrl(false)}/tagli-pfa/${idInterventi}`
    );
  }

  addEvent(formValues: Eventi, idEvento: number) {
    // let form: Eventi;

    // form.progressivoEventoPfa = formValues.progressivoEventoPfa;

    return this.http.post<Eventi>(
      `${this.config.getBERootUrl(
        false
      )}/eventiWrapper/${idEvento}/datiTehnici`,
      formValues
    );
  }

  updateEventiDatiTecnici(
    formValues: Eventi,
    idEvento: number,
    idGeoPFA: number
  ) {
    return this.http.post<Eventi>(
      `${this.config.getBERootUrl(
        false
      )}/eventi/${idEvento}/pfa/${idGeoPFA}/secondo`,
      formValues
    );
  }

  autocompleteFilter(event, res: any, whatFilteringFor) {
    const filtered: any[] = [];
    for (let i = 0; i < res.length; i++) {
      let filtering;
      if (whatFilteringFor === "Provincia") {
        filtering = res[i].denominazioneProv;
      }
      if (whatFilteringFor === "Comune") {
        filtering = res[i].denominazioneComune;
      }
      if (whatFilteringFor === "SezioneComune") {
        filtering = res[i].value;
      }
      if (whatFilteringFor === "FoglioComune") {
        filtering = res[i].value.toString();
      }
      if (whatFilteringFor === "ParticellaComune") {
        filtering = res[i].value;
      }
      if (whatFilteringFor === "TipologiaIstanza") {
        filtering = res[i].descrDettTipoIstanza;
      }
      if (whatFilteringFor === "StatoIstanza") {
        filtering = res[i].descrStatoIstanza;
      }
      if (whatFilteringFor === "FormaGoverno") {
        filtering = res[i].descParametroTrasf;
      }
      if (whatFilteringFor === "CategoriaForestale") {
        filtering = res[i].descParametroTrasf;
      }
      if (whatFilteringFor === "Ubicazione") {
        filtering = res[i].descParametroTrasf;
      }
      if (whatFilteringFor === "TipologiaTrasformazione") {
        filtering = res[i].descParametroTrasf;
      }

      if (whatFilteringFor === "AreeProtete") {
        filtering = res[i].nome;
      }
      if (whatFilteringFor === "Nature2000") {
        filtering = res[i].nome;
      }
      if (whatFilteringFor === "Populamenti") {
        filtering = res[i].nome;
      }
      if (whatFilteringFor === "sezione") {
        filtering = res[i].field;
      }
      if (whatFilteringFor === "foglio") {
        filtering = res[i].field ? res[i].field.toString() : "";
      }
      if (whatFilteringFor === "particella") {
        filtering = res[i].field ? res[i].field.toString() : "";
      }

      if (filtering.toLowerCase().indexOf(event.query.toLowerCase()) === 0) {
        filtered.push(res[i]);
      }
    }
    return filtered;
  }

  formatDate(date) {
    if (date === null || date === "" || date === undefined) {
      return "";
    } else {
      let d = new Date(date),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;
      // console.log("SEARCH TEST", [year, month, day].join("-"));
      return [year, month, day].join("-");
    }
  }

  reverseDate(date: string) {
    if (!date) {
      return null;
    }
    let splitDate = date.split("-");
    let reverseDate = splitDate.reverse();
    let joinDate = reverseDate.join("/");

    return joinDate;
  }

  getTipoEventi() {
    return this.http.get<TipoEventi[]>(
      `${this.config.getBERootUrl(false)}/tipoEventi`
    );
  }

  createEventi(idGeoPFA: number) {
    return this.http.post<any>(
      `${this.config.getBERootUrl(false)}/eventi/pfa/${idGeoPFA}/prima`,
      {}
    );
  }

  getFirstStepEventi(idEvento: number, idGeoPFA: number) {
    return this.http.get(
      `${this.config.getBERootUrl(
        false
      )}/eventi/${idEvento}/pfa/${idGeoPFA}/secondo`
    );
  }

  // api for dropdowns
  getGoverni(): Observable<Governi> {
    return this.http.get<Governi>(`${this.config.getBERootUrl(false)}/governi`);
  }

  getTipoInterventi(): Observable<TipoInterventi> {
    return this.http.get<TipoInterventi>(
      `${this.config.getBERootUrl(false)}/tipoInterventi/3`
    );
  }

  getTipoInterventiByGoverno(idGoverno: number): Observable<TipoInterventi> {
    return this.http.get<TipoInterventi>(
      `${this.config.getBERootUrl(
        false
      )}/tipoInterventi/3/gov?idGoverno=${idGoverno}`
    );
  }

  getFinalitaTaglie(): Observable<FinalitaTaglie> {
    return this.http.get<FinalitaTaglie>(
      `${this.config.getBERootUrl(false)}/finalitaTaglie`
    );
  }

  getDestLegnami(): Observable<DestLegnami> {
    return this.http.get<DestLegnami>(
      `${this.config.getBERootUrl(false)}/destLegnami`
    );
  }

  getEventoCorrelato(idGeoPFA: number): Observable<EventoCorrelato> {
    return this.http.get<EventoCorrelato>(
      `${this.config.getBERootUrl(
        false
      )}/eventi/pfa/${idGeoPFA}/progressiviNomi`
    );
  }

  getAllTipoEsbosco(): Observable<Esbosco> {
    return this.http.get<Esbosco>(`${this.config.getBERootUrl(false)}/esbosco`);
  }
  getAllUsoViabilita(): Observable<UsoViabilita> {
    return this.http.get<UsoViabilita>(
      `${this.config.getBERootUrl(false)}/usoViabilita`
    );
  }

  getAllFasciaAltimetrica(): Observable<FasciaAltimetrica> {
    return this.http.get<FasciaAltimetrica>(
      `${this.config.getBERootUrl(false)}/fasciaAltimetrica`
    );
  }

  getAllSpecie(): Observable<Specie[]> {
    return this.http.get<Specie[]>(
      `${this.config.getBERootUrl(false)}/specie/pfa`
    );
  }

  getAllPriorita(): Observable<Priorita> {
    return this.http.get<Priorita>(
      `${this.config.getBERootUrl(false)}/priorita/pfa`
    );
  }
  getDatiTehnici(idIntervento: number): Observable<DatiTehnici> {
    return this.http.get<DatiTehnici>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/datiTehnici`
    );
  }
  getAllStatoIntervento(): Observable<StatoIntervento> {
    return this.http.get<StatoIntervento>(
      `${this.config.getBERootUrl(false)}/statiInterventi`
    );
  }

  getStatoByIdIntervento(idIntervento): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/statiInterventi/${idIntervento}`
    );
  }

  saveSpecie(specieForm: SpecieForm, idIntervento: number) {
    return this.http.post<SpecieForm>(
      `${this.config.getBERootUrl(
        false
      )}/specie/${idIntervento}/specieIntervento`,
      specieForm
    );
  }

  deleteSpecie(idIntervento: number, idSpecie: number) {
    return this.http.delete(
      `${this.config.getBERootUrl(
        false
      )}/specie/${idSpecie}?idIntervento=${idIntervento}`
    );
  }

  sumSpecieVolume(idIntervento: number) {
    return this.http.get(
      `${this.config.getBERootUrl(false)}/specie/${idIntervento}/volume`
    );
  }
  // postDatiTehnici(datiTehnici: DatiTehnici, idIntervento: number) {
  //   return this.http.post<DatiTehnici>(
  //     `${this.config.getBERootUrl(
  //       false
  //     )}/intervento/${idIntervento}/datiTehnici`,
  //     datiTehnici
  //   );
  // }
  postDatiTehnici(datiTehnici: DatiTehnici, idGeoPlPfa: number) {
    return this.http.post<DatiTehnici>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idGeoPlPfa}/datiTehniciNEW`,
      datiTehnici
    );
  }
  putDatiTehnici(datiTehnici: DatiTehnici, idIntervento: number) {
    return this.http.put<StepErrors[]>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/datiTehniciNEW`,
      datiTehnici
    );
  }
  getDataForShootingMirror(
    idIntervento: number
  ): Observable<ShootingMirrorModel[]> {
    return this.http.get<ShootingMirrorModel[]>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/shootingMirror`
    );
  }
  saveDatiTehnici(idIntervento: number) {
    return this.http.get(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/saveSecondStep`
    );
  }

  getDataTaglio(idIntervento: number): Observable<DataTaglio> {
    return this.http.get<DataTaglio>(
      `${this.config.getBERootUrl(false)}/riepilogo/${idIntervento}`
    );
  }

  allTipoAllegato(): Observable<TipoAllegato> {
    return this.http.get<TipoAllegato>(
      `${this.config.getBERootUrl(false)}/tipoAllegato/pfa`
    );
  }

  allTipoAllegatoIntervento() {
    return this.http.get<TipoAllegato[]>(
      `${this.config.getBERootUrl(false)}/tipoAllegato/pfa`
    );
  }

  getSoggettoFisicoByCodiceFiscale(
    codiceFiscale: string
  ): Observable<SoggettoModel> {
    return this.http.get<SoggettoModel>(
      `${this.config.getBERootUrl(
        false
      )}/soggetti/fisica?codiceFiscale=${codiceFiscale}`
    );
  }

  getSoggettoGiuridicoByCodiceFiscale(
    codiceFiscale: string
  ): Observable<SoggettoModel> {
    return this.http.get<SoggettoModel>(
      `${this.config.getBERootUrl(
        false
      )}/soggetti/giuridica?codiceFiscale=${codiceFiscale}`
    );
  }

  getDataForHeader() {
    return this.http.get<any>(this.config.getBERootUrl(false) + `/soggetti/io`);
  }

  validateData() {
    return this.http.get<any>(
      this.config.getBERootUrl(false) + `/geoPlPfi/home`
    );
  }

  getTipoInterventoDettaglio(
    idIntervento: number
  ): Observable<TipoInterventoDettaglio> {
    return this.http.get<TipoInterventoDettaglio>(
      `${this.config.getBERootUrl(
        false
      )}/dettaglioInterventi/${idIntervento}/tipoInt`
    );
  }
  getPropAndRicandeza(idIntervento: number): Observable<PropAndRicandeza> {
    return this.http.get<PropAndRicandeza>(
      `${this.config.getBERootUrl(
        false
      )}/dettaglioInterventi/${idIntervento}/particelle`
    );
  }
  getSpecieAndTaglio(idIntervento: number): Observable<SpeciesAndTaglio> {
    return this.http.get<SpeciesAndTaglio>(
      `${this.config.getBERootUrl(
        false
      )}/dettaglioInterventi/${idIntervento}/species`
    );
  }
  getUtilizzatoreDettaglio(
    idIntervento: number
  ): Observable<UtilizzatoreDetails> {
    return this.http.get<UtilizzatoreDetails>(
      `${this.config.getBERootUrl(
        false
      )}/dettaglioInterventi/${idIntervento}/utilizzatore`
    );
  }
  uploadPfa(
    file: FormData,
    intervento: number,
    idGeoPlPfa: number,
    tipo: number
  ): Observable<DocumentoAllegato> {
    return this.http.post<DocumentoAllegato>(
      `${this.config.getBERootUrl(
        false
      )}/documenti/uploadPfaV2?intervento=${intervento}&idGeoPlPfa=${idGeoPlPfa}&tipo=${tipo}`,
      file
    );
  }

  updateUploadingPfa(documentoAllegato: DocumentoAllegato) {
    return this.http.put(
      `${this.config.getBERootUrl(false)}/documenti/updateUploadingPfa`,
      documentoAllegato
    );
  }

  deletePfaDocumenti(idDocumentoAllegato: number) {
    return this.http.delete(
      `${this.config.getBERootUrl(
        false
      )}/documenti/deletePfa/${idDocumentoAllegato}`
    );
  }

  downloadPfaDocumenti(idDocumentoAllegato: number) {
    return this.http.get(
      `${this.config.getBERootUrl(
        false
      )}/documenti/getPfaDoc/${idDocumentoAllegato}`,
      { observe: "response", responseType: "blob" }
    );
  }

  getAllTecnicoForestale(): Observable<SoggettoModel> {
    return this.http.get<SoggettoModel>(
      `${this.config.getBERootUrl(false)}/soggetti/tecnicoForestale`
    );
  }
  saveDataForUtilizzatore(
    interventoUtilizzatore: InterventoUtilizzatore,
    idIntervento: number
  ) {
    return this.http.post<StepErrors[]>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/utilizzatore`,
      interventoUtilizzatore
    );
  }
  getDataForUtilizzatore(idIntervento: number) {
    return this.http.get<InterventoUtilizzatore>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/utilizzatoreETecnico`
    );
  }

  isPfaEntePubblico(idIntervento: number) {
    return this.http.get<boolean>(
      `${this.config.getBERootUrl(
        false
      )}/geoPlPfi/isPfaEntePubblico/${idIntervento}`
    );
  }

  saveDataForAllegati(idIntervento: number) {
    return this.http.get(
      `${this.config.getBERootUrl(false)}/intervento/${idIntervento}/allegati`
    );
  }

  trasmettiIntervento(idIntervento: number, obj: any) {
    return this.http.post<any>(
      `${this.config.getBERootUrl(false)}/interventi/${idIntervento}/trasmetti`,
      obj
    );
  }

  getStepNumber(idIntervento: number) {
    return this.http.get(
      `${this.config.getBERootUrl(
        false
      )}/interventi/getStepNumber/${idIntervento}`
    );
  }

  editDatiTehnici(idIntervento: number) {
    return this.http.get(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/datiTehniciNEW`
    );
  }

  getDataForCompleta(idIntervento) {
    return this.http.get<InterventoRiepilogo>(
      `${this.config.getBERootUrl(false)}/interventi/chiusura/${idIntervento}`
    );
  }

  getErroriIntervento(idIntervento: number) {
    return this.http.get<StepErrors[]>(
      `${this.config.getBERootUrl(false)}/intervento/${idIntervento}/errori/pfa`
    );
  }

  salvaIntervento(
    interventoRipeilogo: InterventoRiepilogo,
    idIntervento: number
  ) {
    return this.http.post<InterventoRiepilogo>(
      `${this.config.getBERootUrl(false)}/interventi/salva/${idIntervento}
      `,
      interventoRipeilogo
    );
  }
  completaIntervento(
    interventoRipeilogo: InterventoRiepilogo,
    idIntervento: number
  ) {
    return this.http.post<InterventoRiepilogo>(
      `${this.config.getBERootUrl(false)}/interventi/completa/${idIntervento}
      `,
      interventoRipeilogo
    );
  }
  isNamePresent(name: string, array: DocumentoAllegato[]): boolean {
    let same: boolean = false;
    if (array === undefined) {
      return same;
    } else {
      array.forEach((data) => {
        if (data.nomeAllegato === name) {
          same = true;
        }
      });
      return same;
    }
  }
  isTipoPresent(tipo: number, array: DocumentoAllegato[]): boolean {
    let same: boolean = false;
    if (array === undefined) {
      return same;
    } else {
      array.forEach((data) => {
        if (data.fkTipoAllegato === tipo) {
          same = true;
        }
      });

      return same;
    }
  }

  getCartograficoByIdUrl(
    idProfiloGeeco: number,
    idIdfObject: string
  ): Observable<any> {
    console.log(
      `${this.config.getBERootUrl(
        false
      )}/geeco/${idProfiloGeeco}/id?idIdfObject=${
        idIdfObject != null ? idIdfObject : ""
      }`
    );
    return this.http.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/geeco/${idProfiloGeeco}/id?idIdfObject=${
        idIdfObject != null ? idIdfObject : ""
      }`
    );
  }

  getCartograficoAllUrl(idProfiloGeeco: number): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}`
    );
  }

  getCartograficoPointsUrl(
    idProfiloGeeco: number,
    idIdfObjects: string[]
  ): Observable<any> {
    return this.http.post<any>(
      `${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}/list`,
      idIdfObjects
    );
  }

  getEventiDrawedGeometryInfo(idEvento: number): Observable<any> {
    return this.http.get<any>(
      `${this.config.getBERootUrl(false)}/eventi/${idEvento}/geometries/info`
    );
  }

  getInterventiDrawedGeometryInfo(idIntervento: number): Observable<any> {
    console.log(
      "url ",
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/geometries/info`
    );
    return this.http.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/intervento/${idIntervento}/geometries/info`
    );
  }
}
