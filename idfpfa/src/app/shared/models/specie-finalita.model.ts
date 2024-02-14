/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export interface SpecieFinalita {
  idSpecie?:            number;
  idIntervento?:        number;
  idFinalitaTaglio?:    number;
  percAutoconsumo?:     number;
  percCommerciale?:     number;
  dataInserimento?:     string;
  dataAggiornamento?:   string;
  flgSpeciePriorita?:   string;
  descrFinalitaTaglio?: string;

}
