/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.CombustibileDAO;
import it.csi.idf.idfapi.dto.Combustibile;
import it.csi.idf.idfapi.mapper.CombustibileMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class CombustibileDAOImpl implements CombustibileDAO {

	String tableName = "idf_r_ads_combustibile";
	String pkColumnName = "idgeo_pt_ads";
	
	@Override
	public Combustibile getSuperficieNotaByIdGeoPtAds(Long idGeoPtAds) {
		
		return DBUtil.jdbcTemplate.queryForObject("Select * from " + tableName + " WHERE "+pkColumnName+ " = " +idGeoPtAds, new CombustibileMapper());
		
	}
	
	

}
