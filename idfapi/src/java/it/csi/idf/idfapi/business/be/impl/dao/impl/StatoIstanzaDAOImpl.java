/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.StatoIstanzaDAO;
import it.csi.idf.idfapi.dto.StatoIstanzaResource;
import it.csi.idf.idfapi.mapper.StatoIstanzaResourceMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.InstanceStateEnum;

@Component
public class StatoIstanzaDAOImpl implements StatoIstanzaDAO {

	private final StatoIstanzaResourceMapper statoMapper = new StatoIstanzaResourceMapper();
	
	@Override
	public List<StatoIstanzaResource> getAllVisibleStatesForBackEndSearch() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_stato_istanza, descr_stato_istanza");
		sql.append(" FROM idf_d_stato_istanza");
		sql.append(" WHERE flg_visibile = 1 AND id_stato_istanza <> ");
		sql.append(InstanceStateEnum.BOZZA.getStateValue());
		sql.append(" ORDER BY mtd_ordinamento");
		
		return DBUtil.jdbcTemplate.query(sql.toString(), statoMapper);
	}

	@Override
	public List<StatoIstanzaResource> getAllVisibleStatesForSportelloSearch() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_stato_istanza, descr_stato_istanza");
		sql.append(" FROM idf_d_stato_istanza");
		sql.append(" WHERE flg_visibile = 1 ");
		sql.append(" ORDER BY mtd_ordinamento");

		return DBUtil.jdbcTemplate.query(sql.toString(), statoMapper);
	}
}
