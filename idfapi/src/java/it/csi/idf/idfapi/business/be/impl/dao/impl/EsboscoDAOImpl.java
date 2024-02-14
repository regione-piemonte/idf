/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.EsboscoDAO;
import it.csi.idf.idfapi.dto.Esbosco;
import it.csi.idf.idfapi.mapper.EsboscoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class EsboscoDAOImpl implements EsboscoDAO {
	
	@Override
	public List<Esbosco> findAllEsbosco() {
		
		String SQL = "SELECT * FROM idf_d_esbosco WHERE flg_visibile = 1 order by mtd_ordinamento";
		
		return DBUtil.jdbcTemplate.query(SQL, new EsboscoMapper());	
	}

}
