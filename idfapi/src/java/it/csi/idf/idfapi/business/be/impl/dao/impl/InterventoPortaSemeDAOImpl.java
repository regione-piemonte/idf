/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.InterventoPopSemeDAO;
import it.csi.idf.idfapi.business.be.impl.dao.InterventoPortaSemeDAO;
import it.csi.idf.idfapi.dto.InterventoPopSeme;
import it.csi.idf.idfapi.dto.InterventoPortaSeme;
import it.csi.idf.idfapi.mapper.InterventoPopSemeMapper;
import it.csi.idf.idfapi.mapper.InterventoPortaSemeMapper;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.idf.idfapi.util.InstanceStateEnum;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InterventoPortaSemeDAOImpl implements InterventoPortaSemeDAO {
	@Override
	public int insertInterventoPortaSeme(InterventoPortaSeme interventoPopSeme) {
		String sql = "INSERT INTO idf_r_intervento_portaseme(id_intervento, idgeo_pt_portaseme, data_inserimento, fk_config_utente) VALUES (?, ?, ?, ?)";

//		List<Object> parameters = new ArrayList<>();
//		parameters.add(interventoPopSeme.getIdIntervento());
//		parameters.add(interventoPopSeme.getIdPortaSeme());
//		parameters.add(Date.valueOf(LocalDate.now()));
//		parameters.add(interventoPopSeme.getFkConfigUtente());
		return DBUtil.jdbcTemplate.update(sql, interventoPopSeme.getIdIntervento(),interventoPopSeme.getIdPortaSeme(),Date.valueOf(LocalDate.now()),
				interventoPopSeme.getFkConfigUtente());
	}

	@Override
	public List<InterventoPortaSeme> getInterventosByIdIntervento(int idIntervento) {
		String sql = "SELECT * FROM idf_r_intervento_portaseme WHERE id_intervento = ?";
		return DBUtil.jdbcTemplate.query(sql, new InterventoPortaSemeMapper(), idIntervento);
	}

	@Override
	public void deleteInterventosByIdIntervento(int idIntervento) {
		String sql = "DELETE FROM idf_r_intervento_portaseme  WHERE id_intervento = ?";
		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}


}
