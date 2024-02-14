/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from "@angular/common/http";
import { Injectable, EventEmitter } from "@angular/core";
import { from, Observable, of } from "rxjs";
import { map } from "rxjs/operators";

import { ConfigService } from "src/app/shared/services/config.service";
import { environment } from "src/environments/environment";
import { HomeModel } from "src/app/shared/models/home.model";
import { UserChoiceModel } from "src/app/shared/models/user-choice.model";
import { ProvinciaModel } from "src/app/shared/models/provincia.model";
import { ComuneModel } from "src/app/shared/models/comune.model";
import { UserCompanyDataModel } from "src/app/shared/models/user-company-data.model";
import { SoggettoModel } from "src/app/shared/models/soggetto.model";
import { CheckboxAndRadio } from "src/app/shared/models/checkbox-and-radio";
import { ParticleCadastral } from "src/app/shared/models/particle-cadastral";
import { ViewInstance, Content } from "src/app/shared/models/view-instance";
import { Step4Model } from "src/app/shared/models/step4.model";
import { BasedEconomicValueModel } from "src/app/shared/models/based-economic-value.model";
import { StepPosition } from "src/app/shared/models/step-position.model";
import { BaseResponse } from "src/app/shared/models/base-response.model";
import { PlainSecondoSezione } from "src/app/shared/models/plain-secondo-sezione.model";
import {
  PlainSestoSezione,
  DocumentoAllegato,
  IstanzaTaglio,
} from "src/app/shared/models/plain-sesto-sezione.model";
import { DelegaModel } from "src/app/shared/models/delega.model";

@Injectable({
  providedIn: "root",
})
export class ForestaliService {
  polygonEmitter = new EventEmitter<void>();

  utente3 = {
    idSoggetto: 6,
    fkConfigUtente: 1,
    idIntervento: 1,
  };
  user3 = {
    idSoggetto: 5,
    idConfigUtente: 1,
    codiceFiscale: "AAAAAA00A11H000R",
    cognome: "ROSSI",
    nome: "MARIO",
    ruolo: "poljoprivrednik",
    provider: "batica",
    comune: "MILANO",
    indirizzo: "blabla",
    nrTelefonico: "213123dasd",
    civico: "12345",
    cap: "12345",
  };

  tipoIstanzaId = null;
  tipoIstanzaDescr = null;

  typeFiltering = {
    // TipologiaIstanza: 'descrDettTipoIstanza',
    StatoIstanza: ["descrStatoIstanza"],

    Provincia: ["denominazioneProv"],
    Comune: ["denominazioneComune"],
    SezioneComune: ["value"],
    FoglioComune: ["value"],
    ParticellaComune: ["value"],

    RichiedentePF: ["codiceFiscale", "cognome"],
    RichiedentePG: ["partitaIva", "denominazione"],
    Professionista: ["codiceFiscale", "cognome", "idSoggetto"],

    AreeProtete: ["nome"],
    Nature2000: ["nome"],
    Populamenti: ["nome"],

    FormaGoverno: ["descParametroTrasf"],
    CategoriaForestale: ["descParametroTrasf"],
    Ubicazione: ["descParametroTrasf"],
    TipologiaTrasformazione: ["descParametroTrasf"],
  };

  constructor(private httpClient: HttpClient, private config: ConfigService) {}

  getCompanyData(index: number) {
    return this.httpClient.get<HomeModel>(
      `http://localhost:3000/company/${index}`
    );
  }
  getricadenzaInCategorieForestali() {
    // return this.httpClient.get<any>(  `http://localhost:3000/ricadenza-in-categorie-forestali` );
    return {
      codiceAmministrativo: "14",
      nome: "Beseine",
      percentualeRicadenza: event,
    };
  }

