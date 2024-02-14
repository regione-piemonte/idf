/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class DatiTehnici {
  constructor(
    public idTipoIstanza?: number,
    public tipoIntervento?: TipoIntervento,
    public intervSelvicolturale?: IntervSelvicolturale,
    public speciePfaIntervento?: SpeciePfaIntervento[]
  ) {}
}

export class TipoIntervento {
  constructor(
    public conformeDeroga?: string,
    public progressivoNumerico?: number,
    public fkStatoIntervento?: number,
    public dataPresuntaIntervento?: string,
    public annataSilvana?: string,
    public idParticelaForestale?: number[],
    public idEventoCorelato?: number,
    public fkGoverno?: number,
    public richiedePiedilsta?: number,
    public descrizione?: string,
    public localita?: string,
    public superficieInteressata?: number,
    public fkTipoIntervento?: number,
    public fasciaAltimetrica?: number,
    public fkFinalitaTaglio?: number,
    public fkDestLegname?: number
  ) {}
}
export class IntervSelvicolturale {
  constructor(
    public idIntervento?: number,
    public fkTipoIntervento?: number,
    public fkStatoIntervento?: number,
    public idgeoPlPfa?: number,
    public fkTipoForestalePrevalente?: number,
    public fkFinalitaTaglio?: number,
    public dataPresaInCarico?: string,
    public fkDestLegname?: number,
    public fkFasciaAltimetrica?: number,
    public flgIntervNonPrevisto?: number,
    public fkConfigIpla?: number,
    public annataSilvana?: string,
    public nrPiante?: number,
    public stimaMassaRetraibileM3?: number,
    public m3Prelevati?: number,
    public volumeRamagliaM3?: number,
    public nrProgressivoInterv?: number,
    public flgIstanzaTaglio?: number,
    public flgPiedilista?: number,
    public flgConformeDeroga?: number,
    public noteEsbosco?: string,
    public fkConfigUtente?: number,
    public fkGoverno?: number,
    public codEsbosco?: string,
    public idUsoViabilita?: number
  ) {}
}
export class SpeciePfaIntervento {
  constructor(
    public idSpecie?: number,
    public volumeSpecia?: number,
    public flgSpeciePriorita?: string,
    public fkUdm?: number
  ) {}
}
