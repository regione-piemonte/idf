/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class RouteParams {
  id?: string;
  query?: QueryParam;
}

export class QueryParam {
  idComune?: string;
  idPopolamento?: string;
}
