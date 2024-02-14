/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.AmbitoDAO;
import it.csi.idf.idfapi.dto.AmbitoDto;
import it.csi.idf.idfapi.mapper.AmbitoDtoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class AmbitoDAOImpl implements AmbitoDAO {
	
	@Override
	public List<AmbitoDto> findAllAmbito() {
		String SQL = "SELECT id_ambito_istanza, descr_ambito_istanza, mtd_ordinamento, flg_visibile	FROM idf_d_ambito_istanza where flg_visibile=1 order by mtd_ordinamento ;";
		return DBUtil.jdbcTemplate.query(SQL, new AmbitoDtoMapper());	
	}
	
	
}
