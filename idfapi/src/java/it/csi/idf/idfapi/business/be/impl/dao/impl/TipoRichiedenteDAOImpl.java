/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.TipoRichiedenteDAO;
import it.csi.idf.idfapi.dto.TipoRichiedente;
import it.csi.idf.idfapi.mapper.TipoRichiedenteMapper;
import it.csi.idf.idfapi.util.DBUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TipoRichiedenteDAOImpl implements TipoRichiedenteDAO {

	@Override
	public TipoRichiedente getTipoRichiedenteById(int idTipoRichiedente) {
		String sql = "SELECT * FROM idf_d_tipo_richiedente tr WHERE tr.id_tipo_richiedente = ? and flg_visibile = 1";
		return DBUtil.jdbcTemplate.queryForObject(sql, new TipoRichiedenteMapper(), idTipoRichiedente);
	}

	@Override
	public List<TipoRichiedente> getTipoAll() {
		String sql = "SELECT * FROM idf_d_tipo_richiedente tr WHERE flg_visibile = 1 order by mtd_ordinamento";
		return DBUtil.jdbcTemplate.query(sql, new TipoRichiedenteMapper());
	}
}
