/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { CheckboxAndRadio, Heading } from "src/app/shared/models/checkbox-and-radio";

export class VincoloStep3 {
    descrizioneIntervento?: string;
	headings?: Heading[];
    tipoIntervento?: number;
	altroTipoIntervento?: string;
    //totaliSuperfici?: number;
	totaleTotMovimentiTerra: number;
	totaleTotMovimentiTerraVincolo: number;
	tempoPrevisto: number;
	presenzaAreeDissesto: boolean;
	presenzaAreeEsondazione: boolean;
}