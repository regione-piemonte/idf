/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel } from "./comune.model";

export class ParticleCadastral {
  // length: number;
  constructor(
    public idComune?: number,
    public sezione?: string,
    public foglio?: number,
    public particella?: string,
    public supCatastale?: number,
    public comune?: ComuneModel,
    public supCartograficaHa?: number,
    public idGeoPlPropCatasto?:any,
    public id?: number,
    public supIntervento?: number,
    public toDelete?: boolean
  ) {}
}
export class ShowParcel {
  constructor(
    public id?: number,
    public idComune?: number,
    public comune?: any,
    public sezione?: string,
    public foglio?: number,
    public particella?: string,
    public supCatastale?: number,
    public particelleCatastali?: any[],
    public toDelete?: boolean,
    public supIntervento?:number,
    public idGeoPlPropCatasto?: any,
    public denominazioneComune?: string,
  ) {}
}
