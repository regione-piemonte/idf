/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.PrioritaDAO;
import it.csi.idf.idfapi.dto.Priorita;
import it.csi.idf.idfapi.mapper.PrioritaMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.IplaEnum;

@Component
public class PrioritaDAOImpl implements PrioritaDAO {

	@Override
	public List<Priorita> findAllPriorita() {
		
		String sql = "SELECT * FROM idf_d_priorita WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new PrioritaMapper());
		
	}
	
	@Override
	public List<Priorita> findAllPrioritaByIpla(IplaEnum ipla) {
		
		String sql = "SELECT * FROM idf_d_priorita where fk_config_ipla = ? and flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new PrioritaMapper(), ipla.getTypeValue());
		
	}

}
