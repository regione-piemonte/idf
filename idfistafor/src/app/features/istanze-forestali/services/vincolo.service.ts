/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BasedEconomicValueModel } from 'src/app/shared/models/based-economic-value.model';
import { CheckboxAndRadio } from 'src/app/shared/models/checkbox-and-radio';
import { InfoVarianteProrogaModel } from 'src/app/shared/models/info-variante-proroga.model';
import { DocumentoAllegato, IstanzaTaglio, PlainSestoSezione } from 'src/app/shared/models/plain-sesto-sezione.model';
import { StepPosition } from 'src/app/shared/models/step-position.model';
import { Step4Model } from 'src/app/shared/models/step4.model';
import { TipoAllegato } from 'src/app/shared/models/tipo-allegato';
import { UserChoiceModel } from 'src/app/shared/models/user-choice.model';
import { UserCompanyDataModel } from 'src/app/shared/models/user-company-data.model';
import { Content, ViewInstance } from 'src/app/shared/models/view-instance';
import { ConfigService } from 'src/app/shared/services/config.service';
import { VincoloStep3 } from '../components/stepper-vid/step3-vid/vincoloStep3';
import { VincoloStep4 } from '../components/stepper-vid/step4-vid/vincoloStep4';
import { VincoloStep5 } from '../components/stepper-vid/step5-vid/vincoloStep5';
import { VincoloStep6 } from '../components/stepper-vid/step6-vid/vincoloStep6';

@Injectable({
  providedIn: 'root'
})
export class VincoloService {

  typeFiltering = {
    TipoIntervento: ['descrTipoIntervento'],
    CategoriaProfessionale: ['descrCategoriaProfessionale'],
  };

  constructor(private httpClient: HttpClient, private config: ConfigService) {}


  // Generici.

  autocompleteFilter(event, res: any, whatFilteringFor) {
    const filtered: any[] = [];

    for(let i = 0; i < res.length; i++) {
      let filtering;

      for(let j = 0; j < this.typeFiltering[whatFilteringFor].length; j++) {
        filtering = res[i][this.typeFiltering[whatFilteringFor][j]];

        if (typeof filtering === 'number')
          if (filtering.toString().indexOf((event.query).toString()) === 0) filtered.push(res[i]);

        if (typeof filtering === 'string')
          if (filtering.toLowerCase().indexOf((event.query).toLowerCase()) === 0) filtered.push(res[i]);
      }
    }

    if(this.typeFiltering[whatFilteringFor].length > 1)
      return this.filterDuplicates(filtered, 'codiceFiscale');

    return filtered;
  }

