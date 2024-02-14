/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;

public interface RelascopicaSempliceDAO {
	
	int saveRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice);
	
	public int updateRelascopica(PlainRelascopicaSemplice plainRelascopicaSemplice);
	
	PlainRelascopicaSemplice getPlainRelascopicaSemplice(String codiceAds);
	
	boolean relascopicaExists(Long codiceAds);
	
	void deleteRelascopica(Long idgeoPtAds); 
}
