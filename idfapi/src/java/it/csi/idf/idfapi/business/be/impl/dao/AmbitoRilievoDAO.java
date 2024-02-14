/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.AmbitoRilievo;

public interface AmbitoRilievoDAO {
	
	List<AmbitoRilievo> findAllAmbitoRilievo();
	
	List<AmbitoRilievo> findAmbitoRilievoSpecificare();
	
	AmbitoRilievo saveNewAmbito(AmbitoRilievo ambito);
	
	List<AmbitoRilievo> findChildAmbitos(int fkPadre);
	
	List<AmbitoRilievo> findAmbitoByPadreAndDescr(int fkPadre,String desc);

}
