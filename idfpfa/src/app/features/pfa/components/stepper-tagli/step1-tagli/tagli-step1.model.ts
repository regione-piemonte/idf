/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */

import { Proprieta } from './../../../../../shared/models/proprieta.model';
import { Trasformazione } from 'src/app/shared/models/trasformazione.model';
import { CategoriaSelvicolturale } from './../../../../../shared/models/categoria-selvicolturale.model';

export class TagliStep1 {
	idIntervento?: number;
	fkTipoIntervento?: number;
	fkGoverno?: number ;
	fkStatoIntervento?: number = 1;
	dataPresaInCarico?: string;
	annataSilvana?: string;
	tipoIstanzaId?: number;
	flgConformeDeroga?:string;

	proprieta?: Proprieta;
	trasformazSelvicolturale?: Trasformazione;
	categoriaSelvicolturale?: CategoriaSelvicolturale;
  nrProgressivoInterv?:number;
  idEvento?:number


	// fkInterventoCompensativo?: number;
	// fkCategIntervento?: number;
	// fkProprieta?: number
}
