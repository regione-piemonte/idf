/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel } from "./comune.model";

export class InterventoUtilizzatore {
    constructor(
    public nome?: string,
    public cognome?: string,
    public codiceFiscale?: string,
    public comune?: ComuneModel,
    public indirizzo?: string,
    public civico?: string,
    public cap?: string,
    public nrTelefonico?: string,
    public eMail?: string,
    public denominazione?: string,
    public partitaIva?: string,
    public pec?: string,
    public ownerCodiceFiscale?: string,
    public tipoDiUtilizzatore?: number,
    public tipoUtilizzatoreRicerca?: number,
    public idTecnicoForestale?: number
    ) {}
​

}