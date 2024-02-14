/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Soggetto {
    idSoggetto?: number;
    fkComune?: number;
    codiceFiscale?: string;
    cognomeRagSociale?: string;
    nome?: string;
    denominazione?: string;
    partitaIva?: string;
    istatComune?: string;
    indirizzo?: string;
    nrTelefonico?: string;
    eMail?: string;
    pec?: string;
    nIscrizioneOrdine;
    istatProvIscrizioneOrdine: string;
    istatProvCompetenzaTerr: string;
    flgSettoreRegionale: number;
    dataInserimento: Date;
    dataAggiornamento: Date;
    fkConfigUtente: number;
}