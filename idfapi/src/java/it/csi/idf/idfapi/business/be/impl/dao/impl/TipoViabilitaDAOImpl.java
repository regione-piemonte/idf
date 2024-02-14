/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.TipoViabilitaDAO;
import it.csi.idf.idfapi.dto.TipoViabilita;
import it.csi.idf.idfapi.mapper.TipoViabilitaRowMapper;
import it.csi.idf.idfapi.util.DBUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoViabilitaDAOImpl implements TipoViabilitaDAO {

	@Override
	public List<TipoViabilita> findAll() {
		String sql = "SELECT * FROM idf_d_tipo_viabilita where flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new TipoViabilitaRowMapper());
	}

	@Override
	public List<TipoViabilita> findAllByConfigIpla(Integer fkConfigIpla) {
		String sql = "SELECT * FROM idf_d_tipo_viabilita where flg_visibile = 1 and fk_config_ipla = ? order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new Object[] { fkConfigIpla }, new TipoViabilitaRowMapper());
	}
}
