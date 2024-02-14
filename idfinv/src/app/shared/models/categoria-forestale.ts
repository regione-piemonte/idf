/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { TipoForestale } from "./tipo-forestale";

export class CategoriaForestale {
  codiceAmministrativo?: string;
  descrizione?: string;
  idCategoriaForestale?: number;
  tipoForestaleList?: TipoForestale[];
}
