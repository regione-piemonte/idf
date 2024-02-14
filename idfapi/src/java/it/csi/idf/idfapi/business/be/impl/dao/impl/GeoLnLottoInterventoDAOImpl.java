/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoLnLottoInterventoDAO;
import it.csi.idf.idfapi.dto.GeoLnLottoIntervento;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoLnLottoInterventoDAOImpl implements GeoLnLottoInterventoDAO {

	@Override
	public int insertGeoLnLottoIntervento(GeoLnLottoIntervento geoLnLottoIntervento) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_geo_ln_lotto_intervento(");
		sql.append("data_inserimento, geometria, id_intervento)");
		sql.append(" VALUES (?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add(Date.valueOf(geoLnLottoIntervento.getDatainserimento()));
		parameters.add(geoLnLottoIntervento.getGeometria());
		parameters.add(geoLnLottoIntervento.getIdIntervento());
		
		return AIKeyHolderUtil.updateWithGenKey("idgeo_ln_lotto_intervento", sql.toString(), parameters);
	}

	@Override
	public void deleteGeoLnLottoIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM idf_geo_ln_lotto_intervento");
		sql.append(" WHERE id_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}
}
