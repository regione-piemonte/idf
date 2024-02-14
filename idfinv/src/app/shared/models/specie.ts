/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SpecieInfo } from "./specie-info";
import { Alberi } from "./alberi";

export class Specie {
    gruppo?: string;
    linkFoto?: string;
    rAdsSpecie?: SpecieInfo | string;
    tIdfInveAlberi?: Alberi | string;
    altezza?: number;
    flgTipoSpecie?: string;
    incremento?: number;
    qualita?: string;
    idgeoPtAds?: number;

    idSpecie?: number;
    codice?: string;
    codGruppo?: string;
    codicePignatti?: string;
    latino?: string;
    volgare?: string;
    flg386?: string;
    linkScheda?: string;
    mtdOrdinamento?: number;
    flgVisibile?: number;
}
