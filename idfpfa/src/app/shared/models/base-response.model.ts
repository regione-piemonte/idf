/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export type BaseResponse<T> = {
  codice: string;
  testo: string;
  tipologia: string;
  payload: T;
}