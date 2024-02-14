/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { DocumentoAllegato } from "src/app/shared/models/plain-sesto-sezione.model";

export class VincoloStep6 {
    flagProprieta: boolean;
	flagDissensi: boolean;
	flagImporto: boolean;
	flagCopiaConforme: boolean;
	flagConfServizi: boolean;
	flagSuap: boolean;
	annotazioni: string;

	flagSpeseIstruttoria: boolean;
	flagEsenzioneMarcaBollo: boolean;
	nMarcaBollo: boolean;
	
	documentiAllegati: DocumentoAllegato[]= [];
}