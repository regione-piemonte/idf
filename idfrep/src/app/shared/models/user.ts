/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class User {
    idSoggetto?: number;
    idConfigUtente?: number;
    codiceFiscale?: string;
    cognome?: string;
    nome?: string;
    ruolo?: string;
    provider?: string;
    comune?: Comune;
    indirizzo?: string;
    nrTelefonico?: string;
    email?: string;
    flgPrivacy?: number;
    civico?: string;
    cap?: string;
}

export class Comune {
    idComune?: number;
    istatComune?: string;
    istatProv?: string;
    denominazioneComune?: string;
    fk_area_forestale?: number;
    codiceBelfiore?: string;
}