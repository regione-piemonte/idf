/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Data } from "./dettaglio";

export class Interventi {
  idIntervento: number;
  nrProgressivoInterv: number;
  annataSilvana: string;
  nParticelaForestale: number[];
  denominazioneParticella: string[];
  dataInizio: Data;
  dataInizioString: string;
  dataFine: Data;
  dataFineString: string;
  descrizione: string;
  localita: string;
  superficieInteressata: number;
  m3Prelevati: number;
  descrStatoIntervento: string;
  comunicazioneDiTaglio: string;
}
