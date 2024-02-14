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
    public denominazioneComune?: string
  ) {}
}

export class ComuniModel {
  public id?: number;
  public comune?: ComuneModel;
  public denomazione?: string;
  public istatComune?: number;
  public sezione?: string;
  public foglio?: number;
  public particella?: string;
  public supCatastale?: number;
  public toDelete?: boolean;
  public supIntervento?: number;
}

export class ParticelleCatastali {
  public particelleCatastali: ComuniModel[];
}
