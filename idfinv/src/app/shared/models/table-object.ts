/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Content<T> {
    content?: T;
    totalElements?: number;
    limit?: number;
    page?: number;
  }