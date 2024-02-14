/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class ParticellaModel {
  constructor(
  public features?: FeatureModelParticella,
  public idComune?: string,
  public istatComune?:string,
  public istatProv?: string, 
  public geometry?:string,
  public id?:string,
  public properties?:PropertiesModelParticella){}
}

export class FeatureModelParticella {
constructor(
public geometry?:string,
public id?:string,
public properties?:PropertiesModelParticella[]){}
}


export class PropertiesModelParticella {
  constructor(
  public comune?:string,
  public allegato?:string,
  public id_geo_particella?:number,
  public id?:number,
  public id_geo_foglio?:number,
  public cod_com_belfiore?:string,
  public sviluppo?:number,
  public foglio?:string,
  public particella?:string,
  public aggiornato_al?:string){}
}