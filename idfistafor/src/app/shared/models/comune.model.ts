/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ComuneModel {
  constructor(
  public idComune?: number,
  public istatComune?: string,
  public istatProv?: string,
  public denominazioneComune?: string,
  public fk_area_forestale?: number,
  public codiceBelfiore?: string
  ) {}
}


