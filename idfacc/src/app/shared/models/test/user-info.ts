/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class UserInfo {
    codFisc: string;
    codRuolo: string;
    cognome: string;
    ente: string;
    nome: string;
    ruolo: string;
    livAuth: number;
    community: string;
}

export class ConfigUtente {
    dataPrimoAccesso?: string;
    dataUltimoAccesso: string;
    fkProfiloUtente: number;
    fkSoggetto: number;
    fkTipoAccreditamento: number;
    flgPrivacy: number;
    idConfigUtente: number;
    nrAccessi: number;
}