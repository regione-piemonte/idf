/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.Priorita;
import it.csi.idf.idfapi.util.IplaEnum;

public interface PrioritaDAO {
	
	List<Priorita> findAllPriorita();
	
	public List<Priorita> findAllPrioritaByIpla(IplaEnum ipla);
}
