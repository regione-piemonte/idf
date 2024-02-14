/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.RN2000DAO;
import it.csi.idf.idfapi.dto.RN2000;
import it.csi.idf.idfapi.mapper.RN2000Mapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class RN2000DAOImpl implements RN2000DAO {

	private final RN2000Mapper rn2000Mapper = new RN2000Mapper();

	@Override
	public List<RN2000> getByIdgeoPlPfa(int idgeoPlPfa) {
		String sql = "SELECT * FROM idf_r_pfa_rn_2000 WHERE idgeo_pl_pfa = ?";
		return DBUtil.jdbcTemplate.query(sql, rn2000Mapper, idgeoPlPfa);
	}
}
