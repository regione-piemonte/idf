/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.UriInfo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.transaction.annotation.Transactional;

import it.csi.idf.idfapi.dto.EventoPiano;
import it.csi.idf.idfapi.dto.ExcelDTO;
import it.csi.idf.idfapi.dto.GeoPfaSearch;
import it.csi.idf.idfapi.dto.GeoPfaSearchDettaglio;
import it.csi.idf.idfapi.dto.InterventoPiano;
import it.csi.idf.idfapi.util.PaginatedList;

@Transactional(rollbackFor=Exception.class)
public interface WrapperPlPfaDAO {
	
	public PaginatedList<GeoPfaSearch> getPublicPianiForestaliSearch(Map<String, String> queryParams);

	public GeoPfaSearchDettaglio getPublicPfaSearchByID(Integer idGeoPlPfa, Integer idSoggetto);
	
	public byte[] generateExcel(ExcelDTO excel, UriInfo uriInfo);
	
	public void createSheetInterventi(HSSFWorkbook hwb, CellStyle headerCellStyle, List<InterventoPiano> interventoList);
	
	public void createSheetEventi(HSSFWorkbook hwb, CellStyle headerCellStyle, List<EventoPiano> eventoList);
	
	public CellStyle getCellStyle(HSSFWorkbook hwb);
}
