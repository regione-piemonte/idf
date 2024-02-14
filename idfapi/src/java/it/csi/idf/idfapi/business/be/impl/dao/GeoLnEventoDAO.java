/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

public interface GeoLnEventoDAO {

	void insertGeoLnEvento(int fkEvento, Object geometria);

	void deleteGeoLnEvento(int idEvento);
}
