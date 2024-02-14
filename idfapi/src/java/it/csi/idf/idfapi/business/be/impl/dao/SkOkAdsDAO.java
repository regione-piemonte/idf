/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import it.csi.idf.idfapi.util.DBUtil;

public interface SkOkAdsDAO {
		
	void insertFlgSez1(Long idgeoPtAds, int stepNum);
		
	void updateStepFinished(Long idgeoPtAds, int stepNumber);
	
	boolean isStepDone(Long idgeoPtAds, int stepNumber);
		
	int getLastStepDone(Long idgeoPtAds);
	
	String getWhereClause();
	
	public boolean skOdsExists(Long idgeoPtAds);
	
	void deleteSkOkAds(Long idgeoPtAds);
}
