/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class AreaSearch {
    istatProvs?: {istatProv: string, idComune: number}[]; //string [];
    idCategoriaForestales?: number [];
    idAmbitos?: string[];
    dettaglios?: string [];
    tipologias?:  string [];
    statoSchedas?: string [];
    formDates?:{fromDate: Date, toDate: Date} [] | {fromDate: string, toDate: string} [] | any[];
}