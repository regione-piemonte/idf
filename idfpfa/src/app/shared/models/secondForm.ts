/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class SecondForm {
  particelleCatastali: ParticelleCatastali[];
  totaleSuperficieCatastale: number;
  totaleSuperficieTrasf: number;
  ricadenzaAreeProtette: Ricadenza[];
  ricadenzaNatura2000: Ricadenza[];
  ricadenzaPopolamentiDaSeme: Ricadenza[];
  ricadenzaCategorieForestali: Ricadenza[];
  ricadenzaVincoloIdrogeologico: boolean;
  annotazioni: string;
}

export class ParticelleCatastali {
  id: number;
  comune: Comune;
  sezione: string;
  foglio: number;
  particella: string;
  supCatastale: number;
  toDelete: boolean;
}

export class Comune {
  idComune: number;
  istatComune: string;
  istatProv: string;
  denominazioneComune: string;
}

export class Ricadenza {
  codiceAmministrativo: string;
  nome: string;
  percentualeRicadenza: number;
}
