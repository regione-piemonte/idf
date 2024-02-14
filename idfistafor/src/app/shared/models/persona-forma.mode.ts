/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class PersonaFisica {
  constructor(
    public codiceFiscale?: string,
    public cognome?: string,
    public nome?: string,
    public istatComuneRes?: string,
    public indirizzoRes?: string,
    public civicoRes?: string,
    public capResidenca?: number,
    public telefono?: number,
    public mail?: string,
    public flagTrattamento?: boolean
  ) {}
}

export class PeronaGiuridica {
  constructor(
    public codiceFiscale?: string,
    public partitaIVA?: string,
    public denominazione?: string,
    public comune?: string,
    public indirizzo?: string,
    public CAP?: string,
    public civico?: string,
    public PEC?: string,
    public eMail?: string,
    public recapitoTelefonico?: string
  ) {}
}
