/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.StatoInterventoDAO;
import it.csi.idf.idfapi.dto.StatoIntervento;
import it.csi.idf.idfapi.mapper.StatoInterventoMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class StatoInterventoDAOImpl implements StatoInterventoDAO {
	
	private final StatoInterventoMapper statoInterventoMapper = new StatoInterventoMapper();

	@Override
	public List<StatoIntervento> getStatiInterventi() {
		String sql = "SELECT id_stato_intervento, descr_stato_intervento, cod_stato_intervento " +
				"FROM idf_d_stato_intervento " +
				"WHERE flg_visibile = 1 " +
				"ORDER BY mtd_ordinamento";
		
		return DBUtil.jdbcTemplate.query(sql, statoInterventoMapper);
	}

	@Override
	public StatoIntervento getStatoByIdIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder(); 
		sql.append("select stato.* ");
		sql.append(" from idf_t_interv_selvicolturale intervSel  ");
		sql.append("LEFT JOIN idf_d_stato_intervento stato ON intervSel.fk_stato_intervento = stato.id_stato_intervento  ");
		sql.append("where intervSel.id_intervento = ?");
		return DBUtil.jdbcTemplate.queryForObject(sql.toString(), statoInterventoMapper, idIntervento);
	}
}
