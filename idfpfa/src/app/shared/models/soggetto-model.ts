/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SoggettoModel {
  constructor(
  public idSoggetto?: number,
  public fkComune?: number,
  public nome?: string,
  public cognome?: string,
  public codiceFiscale?: string,
  public partitaIva?: string,
  public denominazione?: string,
  public indirizzo?: string,
  public nrTelefonico?: string,
  public eMail?: string,
  public pec?: string,

  public nIscrizioneOrdine?: string,
  public istatProvIscrizioneOrdine?: string,

  public flgSettoreRegionale?: number,

  public dataInserimento?: DataInserimentoModel | string,
  public fkConfigUtente?: string | number,
  public civico?: string,
  public cap?: string
  ) {}
}

export class DataInserimentoModel {
  constructor(
    public year?: number,
    public month?: string,
    public era?: string,
    public dayOfMonth?: number,
    public dayOfWeek?: string,
    public dayOfYear?: number,
    public leapYear?: boolean,
    public monthValue?: number,
    public chronology?: ChronologyModel | string
  ) {}
}

export class ChronologyModel {
  constructor(
    public id?: string,
    public calendarType?: string
  ) {}
}

