/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SezioneModel {
  constructor(
  public denominazioneComune?: string,
  public idComune?: string,
  public istatComune?:string,
  public istatProv?: string) {}
}