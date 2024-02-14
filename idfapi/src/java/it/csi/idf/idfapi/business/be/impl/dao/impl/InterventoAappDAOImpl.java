/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.InterventoAappDAO;
import it.csi.idf.idfapi.dto.InterventoAapp;
import it.csi.idf.idfapi.mapper.InterventoAappMapper;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class InterventoAappDAOImpl implements InterventoAappDAO {

	@Override
	public int insertInterventoAapp(InterventoAapp interventoAapp) {
		String sql = "INSERT INTO idf_r_intervento_aapp(id_intervento, codice_aapp) VALUES (?, ?)";
		return DBUtil.jdbcTemplate.update(sql, interventoAapp.getIdIntervento(), interventoAapp.getCodiceAapp());
	}

	@Override
	public List<InterventoAapp> getInterventosByIdIntervento(int idIntervento) {
		String sql = "SELECT id_intervento, codice_aapp FROM idf_r_intervento_aapp WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new InterventoAappMapper(), idIntervento);
	}

	@Override
	public void deleteAllInterventoAappsByIdIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_r_intervento_aapp WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

	@Override
	public void deleteAllInterventoPPRByIdIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_r_intervento_ppr WHERE id_intervento = ?";
		int cout= DBUtil.jdbcTemplate.update(sql, idIntervento);
		int countInsrt=cout++;
       System.out.println("Se han eliminado " + countInsrt + " registros.");
	}
}
