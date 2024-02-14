/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Data } from "./dettaglio";

export class Documenti {
  idDocumentoAllegato: number;
  idGeoPlPfa: number;
  fkIntervento: number;
  fkTipoAleggato: number;
  nomeAllegato: string;
  dimensioneAllegatoKB: number;
  dataIniziValidita: Data;
  fkConfigUtente: number;
  note: string;
}
