/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.business.be.exceptions.ValidationException;

public interface IntervselEventoDAO {

	int createIntervselEvento(Integer idEvento, Integer idIntervento);

	void deleteIntervselEvento(Integer idIntervento);

	void updateIntervselEvento(Integer idEvento, Integer idIntervento);

	int createIntervselEventoNEW(Integer idEvento, Integer idIntervento, Integer idConfigUtente)
			throws ValidationException;
	
	boolean exitsInterventoEvento(Integer idIntervento);

}
