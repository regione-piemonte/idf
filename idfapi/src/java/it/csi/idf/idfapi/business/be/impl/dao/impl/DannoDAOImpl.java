/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DannoDAO;
import it.csi.idf.idfapi.dto.Danno;
import it.csi.idf.idfapi.mapper.DannoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DannoDAOImpl implements DannoDAO {

	@Override
	public List<Danno> findAllDanno() {

		DannoMapper mapper = new DannoMapper();
		String sql = "SELECT * FROM idf_d_danno WHERE flg_visibile = 1 order by mtd_ordinamento";
		List<Danno> danni = DBUtil.jdbcTemplate.query(sql, mapper);		
		
		return danni;
	}

}
