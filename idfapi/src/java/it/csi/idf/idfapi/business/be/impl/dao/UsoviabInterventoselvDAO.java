/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.SpecieInterventoFinalita;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface UsoviabInterventoselvDAO {

	int createIntervselUsovib(Integer fkUsoViabilita, Integer idIntervento);
	
	void deleteIntervselUsovib(Integer idIntervento);
	
	void updateIntervselUsovib(Integer fkUsoViabilita, Integer idIntervento);
	
	boolean isInterventoPresente(Integer idIntervento);


	List<Integer> getAllByIntervento(Integer idIntervento);
}
