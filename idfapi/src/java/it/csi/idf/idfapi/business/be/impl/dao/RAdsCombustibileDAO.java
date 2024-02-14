/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;

public interface RAdsCombustibileDAO {
	void saveCombustibile(AdsDatiStazionaliOne adsDatiStazionaliOne);
	
	void deleteCompustibile(Long idgeoPtAds);
}
