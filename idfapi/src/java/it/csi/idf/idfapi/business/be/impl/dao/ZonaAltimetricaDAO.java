/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

public interface ZonaAltimetricaDAO {
	Integer getMaxOccurencesOfFkParamtrasf(List<String[]> sezioniFogli);
	
	Integer getZonaAltimentricaByIdIntervento(Integer idIntervento);
}
