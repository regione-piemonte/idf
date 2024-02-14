/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { Data } from "@angular/router";

export class DelegaModel {
    constructor(
    public idConfigUtente?: number,
    public cfDelegante?: string,
    public dataInizio?: Data,
    public dataFine?: Data
    ) {}
}