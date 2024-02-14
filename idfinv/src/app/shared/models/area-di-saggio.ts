/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { StatoAds } from "./statoAds";

export class AreaDiSaggio {
    idgeoPtAds?: number;
    lastCompletedStep?: number;
    codiceADS?: string; 
    comune?: string; 
    categoriaForestale?: string; 
    ambitoDiRilevamento?: string; 
    dettaglioAmbito?: string;
    ambitoSpecifico?:string;
    tipologia?: string;
    descrTipoAds?: string;
    tipoAds?:number;
    quota?: string;
    tipoForestale?: string;
    tipoStrutturale?: string;
    raggioArea?: string;
    classeDiFertilita?: string;
    idClasseDiFertilita?:number;
    dataRilevamento?: string; 
    espozione?: string; 
    proprieta?: string;
    inclinazione?: string; 
    stadioDiSviluppo?: string;
    densitaCamp?: string; 
    rinnovazione?: string;
    speciePrevalenteRinnovazione?: string;
    speciePrevalenteRinnovDescr?: string;
    particellaForestale?: string; 
    assettoEvolutivoColturale?: string; 
    nCepaie?: string; 
    coperturaChiome?: string; 
    coperturaCespugli?: string; 
    coperturaErbacea?: string; 
    lettiera?: string; 
    destinazione?: string; 
    intervento?: string; 
    esbosco?: string; 
    esboscoDescr?: string; 
    defp?: string; 
    desp?: string; 
    mdp?: string; 
    intesitaDanni?: string; 
    nPianteMorte?: string; 
    defogliazione?: string; 
    ingiallimento?: string; 
    pascolamento?: string;
    combustibilePrincipale?: number;
    combustibileSecondario?: number;
    tavola?: number;
    utmNord?: number;
    utmEst?: number;
    soggettoPg?: string;
    soggettoPf?: string;
    priorita?: string;
    dannoPrevalente: string;
    statoScheda?:StatoAds;
    provincia?:string;
    errors?:number;
    descrTipoStrutturale:string;
    fattoreNumerazione:string;
    note:string;
   }
