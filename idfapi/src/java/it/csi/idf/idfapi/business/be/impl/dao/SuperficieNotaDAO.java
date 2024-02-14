/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliTwo;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.dto.Superficie;
import it.csi.idf.idfapi.util.DBUtil;

public interface SuperficieNotaDAO {
	
	void saveSuperficie(AdsDatiStazionaliOne adsDatiStazionaliOne);
	
	int insertSuperficieDatiStazionaliOne(AdsDatiStazionaliOne adsDatiStazionaliOne); 
	
	int updateSuperficieDatiStazionaliTwo(AdsDatiStazionaliTwo adsDatiStazionaliTwo);
	
	void updateSuperficieDatiStazionaliOne(AdsDatiStazionaliOne adsDatiStazionaliOne);
	
	boolean superficiaNotaExists(Long idgeoPtAds);
	
	Superficie getSuperficieNotaByIdGeoPtAds(Long idGeoPtAds);
	
	void deleteSuperficieNota(Long idgeoPtAds);
	
}
