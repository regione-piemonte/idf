/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

public interface EsboscoIntervselvDAO {

	int createIntervselvEsbosco(String codEsbosco, Integer idIntervento);
	
	void deleteIntervselvEsbosco(Integer idIntervento);
	
	void updateIntervselvEsbosco(String codEsbosco, Integer idIntervento);
	
	boolean isInterventoPresente(Integer idIntervento);

	List<String> findAllCodesByIntervento(Integer idIntervento);
	
}
