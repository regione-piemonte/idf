/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export interface CategoriaForestale {
  idCategoriaForestale?: number;
  fkParamMacroCatfor?:   number;
  codiceAmministrativo?: string;
  descrizione?:          string;
  mtdOrdinamento?:       number;
  flgVisibile?:          number;
}
