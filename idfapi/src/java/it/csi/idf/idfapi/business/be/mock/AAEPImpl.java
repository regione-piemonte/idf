/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.mock;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.service.AAEP;
import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.mapper.TSoggettoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class AAEPImpl implements AAEP {
	
	public List<TSoggetto> getCompaniesForRichiedenteLegale() {
		String sql = "SELECT * FROM idf_t_soggetto WHERE denominazione <> '' AND codice_fiscale <> '' AND partita_iva <> ''";
		return DBUtil.jdbcTemplate.query(sql, new TSoggettoMapper());	
	}
}
