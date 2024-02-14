/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class PlainSecondoSezione {
  particelleCatastali?: PlainParticelleCatastali[];
  totaleSuperficieCatastale?: number;
  totaleSuperficieTransf?: number;
  ricadenzaVincoloIdrogeologico?: string;
  ricadenzaAreeProtette?: RicadenzaInformazioni[];
  ricadenzaNatura2000?: RicadenzaInformazioni[];
  ricadenzaPopolamentiDaSeme?: RicadenzaInformazioni[];
  ricadenzaCategorieForestali?: RicadenzaInformazioni[];
  annotazioni?: string;
}

export class PlainParticelleCatastali {
  id?: number;
  istatComune?: string;
  sezione?: string;
  foglio?: number;
  particella?: string;
  supCatastale?: number;
  toDelete?: boolean;
  // comune: ComuneResource
}

export class RicadenzaInformazioni {
  codiceAmministrativo?: string;
  nome?: string;
  percentualeRicadenza: number;
}

export class ComuneResource {
  idComune?: number;
	istatComune?: string;
  istatProv?: string;
	denominazioneComune?: string;
}
