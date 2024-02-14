/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.TipoStrutturaleDAO;
import it.csi.idf.idfapi.dto.TipoStrutturale;
import it.csi.idf.idfapi.mapper.TipoStrutturaleMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.IplaEnum;
@Component
public class TipoStrutturaleDAOImpl implements TipoStrutturaleDAO {
	
	@Override
	public List<TipoStrutturale> findAllTipoStrutturale() {
		String SQL = "SELECT * FROM idf_d_tipo_strutturale WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new TipoStrutturaleMapper());	
	}
	
	@Override
	public List<TipoStrutturale> findAllTipoStrutturaleByIpla(IplaEnum ipla) {
		String SQL = "SELECT * FROM idf_d_tipo_strutturale where fk_config_ipla = ? "
				+ "and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(SQL, new TipoStrutturaleMapper(),ipla.getTypeValue());	
	}


}
