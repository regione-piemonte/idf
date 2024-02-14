/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SpeciePFA } from "src/app/shared/models/specie-pfa-model";
import { TipoIntervento } from 'src/app/shared/models/tipo-intervento';
import { Governo } from 'src/app/shared/models/governo.model';

export interface TagliStep4 {
  intervPfaFat?: IntervPfaFat;
  intervento?:           Intervento;

  headings?:             TagliHeading[];
  specieInteressate?:    SpeciePFA[];
}

export interface TagliHeading {
    id?:             number;
    name?:           string;
    mtdOrdinamento?: number;
    visibility?:     boolean;
    subheadings?:    TagliSubheading[];
    checked?:        boolean;
}

export interface TagliSubheading {
    id?:          number;
    codice?:      string;
    descrizione?: string;
    ordinamento?: number;
    visibility?:  boolean;
    checked?:     boolean;
    valore?:      number;
}

export interface IntervPfaFat {
  tipoInterventoPrincipale?:  TipoIntervento;
  governoPrincipale?:         Governo;
  tipoInterventoSecondario?:  TipoIntervento;
  governoSecondario?:         Governo;

  idIntervento?:              number;
  fkTipoIntervento?:          number;
  fkStatoIntervento?:         number;
  idgeoPlPfa?:                number;
  fkTipoForestalePrevalente?: number;
  fkFinalitaTaglio?:          number;
  fkDestLegname?:             number;
  fkFasciaAltimetrica?:       number;
  flgIntervNonPrevisto?:      number;
  fkConfigIpla?:              number;
  nrPiante?:                  number;
  stimaMassaRetraibileM3?:    number;
  m3Prelevati?:               number;
  volumeRamagliaM3?:          number;
  dataPresaInCarico?:         Date;
  annataSilvana?:             string;
  nrProgressivoInterv?:       number;
  flgIstanzaTaglio?:          number;
  flgPiedilista?:             number;
  flgConformeDeroga?:         string;
  noteEsbosco?:               string;
  dataInserimento?:           Date;
  dataAggiornamento?:         Date;
  fkConfigUtente?:            number;
  ripresaPrevistaMc?:         number;
  ripresaRealeFineIntervMc?:  number;
  fkGoverno?:                 number;
  codEsbosco?:                string;
  idUsoViabilita?:            number;
  fkCategoriaIntervento?:     number;
  fkProprieta?:               number;
  fkTipoIntervento2?:         number;
  fkGoverno2?:                number;
  superficieInt1Ha?:          number;
  superficieInt2Ha?:          number;
}

export interface Intervento {
  idIntervento?:              number;
  superficieInteressata?:     number;
  localita?:                  string;
  descrizioneIntervento?:     string;
  fasciaAltimetrica?:         number;
  fkSoggettoProfess?:         number;
  dataInizioIntervento?:      Date;
  dataFineIntervento?:        Date;
  dataInserimento?:           Date;
  dataAggiornamento?:         Date;
  fkConfigUtenteCompilatore?: number;
}
