/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;

import it.csi.idf.idfapi.dto.CampoReport;
import it.csi.idf.idfapi.dto.TipoReport;

public interface ReportByCsvDAO {
	
	List<TipoReport> getReportList(int adsType); 
	List<TipoReport> getAdsTypeList();
	List<CampoReport> getReportDetail(Integer idReport);
}