  deleteIntervento(idIntervento: number){
    return this.httpClient.delete(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}`);
  }

  filterDuplicates(array, key) {
    return array = array.filter((item, index, self) => index === self.findIndex(t => t[key] === item[key]));
  }

  getAdpfor(tipoIstanzaId: string) {
    return this.httpClient.get<UserChoiceModel>(this.config.getBERootUrl(false) + `/idfvincolo?tipoIstanzaId=${tipoIstanzaId}`);
  }

  getProfessionistiList() {
    return this.httpClient.get<any>(this.config.getBERootUrl(false)
    + `/soggetti/bo/professionisti/list`)
    .pipe(map((result) => {
      result.forEach(element => {
        element['field'] = `${element.cognome} ${element.nome} - ${element.codiceFiscale} - N. iscrizione all’ordine: ${element.nrIscrizioneOrdine} - Provincia : ${element.istatProvIscrizioneOrdine}`;
      });
      return this.filterDuplicates(result, 'codiceFiscale');
    }) ); }

  insertGeometryFromSingleParticella(idIntervento: number, data: any): Observable<any> {
    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/inserisciParticella`, data);
  }

  putAdpfor(moduloDatiPersonali: UserChoiceModel) {
    return this.httpClient.put(this.config.getBERootUrl(false) + `/idfvincolo`, moduloDatiPersonali);
  }

  putTitolarita(idIntervento: number, idConfUtente: number) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/update/titolarita?idConfUtente=${idConfUtente}`, null);
  }

  getArrayOfInstance(page: number, limit: number, sortField?: string) {
    let sortQuery: string;
    let tipoIstanzaId = sessionStorage.getItem("tipoIstanzaId");
    if(sortField) sortQuery = `&sort=${sortField}`;
    return this.httpClient.get<Content<ViewInstance[]>>(this.config.getBERootUrl(false)
      + `/vincolo/idfvincidro?tipoIstanzaId=${tipoIstanzaId}&page=${page}&limit=${limit}${sortQuery ? sortQuery : ''}`);
  }

  getSecondStepRicadenze(idIntervento: number): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/vincolo/secondo/elencoRicadenze?idIntervento=${idIntervento}`);
  }

  getSuperfici(idIntervento: number): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/superfici`);
  }

  getCoefficienteCalcoloImportoSupNonBoscata(): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/vincolo/parametri/COEFF_CALCOLO_EURO_SUP_NON_BOSCATA_LR45`);
  }

  getValoreMarcaBollo(): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/vincolo/parametri/VALORE_MARCA_DA_BOLLO`);
  }

  getStepNumber(editMode?: number): Observable<StepPosition> {
    return this.httpClient.get<StepPosition>(`${this.config.getBERootUrl(false)}/vincolo/${editMode}`);
  }

  getTipiIntervento(fkConfigIpla?: number): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/tipoInterventi/${fkConfigIpla}`);
  }

  getCategorieProfessionali(): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/categorieProfessionali`);
  }

  getCategoriaProfessionaleById(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/categorieProfessionali/${id}`);
  }

  recalculateParticelle(idIntervento: number) {
    return this.httpClient.get<PlainSestoSezione>(`${this.config.getBERootUrl(false)}/vincolo/recalculate/particelle/${idIntervento}`);
  }

  deletePlainParticella(idIntervento: number, id: number){
    return this.httpClient.delete(this.config.getBERootUrl(false) + `/catasti/vincolo/${idIntervento}?idGeoPlPropCatasto=${id}`);
  }

  // Step nuova istanza.

  getStep1(idIntervento: number) {
    return this.httpClient.get<UserCompanyDataModel>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/prima`);
  }

  postStep1(data: UserCompanyDataModel) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/vincolo/prima`, data);
  }

  putStep1(data: UserCompanyDataModel, idIntervento) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/prima`, data);
  }

  getStep2(idIntervento: number): Observable<any> {
    return this.httpClient.get<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/seconda`);
  }

  postStep2(data: any, isTableEmpty: boolean, idIntervento: number): Observable<any> {
    /*return isTableEmpty ?
      this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/seconda`, data)
      : this.httpClient.put<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/seconda`, data);*/

    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/seconda`, data)
  }

  putStep2(data: any, isTableEmpty: boolean, idIntervento: number): Observable<any> {
    return this.httpClient.put<any>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/seconda`, data)
  }

  getStep3(idIntervento: number) {
    return this.httpClient.get<VincoloStep3>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/terza`);
  }

  postStep3(data: VincoloStep3, idIntervento: number) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/terza`, data);
  }

  getStep4(idIntervento: number) {
    return this.httpClient.get<VincoloStep4>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/quarta`);
  }

  postStep4(data: VincoloStep4, idIntervento: number) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/quarta`, data);
  }

  getStep5(idIntervento: number) {
    return this.httpClient.get<VincoloStep5>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/quinta`);
  }

  postStep5(data: VincoloStep5, idIntervento: number) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/quinta`, data);
  }

  getStep6(idIntervento: number) {
    return this.httpClient.get<VincoloStep6>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/sesta`);
  }

  getTipiAllegatoByIdIntervento(idIntervento: number): Observable<any> {
    return this.httpClient.get<TipoAllegato>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/tipiallegato`);
  }

  postStep6(data: VincoloStep6, idIntervento: number) {
    return this.httpClient.post<any>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/sesta`, data);
  }

  getCauzione(idIntervento: number) {
    return this.httpClient.get<number>(this.config.getBERootUrl(false) + `/vincolo/${idIntervento}/cauzione`);
  }

  getAllDocuments(idIntervento: number): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/elencaDich`);
  }

  getDocGestore(idIntervento: number): Observable<[DocumentoAllegato]> {
    return this.httpClient.get<[DocumentoAllegato]>(`${this.config.getBERootUrl(false)}/vincolo/${idIntervento}/elencoDocsGestore`);
  }

  uploadAllegato(file: FormData, idIntervento: number, tipo: number): Observable<any> {
    return this.httpClient.post<any>(`${this.config.getBERootUrl(false)}/documenti/uploadVincolo?intervento=${idIntervento}&tipo=${tipo}`, file);
  }

  uploadRicevutePagamento(file: FormData, idIntervento: number): Observable<DocumentoAllegato> {
    return this.httpClient.post<DocumentoAllegato>(`${this.config.getBERootUrl(false)}/documenti/uploadRicevutePagamentoVincolo?intervento=${idIntervento}`, file);
  }

  downloadAllegato(idDocumento: number) {
    return this.httpClient.get(`${this.config.getBERootUrl(false)}/documenti/getVincoloDoc?idDocumento=${idDocumento}`, {observe: 'response', responseType: 'blob'});
  }
  deleteDocumentoAllegato(idDocumentoAllegato: number){
    return this.httpClient.delete(`${this.config.getBERootUrl(false)}/documenti/delete/${idDocumentoAllegato}`);
  }
  getinfoCauzione(idIntervento: number) {
    return this.httpClient.get<any>(this.config.getBERootUrl(false) + `/vincolo/cauzione?idIntervento=${idIntervento}`);
  }

  getUtenteForIstanza(idIntervento: number){
    return this.httpClient.get(`${this.config.getBERootUrl(false)}/vincolo/bo/${idIntervento}/ceo`, {observe: 'response'});
  }

  creaVarianteProroga(idIntervento: number,isVarinate: boolean, isCompetenzaRegionale:boolean) {
    return this.httpClient.get<any>(this.config.getBERootUrl(false)
      + `/vincolo/${idIntervento}/creaVarianteProroga?isVariante=${isVarinate}&isCompetenzaRegionale=${isCompetenzaRegionale}`);
  }

  getInfoVarianteProroga(idIntervento: number) {
    return this.httpClient.get<InfoVarianteProrogaModel>(this.config.getBERootUrl(false)
      + `/vincolo/${idIntervento}/getInfoVarianteProroga`);
  }

  autorizzataIstanza(datiAutoriz: any) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/istanze/bo/vincolo/autorizzata`, datiAutoriz);
  }

  updateDataFineIntervento(datiAutoriz: any) {
    return this.httpClient.put<any>(this.config.getBERootUrl(false) + `/istanze/bo/vincolo/update/dataFineIntervento`, datiAutoriz);
  }

  saveOrDeleteIstanzaTaglio(idIntervento: number, istanzaTaglio : IstanzaTaglio) {
    return this.httpClient.put<IstanzaTaglio[]>(this.config.getBERootUrl(false)
      + `/vincolo/update/istanzaTaglio?idIntervento=${idIntervento}`, istanzaTaglio);
  }

  getIstanzeTaglioByIdIntervento(idIntervento: number): Observable<IstanzaTaglio[]> {
    return this.httpClient.get<IstanzaTaglio[]>(`${this.config.getBERootUrl(false)}/vincolo/load/istanzeTaglio?idIntervento=${idIntervento}`);
  }


  isNamePresent(name : string, array: DocumentoAllegato[]) :boolean {
    let elems = array.filter((elem)=>{(name.split(' - ')[0]) == (elem.nomeAllegato.split(' - ')[0])})
    return elems.length > 0;
  }
}
