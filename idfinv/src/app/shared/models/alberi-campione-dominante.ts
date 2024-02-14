/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class AlberiCampioneDominante {
    specie: string;
    latino: string;
    qualita: string;
    diametro: number;
    altezza: number;
    incremento: number;
    eta: number;
    note: string;
    codiceAds: string;
    codTipoCampione: string =  "CAM" || "DOM" || "CS1" || "CS2";
    codiceSpecie: string;
    gruppo: string;
    alberiSeme: number;
    alberiPollone: number;
    semePollone?: string;
    idSpecie?: number;
}