/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.DestLegnameDAO;
import it.csi.idf.idfapi.dto.DestLegname;
import it.csi.idf.idfapi.mapper.DestLegnameMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class DestLegnameDAOImpl implements DestLegnameDAO {
	
	private final DestLegnameMapper destLegnameMapper = new DestLegnameMapper();

	@Override
	public List<DestLegname> findAllDestLegname() {
		String sql = "SELECT * FROM idf_d_dest_legname WHERE flg_visibile = 1 ORDER BY mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, destLegnameMapper);
	}
}
