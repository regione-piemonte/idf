/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.InterventoCatfor;

public interface InterventoCatforDAO {
	int insertInterventoCatfor(InterventoCatfor interventoCatfor);
	void deleteInterventosById(int idIntervento);
	List<InterventoCatfor> getInterventosByIdIntervento(int idIntervento);
	void deleteByIdCategoriaAndIdIntervento(int idIntervento, int codiceAmministratico);
}
