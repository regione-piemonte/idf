/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

public interface GeoPlEventoDAO {
	
	void insertGeoPlEvento(int fkEvento, Object geometria);

	void deleteGeoPlEvento(int idEvento);
	
	void updateSuperficieHaByEvento(int idEvento);
}
