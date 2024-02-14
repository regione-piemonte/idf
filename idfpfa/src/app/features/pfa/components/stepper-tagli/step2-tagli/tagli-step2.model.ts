/*!
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
 */
import { UserCompanyDataModel } from 'src/app/shared/models/user-company-data.model';

export class TagliStep2 {

	idIntervento?: number;
	tipoIstanzaId?: number;
	tipoIstanzaDescr?: string;

	tipoAccreditamento?: string;

	tipoRichiedenteId?: number;
	soggetto?: UserCompanyDataModel;

	tipoUtilizzatore?: string;
	utilizzatore?: UserCompanyDataModel;

	gestoreId?: number



}
