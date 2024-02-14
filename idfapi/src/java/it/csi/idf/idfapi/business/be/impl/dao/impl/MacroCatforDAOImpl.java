/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.MacroCatforDAO;
import it.csi.idf.idfapi.dto.MacroCatfor;
import it.csi.idf.idfapi.mapper.MacroCatforMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class MacroCatforDAOImpl implements MacroCatforDAO {

	@Override
	public MacroCatfor getMacroCatforById(int idParamMacroCatfor) {
		String sql = "SELECT * FROM idf_d_macro_catfor WHERE id_param_macro_catfor = ?";
		
		return DBUtil.jdbcTemplate.queryForObject(sql, new MacroCatforMapper(), idParamMacroCatfor);
	}
}
