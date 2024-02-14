/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.InterventoRn2000DAO;
import it.csi.idf.idfapi.dto.InterventoRn2000;
import it.csi.idf.idfapi.mapper.InterventoRn2000Mapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class InterventoRn2000DAOImpl implements InterventoRn2000DAO {

	@Override
	public int insertInterventoRn2000(InterventoRn2000 interventoRn2000) {
		String sql = "INSERT INTO idf_r_intervento_rn_2000(id_intervento, codice_rn_2000) VALUES (?, ?)";
		return DBUtil.jdbcTemplate.update(sql, interventoRn2000.getIdIntervento(), interventoRn2000.getCodiceRn2000());
	}

	@Override
	public List<InterventoRn2000> getInterventosByIdIntervento(int idIntervento) {
		String sql = "SELECT id_intervento, codice_rn_2000 FROM idf_r_intervento_rn_2000 WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new InterventoRn2000Mapper(), idIntervento);
	}

	@Override
	public void deleteInterventosByIdIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_r_intervento_rn_2000 WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}
}
