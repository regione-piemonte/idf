/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.EventoPartforDAO;
import it.csi.idf.idfapi.dto.EventoDTO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class EventoPartforDAOImpl implements EventoPartforDAO {

	@Override
	public int createEventoPartfor(EventoDTO eventoDto, Integer idEvento) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_r_evento_partfor");
		sql.append("(id_evento, idgeo_pl_particella_forest, perc_danno) ");
		sql.append("VALUES (?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(idEvento);
		parameters.add(eventoDto.getIdgeoPlParticelaForest());
		parameters.add(eventoDto.getPercDanno());
		
		return DBUtil.jdbcTemplate.update(sql.toString(),parameters.toArray());
	}

	
	@Override
	public void deleteEventoPartfor(Integer idEvento) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM idf_r_evento_partfor ");
		sql.append(" WHERE id_evento = ?; ");
		
		DBUtil.jdbcTemplate.update(sql.toString(),idEvento);
	}


	@Override
	public void createEventoPartfor(Integer idEvento, Integer idGeoPlParticellaForest) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_r_evento_partfor");
		sql.append("(id_evento, idgeo_pl_particella_forest, perc_danno) ");
		sql.append("VALUES (?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(idEvento);
		parameters.add(idGeoPlParticellaForest);
		parameters.add(null);
		
		DBUtil.jdbcTemplate.update(sql.toString(),parameters.toArray());
	}

	@Override
	public void updateEventoPartfor(Integer idEvento, Integer percDanno) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE idf_r_evento_partfor SET perc_danno = ?");
		sql.append(" WHERE id_evento = ? ");
		
		Object[] parameters = new Object[]{percDanno,idEvento};		
		DBUtil.jdbcTemplate.update(sql.toString(),parameters);
	}
	
}
