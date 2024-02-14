/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { SpecieFinalita } from './specie-finalita.model';
import { Specie } from 'src/app/shared/models/specie.model';


export interface SpeciePFA {
  idSpecie?:          number;
  idIntervento?:      number;
  flgSpeciePriorita?: string;
  volumeSpecia?:      number;
  dataInserimento?:   string;
  dataAggiornamento?: string;
  fkUdm?:             number;
  numPiante?:         number;
  specieFinalita?:    SpecieFinalita[];
  specie?:            Specie;

}

