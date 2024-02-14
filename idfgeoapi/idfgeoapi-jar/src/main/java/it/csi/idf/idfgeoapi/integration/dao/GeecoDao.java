/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao;

import it.csi.idf.idfgeoapi.dto.GeecoProfiloDto;
import it.csi.idf.idfgeoapi.exception.DaoException;

public interface GeecoDao {
	
	public GeecoProfiloDto getGeecoProfiloByIdLayer(String idLayer) throws DaoException;
	public void testResources() throws DaoException;
}
