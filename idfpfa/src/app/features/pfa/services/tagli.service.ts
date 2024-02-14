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
import { CategoriaSelvicolturale } from "./../../../shared/models/categoria-selvicolturale.model";
import { Proprieta } from "../../../shared/models/proprieta.model";
import { Trasformazione } from "src/app/shared/models/trasformazione.model";
import { TagliStep1 } from "../components/stepper-tagli/step1-tagli/tagli-step1.model";
import { StepPosition } from "src/app/shared/models/step-position.model";
import { Content } from "src/app/shared/models/view-instance";
import { ViewInstance } from "./../../../shared/models/view-instance";
import { TipoRichiedente } from "./../../../shared/models/tipo-richiedente.model";
import { TagliStep2 } from "./../components/stepper-tagli/step2-tagli/tagli-step2.model";
import { TagliStep3 } from "../components/stepper-tagli/step3-tagli/tagli-step3.model";
import { CheckboxAndRadio } from "src/app/shared/models/checkbox-and-radio";
import { FasciaAltimetrica } from "./../../../shared/models/fascia-altimetrica.model";
import { Governo } from "src/app/shared/models/governo.model";
import { TagliStep4 } from "../components/stepper-tagli/step4-tagli/tagli-step4.model";
import { TipoIntervento } from "src/app/shared/models/tipo-intervento";
import { TipoEventi } from "src/app/shared/models/tipo-eventi";
import { TagliStep5 } from "../components/stepper-tagli/step5-tagli/tagli-step5.model";
import { Esbosco } from "src/app/shared/models/esbosco.model";
import { UsoViabilita } from "src/app/shared/models/uso-viabilita.model";
import { TipoViabilita } from "src/app/shared/models/tipo-viabilita.model";
import { Specie } from "src/app/shared/models/specie.model";
import { FinalitaTaglio } from "src/app/shared/models/finalita-taglio.model";
import { DestinazioneLegname } from "src/app/shared/models/destinazione-legname.model";
import { CategoriaForestale } from "src/app/shared/models/categoria-forestale.model";
import { SoggettoModel } from "src/app/shared/models/soggetto.model";
import { FatSoggetto } from "src/app/shared/models/fat-soggetto.model";
import { TipoAllegato } from "src/app/shared/models/tipo-allegato";
import { DocumentoAllegato } from "src/app/shared/models/plain-sesto-sezione.model";
import { StatoIntervento } from "src/app/shared/models/stato-intervento.model";
import { InfoDocsMancanti } from "./../../../shared/models/info-docs-mancanti.model";
import { InfoVarianteProrogaModel } from "src/app/shared/models/info-variante-proroga.model";
import { AziendaTAIF } from "./../../../shared/models/azienda-taif.model";
import { RicadenzaIntervento } from "src/app/shared/models/ricadenzaIntervento.model";
import { Eventi } from "src/app/shared/models/eventi";

@Injectable({
  providedIn: "root",
})
export class TagliService {
  constructor(private httpClient: HttpClient, private config: ConfigService) {}

  getCategorieSelvicolturali(): Observable<CategoriaSelvicolturale[]> {
    return this.httpClient.get<CategoriaSelvicolturale[]>(
      `${this.config.getBERootUrl(false)}/categorie-selvicolturali`
    );
  }

  getProprietaSelvicolturali(): Observable<Proprieta[]> {
    return this.httpClient.get<Proprieta[]>(
      `${this.config.getBERootUrl(false)}/proprieta/primpa`
    );
  }

  searchTrasformazioni(search: any): Observable<Trasformazione[]> {
    return this.httpClient.get<Trasformazione[]>(
      `${this.config.getBERootUrl(
        false
      )}/trasformazioni/search-base?search=${search}`
    );
  }

  searchSoggettiByCForName(search: any): Observable<FatSoggetto[]> {
    return this.httpClient.get<FatSoggetto[]>(
      `${this.config.getBERootUrl(
        false
      )}/soggetti/search-pf-by-param?search=${search}`
    );
  }

