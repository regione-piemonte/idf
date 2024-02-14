/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.ConfigUtente;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanza;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaTagli;
import it.csi.idf.idfapi.dto.PlainSoggettoIstanzaVincolo;
import it.csi.idf.idfapi.util.PaginatedList;

public interface PlainSoggettoIstanzaDAO {

	PaginatedList<PlainSoggettoIstanza> getAllIstances(ConfigUtente cnut, Integer tipoAccreditamento, int ambitoIstanza,
			int page, int limit, String sort);
	
	PaginatedList<PlainSoggettoIstanzaVincolo> getAllIstancesVincolo(ConfigUtente cnut, Integer tipoAccreditamento, int ambitoIstanza, 
			int tipoIstanzaId, int page, int limit, String sort);

	PaginatedList<PlainSoggettoIstanzaTagli> getAllIstancesTagli_old(ConfigUtente cnut, Integer tipoAccreditamento, int ambitoIstanza,
																	 int tipoIstanzaId, int page, int limit, String sort);

	PaginatedList<PlainSoggettoIstanzaTagli> getAllIstancesTagli(ConfigUtente configUtente, int page, int limit, String sort);

}
