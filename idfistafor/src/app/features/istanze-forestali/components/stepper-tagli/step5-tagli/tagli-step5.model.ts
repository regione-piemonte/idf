/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel } from "src/app/shared/models/comune.model";
import { TipoIstanzaEnum } from "src/app/shared/models/tipo-istanza.enum";
import { IntervSelvicolturale } from "../step4-tagli/tagli-step4.model";
import { Intervento } from './../step4-tagli/tagli-step4.model';


export interface TagliStep5 {
  intervSelvicolturale?: IntervSelvicolturale;
  intervento?: Intervento;
  tipoIstanzaProposta?: TipoIstanzaEnum;
  tipoIstanza?: TipoIstanzaEnum;
  tipoIstanzaId?: number;
  documentazioneAllegata?: Allegati[];
  allegati?: Allegati[];
  tecnicoForestale?: TecnicoForestale;
  motivazione?: string;
  noteFinaliRichiedente?: string;
}

export interface Allegati {
  idDocumentoAllegato?: number;
  idGeoPlPfa?: number;
  fkIntervento?: number;
  idTipoAllegato?: number;
  descrTipoAllegato?: string;
  nomeAllegato?: string;
  dimensioneAllegatoKB?: number;
  dataIniziValidita?: Date;
  dataFineValidita?: Date;
  dataInserimento?: Date;
  dataAggiornamento?: Date;
  fkConfigUtente?: number;
  note?: string;
  isDeleted?: boolean;
  uidIndex?: string;
  idDocDoqui?: string;
  udClassificazioneDoqui?: string;
  fkTipoAllegato?: number;
}


export interface TecnicoForestale {
  idSoggetto?: number;
  personaDatiOption?: string;
  flgEntePubblico?: boolean;
  nome?: string;
  cognome?: string;
  codiceFiscale?: string;
  indirizzo?: string;
  civico?: string;
  cap?: string;
  nrTelefonico?: string;
  eMail?: string;
  comune?: ComuneModel;
  denominazione?: string;
  partitaIva?: string;
  pec?: string;
  ownerCodiceFiscale?: string;
  numAlboForestale?: string;
  isTAIF?: boolean;
}

