/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ViewInstance {
  constructor(
    public idIntervento?: number,
    public nrIstanzaForestale?: number,
    public idTipoIstanza?: number,
    public dataInvio?: DataInvio,
    public richiedente?: Richidente,
    public comune?: Comune,
    public stato?: string,    
    public idStato?: number,
    public statoCauzione?: string,
    public varianteProroga?: string
  ) {}
}

export class Content<T> {
  content?: T;
  totalElements?: number;
  limit?: number;
  page?: number;
}

export class DataInvio {
  constructor(
    public year?: number,
    public month?: string,
    public era?: string,
    public dayOfMonth?: number,
    public dayOfWeek?: string,
    public dayOfYear?: number,
    public leapYear?: boolean,
    public monthValue?: number,
    public chronology?: Chronology
  ) {}
}

export class IstanzaInviata {
  constructor(
    public idIstanzaIntervento?: number,
    public dataInvio?: string,
    public dataInvioFormated?: string,
    public dataVerifica?: string,
    public dataProtocollo?: string,
    public dataInserimento?: string,
    public annoInserimento?: number,
    public dataAggiornamento?: string,
    public utenteCompilatore?: string,
    public nrProtocollo?: string,
    public nrDeterminaAut?: string,
    public fkStatoIstanza?: number,
    public fkTipoIstanza?: number,
    public dataFineIntervento?: number,
    public dataTermineAutorizzazione?: number,
    public nrIstanzaForestale?: string,
    public utenteApprovatore?: string,
    public motivazioneRifiuto?: string,
  ) {}
}

export class Chronology {
  constructor(
    public id?: string,
    public calendarType?: string
  ) {}
}

export class ViewInstanceTable {
  constructor(
    public idIntervento?: number,
    public numeroIstanza?: number,
    public idTipoIstanza?: number,
    public dataInvio?: string,
    public richiedente?: string,
    public comune?: string,
    public stato?: string,
    public idStato?: number,
    public statoCauzione?: string,
    public varianteProroga?: string
  ) {}
}

export class Richidente {
  constructor(
    public idSoggeto?: number,
    public nome?: string,
    public cognome?: string,
    public codiceFiscale?: string,
    public partitaIva?: string,
    public denominazione?: string
  ) {}
}

export class Comune {
  constructor(
    public idComune?: number,
    public istatComune?: string,
    public istatProv?: string,
    public denominazioneComune?: string
  ) {}
}
