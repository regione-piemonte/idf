/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class AreaDiSaggioSave {
  idgeoPtAds?:number;
  codiceADS?: string;
  tipologiaDiRilievo?: string;
  ambitoDiRilevamento?: string;
  idDettaglioAmbito?: string;
  dataRilevamento?: string;
  fkSoggettoPg?: string;
  fkSoggettoPf?: string;
  comune?: string;
  utmEST?: number;
  utmNORD?: number;
  quota?: string;
  espozione?: string;
  inclinazione?: string;
  particellaForestale?: string;
  proprieta?: string;
  ambitoSpecifico?:string;
}
