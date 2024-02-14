/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { ParticelleCatastali } from "src/app/shared/models/particelleCatastali.model";
import { Ricadenza } from "src/app/shared/models/ricadenza.model";
import { RicadenzaPortaseme } from "src/app/shared/models/ricadenzaPortaseme.model";
import { FasciaAltimetrica } from '../../../../../shared/models/fascia-altimetrica.model';
import { RicadenzaIntervento } from "src/app/shared/models/ricadenzaIntervento.model";

export interface TagliStep3 {
  idIntervento?:                        number;
  particelleCatastali?:                 ParticelleCatastali[];
  totaleSuperficieCatastale?:           string;
  totaleSuperficieTrasf?:               string;
  totaleSuperficieIntervento?:               string;

  ricadenzaAreeProtette?:               Ricadenza[];
  ricadenzaNatura2000?:                 Ricadenza[];
  ricadenzaPopolamentiDaSeme?:          Ricadenza[];
  ricadenzaCategorieForestali?:         Ricadenza[];
  annotazioni?:                         string;

  ricadenzaPortaSeme?:                  RicadenzaPortaseme[];
  ricadenzaPfa?:                        boolean;


  ricadenzaVincoloIdrogeologico?:       boolean;
  totaleSuperficieBoscata?:             number;
  totaleSuperficieNonBoscata?:          number;
  totaleSuperficieInVincolo?:           number;
  totaleSuperficieBoscataInVincolo?:    number;
  totaleSuperficieNonBoscataInVincolo?: number;
  ricadenzaIntervento?:                 RicadenzaIntervento[];  

  fasciaAltimetrica?:                   FasciaAltimetrica;
}






