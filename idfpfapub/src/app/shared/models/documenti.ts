/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Data } from "./dettaglio";

// Generated by https://quicktype.io

export class Documenti {
  idDocumentoAllegato: number;
  idGeoPlPfa: number;
  fkIntervento: number;
  fkTipoAllegato: number;
  nomeAllegato: string;
  dimensioneAllegatoKB: number;
  dataIniziValidita: Data;
  fkConfigUtente: number;
  note: string;
}
