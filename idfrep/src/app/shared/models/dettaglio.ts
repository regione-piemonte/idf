/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
export class Content<T> {
  content?: T;
  totalElements: number;
  limit: number;
  page: number;
}

export class Dettaglio {
  idGeoPlPfa: number;
  idComuni: number[];
  istatProv: string[];
  denominazione?: string;
  geometria?: Geometria;
  denominazioneComuni: string;
  denominazioneProvincie: string;
  dataInizioValidita: string;
  dataFineValidita: string;
  dataApprovazione: string;
}

export class Chronology {
  id?: string;
  calendarType?: string;
}

export class Data {
  year?: number;
  month?: string;
  era?: string;
  dayOfMonth?: number;
  dayOfWeek?: string;
  dayOfYear?: number;
  leapYear?: boolean;
  monthValue?: number;
  chronology?: Chronology;
}

export class Geometria {
  type?: string;
  value?: string;
}

export class Detail {
  idGeoPlPfa: number;
  denominazione: string;
  dataInizioValidita: string;
  dataFineValidita: string;
  dataRevisione: string;
  dataApprovazione: string;
  geometria: Geometria;
  flgRevisione: number;
  propNonForestaleHa: number;
  supPianifNonForestaleHa: number;
  proprietaSilvopastHa: number;
  proprietaForestaleHa: number;
  superfBocsGestAttivaHa: number;
  supPianifForestaleHa: number;
  superfGestNonAttivaMonHa: number;
  superfGestNonAttivaTotHa: number;
  superfGestNonAttivaEvlHa: number;
  gestori: string[];
  denominazioneComuni: string;
  idComuni: number[];
  denominazioneProvincie: string;
  istatProvincie: string[];
  descrPropriete: string;
  ricadenzeAree: Ricadenze[];
  ricadenzeRn2000: Ricadenze[];
  ricadenzePopseme: Ricadenze[];
}

export class Ricadenze {
  nome: string;
  supHa: number;
  percRicadenza: number;
}

// export class Dettaglio {
//   idGeoPlPfa?: number;
//   idComune?: number;
//   idPopolamento?: number;
//   istatProv?: string;
//   denominazione?: string;
//   denominazioneComune?: string;
//   denominazioneProv?: string;
//   fonteFinanziamento?: string;
//   nomeSoggeto?: string;
//   cognomeSoggeto?: string;
//   gestore?: string;
//   geometria?: Geometria;
//   dataInizioValidita?: Data;
//   dataFineValidita?: Data;
//   dataRevisione?: Data;
//   dataApprovazione?: Data;
//   dataInizioValiditaString?: string;
//   dataFineValiditaString?: string;
//   dataRevisioneString?: string;
//   dataApprovazioneString?: string;
//   proprietaSilvopastHa?: number;
//   proprietaForestaleHa?: number;
//   superfBocsGestAttivaHa?: number;
//   superfGestNonAttivaTotHa?: number;
//   superfGestNonAttivaMonHa?: number;
//   superfGestNonAttivaEvlHa?: number;
//   propNonForestaleHa?: number;
//   supPianifNonForestaleHa?: number;
//   supPianifForestaleHa?: number;
//   nomeAreaProtetta?: string;
//   supRicadenzaHa?: number;
//   descrProprieta?: string;
//   nomeRN2000?: string;
//   supRicadenzaHaRN2000?: number;
//   supRicadenzaHaSeme?: number;
//   specieIdonee?: string;
// }

// export class Content<T> {
//   content?: T;
//   totalElements?: number;
//   limit?: number;
//   page?: number;
// }

// export class Data {
//   year?: number;
//   month?: string;
//   era?: string;
//   dayOfMonth?: number;
//   dayOfWeek?: string;
//   dayOfYear?: number;
//   leapYear?: boolean;
//   monthValue?: number;
//   chronology?: Chronology;
// }

// export class Chronology {
//   id?: string;
//   calendarType?: string;
// }

// export class Geometria {
//   type?: string;
//   value?: string;
// }
