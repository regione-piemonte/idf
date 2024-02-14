/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Comune } from "./comune";

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