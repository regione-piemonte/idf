/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel, ComuniModel } from "./comune.model";

export class DataTaglio {
  descrizioneIntervento?: DescrizioneIntervento;
  propCatastos?: PropCatastos[];
  istanzaDetails?: IstanzaDetails;
  utilizzatoreDetails?: UtilizzatoreDetails;
  ricadenzaInfo?: RicadenzaInfo;
  comuneResource?: ComuneModel[];
}

export class DescrizioneIntervento {
  supTagliata?: number;
  descrizione?: string;
  governo?: string;
  tipoDiIntervento?: string;
  categorieForestali?: string;
  localita?: string;
  flgPiedilista?: string;
  numeroPiante?: number;
  finalitaDelTaglio?: string;
  ramaglia?: number;
  stimaMassaRetraibileM3?: number;
  viabilita?: string;
  noteEsbosco?: string;
  fasciaAltimetrica?: string;
  specieInteresate?: string;
  tipoDiEsbosco?: string;
  destinazioneDelLegname?: string;
  descrizioneIntervento?: any;
  supInteressata?: any;
  supCatastale?: any;
}

export class PropCatastos {
  comune?:ComuniModel;
  denominazioneComune?: string;
  denominazioneProvincia?: string;
  foglio?: number;
  particella?: string;
  sezione?: string;
  supCartograficaHa?: number;
  supCatastaleMq?: number;
  supIntervento?: number;
  idGeoPlPropCatasto?: number;
}

export class IstanzaDetails {
  tipoDiComunicazione?: string;
  numeroIstanza?: string;
  descrizione?: string;
  comune?: string;
  dataDiCompilazione?: string;
  dataPrevistaPerInizio?: string;
}

export class UtilizzatoreDetails {
  richiedente?: SoggettoResource;
  utilizzatore?: SoggettoResource;
  tecnicoForestale?: SoggettoResource;
}
export class SoggettoResource {
  idSoggetto?: number;
  nome?: string;
  cognome?: string;
  codiceFiscale?: string;
  partitaIva?: string;
  denominazione?: string;
}

export class RicadenzaInfo {
  areeProtette?: string;
  reteNatura2000?: string[];
  popolamentoSeme?: string;
  categoriaForestali?: string;
}
