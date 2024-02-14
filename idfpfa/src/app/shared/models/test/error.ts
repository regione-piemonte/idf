/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SrvError {
    code: string;
    errorMessage: string;
    fields: string;
    messaggioCittadino: string;
    sessionExpired? : boolean;
}