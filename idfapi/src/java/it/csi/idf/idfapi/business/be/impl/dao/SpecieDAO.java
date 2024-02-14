/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.GruppoSpecie;
import it.csi.idf.idfapi.dto.TSpecie;
import it.csi.idf.idfapi.util.IplaEnum;

public interface SpecieDAO {
	
	List<TSpecie> findAllSpecie();
	
	public List<TSpecie> findAllSpecieByIpla(IplaEnum ipla);
	
	List<GruppoSpecie> findAllGruppoSpecie();

	TSpecie getSpecie(Integer idSpecie);
}
