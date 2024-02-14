/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class FoglioModel {
  constructor(
  public features?: FeatureModel,
  public idComune?: string,
  public istatComune?:string,
  public istatProv?: string, 
  public geometry?:string,
  public id?:string,
  public properties?:PropertiesModel){}
}

export class FeatureModel {
  constructor(
public geometry?:string,
public id?:string,
public properties?:PropertiesModel[]){}
}


export class PropertiesModel {
  constructor(
  public cod_com_belfiore?:string,
  public cod_com_istat?:string,
  public comune?:string,
  public foglio?:string,
  public id?:number,
  public sezione?:string){}
}