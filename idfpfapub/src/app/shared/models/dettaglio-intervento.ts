/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { PropCatastos } from "./data-taglio";

export class TipoInterventoDettaglio {
  conformeDeroga?: string;
  annataSilvana?: string;
  idParticelaForestale?: string;
  dataPresuntaIntervento?: string;
  dataInizioIntervento?: string;
  dataFineIntervento?: string;
  governo?: string;
  tipoIntervento?: string;
  richiedePiedilsta?: string;
  descrizione?: string;
  localita?: string;
  superficieInteressata?: number;
  statoIntervento?: string;
  numeroPiante?: number;
  m3Prelevati?: number;
  fasciaAltimetrica?: string;
  finalitaTaglio?: string;
  destLegname?: string;

}
export class PropAndRicandeza {
    propCatastos: PropCatastos[];
    ricadenzaInfo?:RicadenzaInfo;
}
export class RicadenzaInfo {
  areeProtette?: string;
  reteNatura2000?: any;
  popolamentoSeme?: string;
  categoriaForestali?: string;
}
export class SpeciesAndTaglio {
    species: SpecieInterventoVolumes[];
    dettagliDitaglio: DettagliDitaglio; 
}

export class SpecieInterventoVolumes {
  volgare?: string;
  volumeSpecie?: number;
  priorita?: string;
  udm?: string;
}

export class DettagliDitaglio {
  flgConformeDeroga?:string;
  numeroPiante?: number;
  volumeRamaglia?: number;
  stimaMassaRetraibileM3?: number;
  usoViabilita?: string;
  esbosco?: string;
  noteEsbosco?: string;
  tipoIstanzaTrasmessa?: string;
  dataTrasmissione?: string;
  numeroIstanza?: number;
}