  getCartograficoByIdUrl(
    idProfiloGeeco: number,
    idIdfObject: string
  ): Observable<any> {
    console.log(
      "estas pasando por aqui ",
      `${this.config.getBERootUrl(
        false
      )}/geeco/${idProfiloGeeco}/id?idIdfObject=${
        idIdfObject != null ? idIdfObject : ""
      }`
    );
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/geeco/${idProfiloGeeco}/id?idIdfObject=${
        idIdfObject != null ? idIdfObject : ""
      }`
    );
  }

  getCartograficoAllUrl(idProfiloGeeco: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}`
    );
  }
  /*
  getCartograficoPointsUrl(
    idProfiloGeeco: number,
    idIdfObjects: string[]
  ): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}/list`,
      idIdfObjects
    );
  }*/

  getCartograficoPointsUrl(idProfiloGeeco: number, idIdfObjects: string[]) {
    console.log(
      "dento del metodo",
      this.httpClient.post<string>(
        `${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}/list`,
        idIdfObjects
      )
    );
    return this.httpClient.post<string>(
      `${this.config.getBERootUrl(false)}/geeco/${idProfiloGeeco}/list`,
      idIdfObjects
    );
  }

  getAdpfor() {
    return this.httpClient.get<UserChoiceModel>(
      this.config.getBERootUrl(false) + `/idfistafor`
    );
  }

  getAdpforByTipoIstanzaId(tipoIstanzaId: any) {
    console.log(
      this.config.getBERootUrl(false) +
        `/idfistafor?tipoIstanzaId=${tipoIstanzaId}`
    );
    return this.httpClient.get<UserChoiceModel>(
      this.config.getBERootUrl(false) +
        `/idfistafor?tipoIstanzaId=${tipoIstanzaId}`
    );
  }

  getAdpforByIstanzaId(tipoIstanzaId: string) {
    return this.httpClient.get<UserChoiceModel>(
      this.config.getBERootUrl(false) +
        `/idfvincolo?tipoIstanzaId=${tipoIstanzaId}`
    );
  }

  putAdpfor(moduloDatiPersonali: UserChoiceModel) {
    return this.httpClient.put(
      this.config.getBERootUrl(false) + `/idfistafor`,
      moduloDatiPersonali
    );
  }

  getArrayOfInstance(page: number, limit: number, sortField?: string) {
    let sortQuery: string;
    if (sortField) {
      sortQuery = `&sort=${sortField}`;
    }
    return this.httpClient.get<Content<ViewInstance[]>>(
      this.config.getBERootUrl(false) +
        `/istanze?page=${page}&limit=${limit}${sortQuery ? sortQuery : ""}`
    );
  }

  postStep1(userCompanyData: UserCompanyDataModel) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/istanze/prima`,
      userCompanyData
    );
  }
  putStep1(userCompanyData: UserCompanyDataModel, idIntervento) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/prima`,
      userCompanyData
    );
  }
  getStep1(idIntervento: number) {
    return this.httpClient.get<UserCompanyDataModel>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/prima`
    );
  }

  getDropdownCodiceFiscale() {
    return this.httpClient.get<string[]>(
      this.config.getBERootUrl(false) + `/deleghe/mieiCodiciDelegati`
    );
  }

  getMiaListaDeleghe() {
    return this.httpClient.get<DelegaModel[]>(
      this.config.getBERootUrl(false) + `/deleghe/miaListaDeleghe`
    );
  }

  deleteDelegaByCf(codiceFiscale: string) {
    return this.httpClient.delete<number>(
      this.config.getBERootUrl(false) +
        `/deleghe/delete?codiceFiscale=${codiceFiscale}`
    );
  }

  saveDelega(delega: FormData) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/deleghe/save`,
      delega
    );
  }

  downloadDocumentoDelega(uidDocument: string) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/deleghe/download?uuid=${uidDocument}`,
      { observe: "response", responseType: "blob" }
    );
  }

  getfillFormFields(codiceFiscale) {
    return this.httpClient.get<SoggettoModel>(
      this.config.getBERootUrl(false) +
        `/deleghe/mieiDelegati?codiceFiscale=${codiceFiscale}`
    );
  }

  getStep2(
    comune: string,
    sezione: string,
    foglio: number,
    particella: string
  ) {
    return this.httpClient.get<ParticleCadastral>(
      this.config.getBERootUrl(false) +
        `/sezioni/secondo/particella?istatComune=${comune}&sezione=${sezione}&foglio=${foglio}&particella=${particella}`
    );
  }

  emitPolygon() {
    this.polygonEmitter.emit();
  }

  getStep3(idIntervento: number) {
    return this.httpClient.get<CheckboxAndRadio>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/terza`
    );
  }
  postStep3(descriptionOfTheForest: CheckboxAndRadio, idIntervento: number) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/terza`,
      descriptionOfTheForest
    );
  }

  getStep4(idIntervento: number) {
    return this.httpClient.get<Step4Model>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/quarta`
    );
  }

  getStep5(idIntervento: number) {
    return this.httpClient.get<BasedEconomicValueModel>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/quinta`
    );
  }

  postStep5(datiDelProfessionista: SoggettoModel, idIntervento: number) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/quinta`,
      datiDelProfessionista
    );
  }

  getSoggettiIo() {
    return this.httpClient.get<SoggettoModel>(
      this.config.getBERootUrl(false) + "/soggetti/io"
    );
  }

  getSoggettiById(idSoggetti: number): Observable<SoggettoModel> {
    return this.httpClient.get<SoggettoModel>(
      this.config.getBERootUrl(false) + `/soggetti/${idSoggetti}`
    );
  }
  getSoggetoByCodice(codice: string): Observable<SoggettoModel> {
    return this.httpClient.get<SoggettoModel>(
      this.config.getBERootUrl(false) + `/soggetti?codiceFiscale=${codice}`
    );
  }

  getConfigationByName(configName: string): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/configuration/${configName}`
    );
  }

  getTipologiaIstanzeAmbito(idAmbito: number) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/tipiIstanza/attive/${idAmbito}`
    );
  }

  getTipologiaIstanzeAttive() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/tipiIstanza/attive`
    );
  }
  getStep8StatiIstanza() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/statiIstanza`
    );
  }
  getStep8ParametroTrasfGoverno() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/parametriTrasf/governo`
    );
  }
  getStep8ParametriTrasfCategoriaForestale() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/parametriTrasf/categoriaForestale`
    );
  }
  getStep8ParametriTrasfUbicazione() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/parametriTrasf/ubicazione`
    );
  }
  getStep8ParametriTrasfTrasformazione() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/parametriTrasf/trasformazione`
    );
  }
  getStep8CatastiSezioneComune(comune) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/catasti/sezione?comune=${comune}`
    );
  }
  getStep8CatastiFoglioComune(comune, sezione) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/catasti/foglio?comune=${comune}&sezione=${sezione}`
    );
  }
  getStep8CatastiParticellaComune(comune, sezione, foglio) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/catasti/particella?comune=${comune}&sezione=${sezione}&foglio=${foglio}`
    );
  }

  getDatiCatastiSezioneByTipoIstanza(comune, idTipoIstanza) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/catasti/tipoIstanza/sezione?comune=${comune}&idTipoIstanza=${idTipoIstanza}`
    );
  }
  getDatiCatastiFoglioByTipoIstanza(comune, sezione, idTipoIstanza) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/catasti/tipoIstanza/foglio?comune=${comune}&sezione=${sezione}&idTipoIstanza=${idTipoIstanza}`
    );
  }
  getDatiCatastiParticellaByTipoIstanza(
    comune,
    sezione,
    foglio,
    idTipoIstanza
  ) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/catasti/tipoIstanza/particella?comune=${comune}&sezione=${sezione}&foglio=${foglio}&idTipoIstanza=${idTipoIstanza}`
    );
  }

  getPlainParticella(
    comune,
    sezione,
    foglio,
    particella
  ): Observable<ParticleCadastral[]> {
    return this.httpClient.get<ParticleCadastral[]>(
      this.config.getBERootUrl(false) +
        `/catasti/plainParticella?comune=${comune}&sezione=${sezione}&foglio=${foglio}&particella=${particella}`
    );
  }

  deletePlainParticella(idIntervento: number, id: number) {
    return this.httpClient.delete(
      this.config.getBERootUrl(false) +
        `/catasti/${idIntervento}?idGeoPlPropCatasto=${id}`
    );
  }

  deleteIntervento(idIntervento: number) {
    return this.httpClient.delete(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}`
    );
  }

  getStep8RichiedentePF(idTipoIstanza) {
    return this.httpClient
      .get<any>(
        this.config.getBERootUrl(false) +
          `/soggetti/bo/pf?idTipoIstanza=${idTipoIstanza}`
      )
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.cognome} ${element.nome} - ${element.codiceFiscale}`;
          });
          return this.filterDuplicates(result, "codiceFiscale");
        })
      );
  }
  getStep8RichiedentePG(idTipoIstanza) {
    return this.httpClient
      .get<any>(
        this.config.getBERootUrl(false) +
          `/soggetti/bo/pg?idTipoIstanza=${idTipoIstanza}`
      )
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.denominazione} - ${element.codiceFiscale}`;
          });
          return this.filterDuplicates(result, "codiceFiscale");
        })
      );
  }
  getStep8Professionista(idTipoIstanza) {
    return this.httpClient
      .get<any>(
        this.config.getBERootUrl(false) +
          `/soggetti/bo/prof?idTipoIstanza=${idTipoIstanza}`
      )
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.cognome} ${element.nome} - ${element.codiceFiscale}`;
          });
          return this.filterDuplicates(result, "codiceFiscale");
        })
      );
  }

  getStep8RicadenzeAreeProtete() {
    return this.httpClient
      .get<any>(this.config.getBERootUrl(false) + `/ricadenze/areeProtete`)
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.codiceAmministrativo} - ${element.nome} - ${element.percentualeRicadenza}`;
          });
          return this.filterDuplicates(result, "nome");
        })
      );
  }
  getStep8RicadenzeNature2000() {
    return this.httpClient
      .get<any>(this.config.getBERootUrl(false) + `/ricadenze/nature2000`)
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.codiceAmministrativo} - ${element.nome} - ${element.percentualeRicadenza}`;
          });
          return this.filterDuplicates(result, "nome");
        })
      );
  }
  getStep8RicadenzePopulamenti() {
    return this.httpClient
      .get<any>(this.config.getBERootUrl(false) + `/ricadenze/populamenti`)
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.codiceAmministrativo} - ${element.nome} - ${element.percentualeRicadenza}`;
          });
          return this.filterDuplicates(result, "nome");
        })
      );
  }

  getStep8Provincia() {
    return this.httpClient.get<ProvinciaModel[]>(
      this.config.getBERootUrl(false) + `/provincia/bo`
    );
  }
  getStep8ComuneByProvincia(istatProv) {
    return this.httpClient.get<ComuneModel[]>(
      this.config.getBERootUrl(false) + `/comuni/bo/provincia/${istatProv}`
    );
  }

  getPraticaInviata(idIntervento: number) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/inviata`
    );
  }

  getDataInvio(idIntervento: number) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/invio`
    );
  }

  postDataInvio(idIntervento: number, allegato: DocumentoAllegato[]) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/istanze/${idIntervento}/invio`,
      { allegati: allegato },
      { observe: "response" }
    );
  }

  postDataVidInvio(idIntervento: number, allegato: DocumentoAllegato[]) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/invio`,
      { allegati: allegato },
      { observe: "response" }
    );
  }

  getProvincia() {
    return this.httpClient.get<ProvinciaModel[]>(
      this.config.getBERootUrl(false) + `/provincia/search`
    );
  }
  getComuneByProvincia(istatProv) {
    return this.httpClient.get<ComuneModel[]>(
      this.config.getBERootUrl(false) + `/comuni/provincia/${istatProv}`
    );
  }
  getAllComuni() {
    return this.httpClient.get<ComuneModel[]>(
      this.config.getBERootUrl(false) + `/comuni`
    );
  }

  findComuniByNome(comune: String) {
    return this.httpClient.get<ComuneModel[]>(
      this.config.getBERootUrl(false) + `/comuni/find?comune=${comune}`
    );
  }

  getComuniById(idComune) {
    return this.httpClient.get<ComuneModel>(
      this.config.getBERootUrl(false) + `/comuni/${idComune}`
    );
  }

  getProvinciaById(istatProv: string) {
    return this.httpClient.get<ProvinciaModel>(
      this.config.getBERootUrl(false) + `/provincia/${istatProv}`
    );
  }
  autocompleteFilter(event, res: any, whatFilteringFor) {
    const filtered: any[] = [];
    for (let i = 0; i < res.length; i++) {
      let filtering;
      for (let j = 0; j < this.typeFiltering[whatFilteringFor].length; j++) {
        filtering = res[i][this.typeFiltering[whatFilteringFor][j]];
        if (typeof filtering === "number") {
          if (filtering.toString().indexOf(event.query.toString()) === 0) {
            filtered.push(res[i]);
          }
        }
        if (typeof filtering === "string") {
          //  console.log(filtering.toLowerCase().indexOf((event.query).toLowerCase()));
          if (
            filtering.toLowerCase().indexOf(event.query.toLowerCase()) === 0
          ) {
            filtered.push(res[i]);
          }
        }
      }
    }
    if (this.typeFiltering[whatFilteringFor].length > 1) {
      return this.filterDuplicates(filtered, "codiceFiscale");
    }
    return filtered;
  }

  filterDuplicates(array, key) {
    return (array = array.filter(
      (item, index, self) =>
        index === self.findIndex((t) => t[key] === item[key])
    ));
  }

  filterItem(event, res) {
    const filtered: any[] = [];
    for (let i = 0; i < res.length; i++) {
      if (res[i].name.toLowerCase().indexOf(event.query.toLowerCase()) === 0) {
        filtered.push(res[i].name);
      }
    }
    return filtered;
  }

  ricerca(search) {
    // console.log(this.config.getBERootUrl(false) +
    // `/istanze/bo/search?${search}`);

    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/istanze/bo/search?${search}`
    );
  }

  //////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////

  /* getDataForRsuInPuglia(anno: number): Observable<RsuInPugliaData> {
    return combineLatest(
      this.getSchedaTeritorie(anno),
      this.getSchedaTeritorieMese(anno),
      this.getFlusoMensileRaccolta(anno)
    ).pipe(
      map(([schedaTeritorie, dataSchedaTeritorie, flusoMensileRaccolta]) => {
        return { schedaTeritorie, dataSchedaTeritorie, flusoMensileRaccolta }
      })
    )
  } */

  sendFourthForm(fourthForm: Step4Model, idIntervento: number) {
    return this.httpClient.put<any>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/quarta`,
      fourthForm
    );
  }

  getStepNumber(editMode?: number): Observable<StepPosition> {
    return this.httpClient.get<StepPosition>(
      `${this.config.getBERootUrl(false)}/istanze/${editMode}`
    );
    // const position: StepPosition = {nextStep: 2};
    // return of(position);
  }

  getValoreEconomicoReale(idIntervento: number) {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/xxxx/${idIntervento}`
    );
  }

  testErrorHandling(randomNum: number) {
    let baseObj: Step4Model = {};
    if (randomNum === 1) {
      return this.httpClient
        .get<BaseResponse<Step4Model>>(
          `${this.config.getBERootUrl(false)}/test/success`
        )
        .pipe(
          map((response: BaseResponse<Step4Model>) => {
            return (baseObj = response.payload);
          })
        );
    } else {
      return this.httpClient
        .get<BaseResponse<Step4Model>>(
          `${this.config.getBERootUrl(false)}/test/error/validation`
        )
        .pipe(
          map((response: BaseResponse<Step4Model>) => {
            return (baseObj = response.payload);
          })
        );
    }
  }

  sendSecondStep(
    secondStepForm: PlainSecondoSezione,
    idIntervento: number
  ): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/secondo `,
      secondStepForm
    );
  }

  getSecondStepTable(idIntervento: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/secondo`
    );
  }

  getSecondStepRicadenze(idIntervento: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/sezioni/secondo/elencoRicadenze?idIntervento=${idIntervento}`
    );
  }

  insertGeometryFromSingleParticle(
    idIntervento: number,
    data: any
  ): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(
        false
      )}/sezioni/${idIntervento}/inserisciParticelle`,
      data
    );
  }

  insertGeometryFromSingleParticella(
    idIntervento: number,
    data: any
  ): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(
        false
      )}/sezioni/${idIntervento}/inserisciParticella`,
      data
    );
  }

  saveStep2(
    secondStepForm: any,
    isTableEmpty: boolean,
    idIntervento: number
  ): Observable<any> {
    return isTableEmpty
      ? this.httpClient.post<any>(
          `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/secondo`,
          secondStepForm
        )
      : this.httpClient.put<any>(
          `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/secondo`,
          secondStepForm
        );
  }

  getStep6(idIntervento: number): Observable<PlainSestoSezione> {
    return this.httpClient.get<PlainSestoSezione>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/sesto`
    );
  }

  getAllDocuments(idIntervento: number): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/elencaDich`
    );
  }

  getDocGestore(idIntervento: number): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(
      `${this.config.getBERootUrl(
        false
      )}/istanze/${idIntervento}/elencoDocsGestore`
    );
  }

  uploadFileStep6(
    file: FormData,
    idIntervento: number,
    tipo: number
  ): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(
        false
      )}/documenti/upload?intervento=${idIntervento}&tipo=${tipo}`,
      file
    );
  }

  searchIstanzaTaglio(numIstanza: number): Observable<IstanzaTaglio> {
    return this.httpClient.get<IstanzaTaglio>(
      `${this.config.getBERootUrl(
        false
      )}/sezioni/sesto/istanzaTaglio?numIstanza=${numIstanza}`
    );
  }

  saveStep6(idIntervento: number, ste6Data: any): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(false)}/istanze/${idIntervento}/sesto`,
      ste6Data
    );
  }

  downloadStep6(idDocumento: number) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(
        false
      )}/documenti/getDoc?idDocumento=${idDocumento}`,
      { observe: "response", responseType: "blob" }
    );
  }

  downloadDichiarazione(idIntervento: number, tipoAllegato: number) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(
        false
      )}/documenti/dichiarazione/${idIntervento}/${tipoAllegato}`,
      { observe: "response", responseType: "blob" }
    );
  }

  downloadDichiarazioneXdoc(idIntervento: number, tipoAllegato: string) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(
        false
      )}/documenti/dichiarazione/xdoc/${idIntervento}/${tipoAllegato}`,
      { observe: "response", responseType: "blob" }
    );
  }

  downloadExcel(search: string) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/istanze/bo/excel?${search}`,
      { observe: "response", responseType: "blob" }
    );
  }

  recalculateParticelle(idIntervento: number) {
    return this.httpClient.get<PlainSestoSezione>(
      `${this.config.getBERootUrl(
        false
      )}/istanze/recalculate/particelle/${idIntervento}`
    );
  }

  deleteDocumentoAllegato(idDocumentoAllegato: number) {
    return this.httpClient.delete(
      `${this.config.getBERootUrl(
        false
      )}/documenti/delete/${idDocumentoAllegato}`
    );
  }

  formatDate(date) {
    if (date === null || date === "" || date === undefined) {
      return "";
    } else {
      var d = new Date(date),
        month = "" + (d.getMonth() + 1),
        day = "" + d.getDate(),
        year = d.getFullYear();

      if (month.length < 2) month = "0" + month;
      if (day.length < 2) day = "0" + day;
      // console.log("SEARCH TEST", [year, month, day].join("-"));
      return [year, month, day].join("-");
    }
  }
  clearSearchStorage() {
    sessionStorage.removeItem("searchResults");
    sessionStorage.removeItem("paramsString");
    sessionStorage.removeItem("searchForm");
  }

  getUtenteForIstanza(idIntervento: number) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/istanze/bo/${idIntervento}/ceo`,
      { observe: "response" }
    );
  }

  associareDocumentiBo(idIntervento: number, allegato: DocumentoAllegato[]) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/istanze/bo/${idIntervento}/asociare`,
      { allegati: allegato },
      { observe: "response" }
    );
  }

  verificataIstanza(idIntervento: number) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/istanze/bo/${idIntervento}/verificata`,
      { observe: "response" }
    );
  }

  rifiutataIstanza(idIntervento: number, motivazioneRifiuto: string) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/istanze/bo/${idIntervento}/rifiutata?motivazioneRifiuto=${motivazioneRifiuto}`,
      { observe: "response" }
    );
  }

  getHomeData() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/idfistafor/home`
    );
  }

  isUtenteAlreadyUpdate() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/utenti/isUpdate`
    );
  }

  logout() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/utenti/logout`
    );
  }

  createSoggetti(soggetto: SoggettoModel) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/idfistafor`,
      soggetto,
      { observe: "response" }
    );
  }

  getMyAziende(codiceFiscale: string) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/soggetti/myGiuridica?codiceFiscale=${codiceFiscale}`,
      { observe: "response" }
    );
  }

  getMyAziendePubblichePrivate(codiceFiscale: string, isPubblico: boolean) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/soggetti/myGiuridicaPubblicaPrivata?codiceFiscale=${codiceFiscale}&isPubblico=${isPubblico}`,
      { observe: "response" }
    );
  }

  getAzienda(codiceFiscale: string) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/soggetti/giuridica?codiceFiscale=${codiceFiscale}`,
      { observe: "response" }
    );
  }

  searchAllProfessionista() {
    return this.httpClient
      .get<any>(this.config.getBERootUrl(false) + `/soggetti/pf`)
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element["field"] = `${element.codiceFiscale}`;
          });
          return this.filterDuplicates(result, "codiceFiscale");
        })
      );
  }

  isNamePresent(name: string, array: DocumentoAllegato[]): boolean {
    let same: boolean = false;
    array.forEach((data) => {
      if (data.nomeAllegato === name) {
        same = true;
      }
    });
    return same;
  }
}
