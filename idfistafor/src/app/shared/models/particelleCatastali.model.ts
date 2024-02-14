/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ComuneModel } from "./comune.model";
import { Geometry } from "./geometry.model";

export interface ParticelleCatastali {
  idGeoPlPropCatasto?: number;
  id?:                 number;
  comune?:             ComuneModel;
  sezione?:            string;
  foglio?:             number;
  particella?:         string;
  supCatastale?:       number;
  supCartograficaHa?:  number;
  supIntervento?:      number;
  toDelete?:           boolean;
  fkConfigUtente?:     number;
  geometry?:           Geometry;
}
