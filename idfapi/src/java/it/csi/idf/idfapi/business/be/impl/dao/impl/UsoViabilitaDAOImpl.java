/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.UsoViabilitaDAO;
import it.csi.idf.idfapi.dto.UsoViabilita;
import it.csi.idf.idfapi.mapper.UsoViabilitaRowMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class UsoViabilitaDAOImpl implements UsoViabilitaDAO {

	@Override
	public List<UsoViabilita> findAllUsoViabilita() {

		String sql = "SELECT * FROM idf_d_uso_viabilita where flg_visibile = 1 order by mtd_ordinamento";
		
		return DBUtil.jdbcTemplate.query(sql, new UsoViabilitaRowMapper());
	}

}
