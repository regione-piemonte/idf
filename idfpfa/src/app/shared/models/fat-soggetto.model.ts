/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export interface FatSoggetto {
  idSoggetto?:         number;
  personaDatiOption?:  string;
  flgEntePubblico?:    boolean;
  nome?:               string;
  cognome?:            string;
  codiceFiscale?:      string;
  indirizzo?:          string;
  civico?:             string;
  cap?:                string;
  nrTelefonico?:       string;
  eMail?:              string;
  comune?:             Comune;
  denominazione?:      string;
  partitaIva?:         string;
  pec?:                string;
  ownerCodiceFiscale?: string;
  numAlboForestale?:   string;
  isTAIF?:             boolean;
}

export interface Comune {
  idComune?:            number;
  istatComune?:         string;
  istatProv?:           string;
  denominazioneComune?: string;
}
