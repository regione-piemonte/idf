/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.FinalitaTaglioDAO;
import it.csi.idf.idfapi.dto.FinalitaTaglio;
import it.csi.idf.idfapi.mapper.FinalitaTaglioMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class FinalitaTaglioDAOImpl implements FinalitaTaglioDAO {
	
	private final FinalitaTaglioMapper finalitaTaglioMapper = new FinalitaTaglioMapper();

	@Override
	public List<FinalitaTaglio> findAllFinalitaTaglio() {

		String sql = "SELECT * FROM idf_d_finalita_taglio WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";
		
		return DBUtil.jdbcTemplate.query(sql, finalitaTaglioMapper);
	}
}
