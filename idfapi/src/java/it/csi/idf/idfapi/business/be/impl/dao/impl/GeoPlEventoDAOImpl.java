/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPlEventoDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoPlEventoDAOImpl implements GeoPlEventoDAO {

	@Override
	public void insertGeoPlEvento(int fkEvento, Object geometria) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_geo_pl_evento(");
		sql.append("data_inserimento, geometria, fk_evento)");
		sql.append(" VALUES (?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(null); //geometria mocked
		parameters.add(fkEvento);

		DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	@Override
	public void deleteGeoPlEvento(int idEvento) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM idf_geo_pl_evento");
		sql.append(" WHERE fk_evento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idEvento);
	}

	@Override
	public void updateSuperficieHaByEvento(int idEvento) {
		StringBuilder sql = new StringBuilder();
		sql.append("update idf_geo_pl_evento ");
		sql.append( "set superficie_ha = ROUND((ST_Area(ST_MakeValid(geometria))/10000)::numeric , 4) ");
		sql.append(" WHERE fk_evento = ? ");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idEvento);
	}
}
