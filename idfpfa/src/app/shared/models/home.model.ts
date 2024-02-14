/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class HomeModel {
  constructor(
    public codiceFiscale?: string,
    public cognome?: string,
    public nome?: string,
    public ruolo?: any,
    public provider?: string,
    public fkProfiloUtente? : any,
    public opzioneId?: number,
    public opzioneDescr?: string,

    public fkComune?: number[],
    public comune?: string[],
    public comuneId?: number,
    public fkSoggetto? : number,
    public indirizzo?: string,
    public nrTelefonico?: string,
    public eMail?: string,
    public civico?: string,
    public cap?: number,
    public flagPrivata?: boolean,

    public fkProfilo?: number,
    public idConfigUtente?: number,

    public fkTipoAccreditamento?: string,
    public tipoAccreditamento?: string,
    public fkSoggettoSportello?: number,

    public denominazione?: string,

    public partitaIva?: string,
    public pec?: string,
    public numeroIscrizione?: string,
    public provinciaOrdine?: string,
    public codiceFiscaleDelega?: string
  ) {}
}
