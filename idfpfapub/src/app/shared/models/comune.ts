/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Comune {
  codiceBelfiore: string;
  denominazioneComune: string;
  indiceBoscosita: number;
  istatComune: string;
  supForestale: number;
  supTotale: number;
}
export class Comuni {
  idComune: number;
  istatComune: string;
  istatProv: string;
  denominazioneComune: string;
  fk_area_forestale: number;
  codiceBelfiore: string;
  dataInizioValidita?: Date;
  dataFineValidita?: Date;
}
