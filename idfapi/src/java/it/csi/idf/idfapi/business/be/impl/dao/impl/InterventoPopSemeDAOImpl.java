/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.InterventoPopSemeDAO;
import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.mapper.InterventoPopSemeMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class InterventoPopSemeDAOImpl implements InterventoPopSemeDAO {

	@Override
	public int insertInterventoPopSeme(InterventoPopSeme interventoPopSeme) {
		String sql = "INSERT INTO idf_r_intervento_pop_seme(id_intervento, id_popolamento_seme) VALUES (?, ?)";
		return DBUtil.jdbcTemplate.update(sql, interventoPopSeme.getIdIntervento(), interventoPopSeme.getIdPopolamentoSeme());
	}

	@Override
	public List<InterventoPopSeme> getInterventosByIdIntervento(int idIntervento) {
		String sql = "SELECT id_intervento, id_popolamento_seme FROM idf_r_intervento_pop_seme WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new InterventoPopSemeMapper(), idIntervento);
	}

	@Override
	public void deleteInterventosByIdIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_r_intervento_pop_seme WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}
}