  searchAziendeByCForDenom(
    search: any,
    entePubblico: boolean
  ): Observable<FatSoggetto[]> {
    return this.httpClient.get<FatSoggetto[]>(
      `${this.config.getBERootUrl(
        false
      )}/soggetti/search-pg-by-param?search=${search}&entePubblico=${entePubblico}`
    );
  }

  getArrayOfInstance(page: number, limit: number, sortField?: string) {
    let sortQuery: string;
    let tipoIstanzaId = sessionStorage.getItem("tipoIstanzaId");
    if (sortField) sortQuery = `&sort=${sortField}`;
    return this.httpClient.get<Content<ViewInstance[]>>(
      this.config.getBERootUrl(false) +
        `/tagli-selvicolturali/istanze?tipoIstanzaId=${tipoIstanzaId}&page=${page}&limit=${limit}${
          sortQuery ? sortQuery : ""
        }`
    );
  }

  getTipiRichiedente(): Observable<TipoRichiedente[]> {
    return this.httpClient.get<TipoRichiedente[]>(
      `${this.config.getBERootUrl(false)}/tagli-selvicolturali/tipi-richiedente`
    );
  }

  getUtilizzatoriPF(search: any) {
    return this.httpClient
      .get<any>(
        `${this.config.getBERootUrl(
          false
        )}/soggetti/bo/utlizzatore/pf?q=${search}`
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

  getUtilizzatoriPG(search: any) {
    return this.httpClient
      .get<any>(
        `${this.config.getBERootUrl(
          false
        )}/soggetti/bo/utlizzatore/pg?q=${search}`
      )
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element["field"] = `${element.denominazione} - ${
              element.partitaIva || element.codiceFiscale || "n.d"
            }`;
          });
          return this.filterDuplicates(result, "codiceFiscale");
        })
      );
  }

  getTecniciForestali(search: any) {
    return this.httpClient
      .get<any>(
        `${this.config.getBERootUrl(
          false
        )}/soggetti/bo/utlizzatore/prof?q=${search}`
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

  getSoggettiGestori() {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/soggetti/bo/gestori/list`
    );
  }

  getSoggettiSportello() {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/soggetti/bo/sportello/list`
    );
  }

  getTipoEventi() {
    return this.httpClient.get<TipoEventi[]>(
      `${this.config.getBERootUrl(false)}/tipoEventi/`
    );
  }

  getEventiByPFA(id:number) {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/eventi/pfa/${id}`
    );
  }

  getTipoInterventoConformeDeroga() {
    return this.httpClient.get<TipoEventi[]>(
      `${this.config.getBERootUrl(
        false
      )}/tipoInterventi/tipoInterventoConformeDeroga/`
    );
  }

  filterDuplicates(array, key) {
    return (array = array.filter(
      (item, index, self) =>
        index === self.findIndex((t) => t[key] === item[key])
    ));
  }

  getStepNumber(editMode?: number): Observable<StepPosition> {
    return this.httpClient.get<StepPosition>(
      `${this.config.getBERootUrl(false)}/tagli-pfa/istanze/${editMode}`
    );
  }

  // step1
  getStep1(idIntervento: number) {
    return this.httpClient.get<TagliStep1>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step1`
    );
  }
  postStep1(data: TagliStep1) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/step1`,
      data
    );
  }
  putStep1(data: TagliStep1, idIntervento) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step1`,
      data
    );
  }

  // step2
  getStep2(idIntervento: number) {
    return this.httpClient.get<TagliStep2>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step2`
    );
  }
  postStep2(data: TagliStep2) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/step2`,
      data
    );
  }
  putStep2(data: TagliStep2, idIntervento) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step2`,
      data
    );
  }

  getAzienda(codiceFiscale: string, isPubblic: boolean) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/soggetti/giuridica/pubblic?codiceFiscale=${codiceFiscale}&isPubblic=${isPubblic}`,
      { observe: "response" }
    );
  }

  // step3
  getStep3(idIntervento: number) {
    return this.httpClient.get<TagliStep3>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step3`
    );
  }

  postStep3(data: TagliStep3, isTableEmpty: boolean, idIntervento: number) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step3`,
      data
    );
  }
  putStep3(data: TagliStep3, idIntervento) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step3`,
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
      )}/tagli-pfa/${idIntervento}/inserisciParticella`,
      data
    );
  }

  recalculateParticelle(idIntervento: number) {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/tagli-pfa/${idIntervento}/recalculate_particelle`
    );
  }

  getRicadenze(idIntervento: number): Observable<any> {
    return this.httpClient.get<TagliStep3>(
      `${this.config.getBERootUrl(
        false
      )}/tagli-pfa/${idIntervento}/step3/elencoRicadenze`
    );
  }

  // metodo da customizzare
  postRicadenzeIntervento(
    data: RicadenzaIntervento
  ): Observable<RicadenzaIntervento[]> {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/`,
      data
    );
  }

  getFasceAltimetriche(): Observable<FasciaAltimetrica[]> {
    return this.httpClient.get<FasciaAltimetrica[]>(
      `${this.config.getBERootUrl(false)}/fasciaAltimetrica`
    );
  }

  getGoverni(): Observable<Governo[]> {
    return this.httpClient.get<Governo[]>(
      `${this.config.getBERootUrl(false)}/governi`
    );
  }

  getAllEsbosco(): Observable<Esbosco[]> {
    return this.httpClient.get<Esbosco[]>(
      `${this.config.getBERootUrl(false)}/esbosco`
    );
  }

  getAllUsoViabilita(): Observable<UsoViabilita[]> {
    return this.httpClient.get<UsoViabilita[]>(
      `${this.config.getBERootUrl(false)}/usoViabilita`
    );
  }

  getAllTipoViabilita(fkConfigIpla?: number): Observable<TipoViabilita[]> {
    return this.httpClient.get<TipoViabilita[]>(
      `${this.config.getBERootUrl(false)}/tipoViabilita/ipla/${fkConfigIpla}`
    );
  }

  getAllSpecie(): Observable<Specie[]> {
    return this.httpClient.get<Specie[]>(
      `${this.config.getBERootUrl(false)}/specie/pfa`
    );
  }

  getAllFinalitaTaglio(): Observable<FinalitaTaglio[]> {
    return this.httpClient.get<FinalitaTaglio[]>(
      `${this.config.getBERootUrl(false)}/finalitaTaglie`
    );
  }

  getAllDestinazioneLegname(): Observable<DestinazioneLegname[]> {
    return this.httpClient.get<DestinazioneLegname[]>(
      `${this.config.getBERootUrl(false)}/destLegnami`
    );
  }

  deleteIntervento(idIntervento: number) {
    return this.httpClient.delete(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}`
    );
  }

  // step 4
  getStep4(idIntervento: number) {
    return this.httpClient.get<TagliStep4>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step4`
    );
  }

  postStep4(data: TagliStep4, idIntervento: number) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step4`,
      data
    );
  }
  putStep4(data: TagliStep4, idIntervento) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step4`,
      data
    );
  }

  getTipiIntervento(fkConfigIpla?: number): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/tipoInterventi/${fkConfigIpla}`
    );
  }

  getTipiInterventoGov(
    fkConfigIpla: number,
    idGoverno: number
  ): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/tipoInterventi/${fkConfigIpla}/gov?idGoverno=${idGoverno}`
    );
  }

  getTipiInterventoGovMacro(
    fkConfigIpla: number,
    idGoverno: number,
    idMacroIntervento: number
  ): Observable<any> {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(
        false
      )}/tipoInterventi/${fkConfigIpla}/${idGoverno}/${idMacroIntervento}`
    );
  }

  getTipiInterventoCategoria(
    fkConfigIpla: number,
    idMacroIntervento: number
  ): Observable<TipoIntervento[]> {
    return this.httpClient.get<TipoIntervento[]>(
      `${this.config.getBERootUrl(
        false
      )}/tipoInterventi/${fkConfigIpla}/macro?idCategoriaIntervento=${idMacroIntervento}`
    );
  }

  getCategorieForestali(): Observable<CategoriaForestale[]> {
    return this.httpClient.get<CategoriaForestale[]>(
      `${this.config.getBERootUrl(false)}/categoriaForestale`
    );
  }

  getTipiAllegatoPfa(): Observable<TipoAllegato[]> {
    // console.log(`${this.config.getBERootUrl(false)}/tipoAllegato/pfa`);
    return this.httpClient.get<TipoAllegato[]>(
      `${this.config.getBERootUrl(false)}/tipoAllegato/pfa`
    );
  }

  getAllTipoAllegatoTipoById(
    tipoSelezionato: number
  ): Observable<TipoAllegato[]> {
    /* console.log(
      this.config.getBERootUrl(false) +
        `/tipoAllegato/allTipoAllegatoTipoById/${tipoSelezionato}`
    ); */
    return this.httpClient.post<TipoAllegato[]>(
      this.config.getBERootUrl(false) +
        `/tipoAllegato/allTipoAllegatoTipoById/${tipoSelezionato}`,
      null
    );
  }

  getStatiIntervento(): Observable<StatoIntervento[]> {
    return this.httpClient.get<StatoIntervento[]>(
      `${this.config.getBERootUrl(false)}/statiInterventi`
    );
  }

  // step 5
  getStep5(idIntervento: number) {
    return this.httpClient.get<TagliStep5>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step5`
    );
  }
  postStep5(data: TagliStep5, idIntervento: number) {
    return this.httpClient.post<TagliStep5>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step5`,
      data
    );
  }
  putStep5(data: TagliStep5, idIntervento) {
    return this.httpClient.put<TagliStep5>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/step5`,
      data
    );
  }

  uploadAllegato(
    file: FormData,
    idIntervento: number,
    tipo: number
  ): Observable<any> {
    return this.httpClient.post<any>(
      `${this.config.getBERootUrl(
        false
      )}/documenti/uploadTagli?intervento=${idIntervento}&tipo=${tipo}`,
      file
    );
  }

  downloadAllegato(idDocumento: number) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(
        false
      )}/documenti/getTagliDoc?idDocumento=${idDocumento}`,
      { observe: "response", responseType: "blob" }
    );
  }
  deleteDocumentoAllegato(idDocumentoAllegato: number) {
    return this.httpClient.delete(
      `${this.config.getBERootUrl(
        false
      )}/documenti/delete/${idDocumentoAllegato}`
    );
  }

  getAllDocuments(idIntervento: number): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(
      `${this.config.getBERootUrl(
        false
      )}/tagli-selvicolturali/${idIntervento}/elencaDich`
    );
  }

  getAllDocumentsByIdIntervento(
    idIntervento: number
  ): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(
      `${this.config.getBERootUrl(false)}/documenti/interventi/${idIntervento}`
    );
  }

  getDocGestore(idIntervento: number): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(
      `${this.config.getBERootUrl(
        false
      )}/tagli-pfa/${idIntervento}/elencoDocsGestore`
    );
  }

  isNamePresent(name: string, docs: DocumentoAllegato[]): boolean {
    return docs.some((elem) => elem.nomeAllegato === name);
  }

  isTypePresent(typeId: number, docs: DocumentoAllegato[]): boolean {
    return docs.some((elem) => elem.idTipoAllegato === typeId);
  }

  // invio istanza
  postDataInvio(idIntervento: number, allegato: DocumentoAllegato[]) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/invio`,
      { allegati: allegato },
      { observe: "response" }
    );
  }

  postDataInvioMail(idIntervento: number, allegato: DocumentoAllegato[]) {
    return this.httpClient.post<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/invioEmail`,
      { allegati: allegato },
      { observe: "response" }
    );
  }

  getDataInvio(idIntervento: number) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/invio`
    );
  }

  // gestore
  getUtenteForIstanza(idIntervento: number) {
    return this.httpClient.get(
      `${this.config.getBERootUrl(false)}/tagli-pfa/bo/${idIntervento}/ceo`,
      { observe: "response" }
    );
  }

  autorizzaIstanza(datiAutoriz: any) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/istanze/bo/tagli/autorizza`,
      datiAutoriz
    );
  }

  verificaIstanza(datiAutoriz: any) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/istanze/bo/tagli/verifica`,
      datiAutoriz
    );
  }

  annullaIstanza(dati: any) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) + `/istanze/bo/tagli/annulla`,
      dati
    );
  }

  getinfoDrel(idIntervento: number) {
    return this.httpClient.get<InfoDocsMancanti>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/drel/info`
    );
  }

  getDrel(idIntervento: number) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/tagli-pfa/${idIntervento}/drel`
    );
  }

  uploadDrel(
    file: FormData,
    idIntervento: number
  ): Observable<DocumentoAllegato> {
    return this.httpClient.post<DocumentoAllegato>(
      `${this.config.getBERootUrl(false)}/documenti/uploadDrel/${idIntervento}`,
      file
    );
  }

  creaVarianteProroga(idIntervento: number, isVarinate: boolean) {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) +
        `/tagli-pfa/${idIntervento}/creaVarianteProroga?isVariante=${isVarinate}`
    );
  }

  getInfoVarianteProroga(idIntervento: number) {
    return this.httpClient.get<InfoVarianteProrogaModel>(
      this.config.getBERootUrl(false) +
        `/tagli-pfa/${idIntervento}/getInfoVarianteProroga`
    );
  }

  cambioUtilizzatore(idIntervento: number, data: TagliStep2) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) +
        `/tagli-pfa/${idIntervento}/cambioUtlizzatore`,
      data
    );
  }

  deletePlainParticella(idIntervento: number, id: number) {
    return this.httpClient.delete(
      this.config.getBERootUrl(false) +
        `/catasti/pfa/${idIntervento}?idGeoPlPropCatasto=${id}`
    );
  }

  getAziendeTAIF() {
    return this.httpClient.get<AziendaTAIF[]>(
      this.config.getBERootUrl(false) + `/taif`
    );
  }

  getSportelloCorrente() {
    return this.httpClient.get<any>(
      `${this.config.getBERootUrl(false)}/soggetti/bo/sportello/current`
    );
  }

  getSportelliAssociati() {
    return this.httpClient.get<SoggettoModel[]>(
      `${this.config.getBERootUrl(false)}/soggetti/bo/sportello/associato/list`
    );
  }

  getSportelloSeaarchStatiIstanza() {
    return this.httpClient.get<any>(
      this.config.getBERootUrl(false) + `/statiIstanza/sportello`
    );
  }

  putTitolarita(idIntervento: number, idConfUtente: number) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) +
        `/tagli-pfa/${idIntervento}/update/titolarita?idConfUtente=${idConfUtente}`,
      null
    );
  }

  getProfessionistiList() {
    return this.httpClient
      .get<any>(
        this.config.getBERootUrl(false) + `/soggetti/bo/professionisti/list`
      )
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element[
              "field"
            ] = `${element.cognome} ${element.nome} - ${element.codiceFiscale} - N. iscrizione all’ordine: ${element.nrIscrizioneOrdine} - Provincia : ${element.istatProvIscrizioneOrdine}`;
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

  getStep8RichiedentePF(search) {
    return this.httpClient
      .get<any>(
        this.config.getBERootUrl(false) + `/soggetti/bo/tagli/pf?s=${search}`
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

  getStep8RichiedentePG(search) {
    return this.httpClient
      .get<any>(
        this.config.getBERootUrl(false) + `/soggetti/bo/tagli/pg?s=${search}`
      )
      .pipe(
        map((result) => {
          result.forEach((element) => {
            element["field"] = `${element.denominazione} - ${
              element.partitaIva || element.codiceFiscale || "n.d"
            }`;
          });
          return this.filterDuplicates(result, "denominazione");
        })
      );
  }

  updateDataFineIntervento(datiAutoriz: any) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) +
        `/istanze/bo/vincolo/update/dataFineIntervento`,
      datiAutoriz
    );
  }

  updateTipologiaIstanza(idIntervento: number, data: TagliStep5) {
    return this.httpClient.put<any>(
      this.config.getBERootUrl(false) +
        `/tagli-pfa/${idIntervento}/cambioTipologia`,
      data
    );
  }
}
