/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AreaProtettaDAO;
import it.csi.idf.idfapi.dto.AreaProtetta;
import it.csi.idf.idfapi.mapper.AreaProtettaMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class AreaProtettaDAOImpl implements AreaProtettaDAO {
	
	private final AreaProtettaMapper areaProtettaMapper = new AreaProtettaMapper();

	@Override
	public List<AreaProtetta> getByIdgeoPlPfa(int idgeoPlPfa) {
		String sql = "SELECT * FROM idf_r_pfa_area_protetta WHERE idgeo_pl_pfa = ?";
		return DBUtil.jdbcTemplate.query(sql, areaProtettaMapper, idgeoPlPfa);
		
	}
}
