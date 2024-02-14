/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GovernoDAO;
import it.csi.idf.idfapi.dto.Governo;
import it.csi.idf.idfapi.mapper.GovernoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GovernoDAOImpl implements GovernoDAO {
	
	private final GovernoMapper governoMapper = new GovernoMapper();

	@Override
	public List<Governo> findAllGoverni() {

		String sql= "SELECT * FROM idf_d_governo WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";

		return DBUtil.jdbcTemplate.query(sql, governoMapper);
	}
}
