/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.service;

import java.util.List;

import it.csi.idf.idfapi.dto.TSoggetto;

public interface AAEP {
	
	List<TSoggetto> getCompaniesForRichiedenteLegale();
}
