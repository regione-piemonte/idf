/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TipoAllegato } from "./tipo-allegato";

export class InfoDocsPagamenti {
    constructor(
      public cauzioneMancante? : boolean,
      public compensazioneType?: string,
      public compensazioneFisicaMancante? : boolean,
      public compensazioneMonetariaMancante? : boolean,
      public listDocs? : TipoAllegato[]
    ) {}
  }