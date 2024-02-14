/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AssettoDAO;
import it.csi.idf.idfapi.dto.Assetto;
import it.csi.idf.idfapi.mapper.AssettoMapper;
import it.csi.idf.idfapi.util.DBUtil;


@Component
public class AssettoDAOImpl implements AssettoDAO {
	
	@Override
	public List<Assetto> findAllAssetto() {
		String SQL = "SELECT * FROM idf_d_assetto WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new AssettoMapper());	
	}

}
