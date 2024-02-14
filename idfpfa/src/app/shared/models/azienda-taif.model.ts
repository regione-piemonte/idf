/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export interface AziendaTAIF {
    codiceFiscale?:         string;
    partitaIva?:            string;
    ragioneSociale?:        string;
    numeroAlbo?:            string;
    dataIscrizioneAlbo?:    Date;
    dataAggiornamento?:     Date;
    idStatoPratica?:        number;
    statoPratica?:          string;
    flgSezione?:            string;
    istatComune?:           string;
    indirizzo?:             string;
    civico?:                string;
    cap?:                   string;
    telefono?:              string;
    email?:                 string;
    pec?:                   string;
    codiceFiscaleTitolare?: string;
    cognomeTitolare?:       string;
    nomeTitolare?:          string;
    label?:                 string;
}
