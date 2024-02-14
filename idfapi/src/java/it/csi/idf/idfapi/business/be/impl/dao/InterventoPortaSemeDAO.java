/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.InterventoPortaSeme;

import java.util.List;

public interface InterventoPortaSemeDAO {

	int insertInterventoPortaSeme(InterventoPortaSeme interventoPopSeme);

	List<InterventoPortaSeme> getInterventosByIdIntervento(int idIntervento);

	void deleteInterventosByIdIntervento(int idIntervento);
}
