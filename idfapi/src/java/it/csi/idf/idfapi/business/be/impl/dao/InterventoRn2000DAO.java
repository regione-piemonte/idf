/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.InterventoRn2000;

public interface InterventoRn2000DAO {
	int insertInterventoRn2000(InterventoRn2000 interventoRn2000);
	List<InterventoRn2000> getInterventosByIdIntervento(int idIntervento);
	void deleteInterventosByIdIntervento(int idIntervento);
}
