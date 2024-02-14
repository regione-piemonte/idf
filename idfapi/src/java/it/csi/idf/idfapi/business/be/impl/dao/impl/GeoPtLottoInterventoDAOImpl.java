/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.GeoPtLottoInterventoDAO;
import it.csi.idf.idfapi.dto.GeecoIvnTagliFeature;
import it.csi.idf.idfapi.dto.GeoPtLottoIntervento;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class GeoPtLottoInterventoDAOImpl implements GeoPtLottoInterventoDAO {

	@Override
	public int insertGeoPtLottoIntervento(GeecoIvnTagliFeature geoGeecoIvnTagliFeature) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO idf_geo_pt_lotto_intervento(");
		sql.append("data_inserimento, geometria, id_intervento)");
		sql.append(" VALUES (?, ?, ?)");
		
		List<Object>parameters = new ArrayList<>();
		
		parameters.add((geoGeecoIvnTagliFeature.getDatainserimento()));
		parameters.add(geoGeecoIvnTagliFeature.getGeometria());
		parameters.add(geoGeecoIvnTagliFeature.getFkIntervento());
		
		return AIKeyHolderUtil.updateWithGenKey("idgeo_pt_lotto_intervento", sql.toString(), parameters);
	}

	@Override
	public void deleteGeoPtLottoIntervento(int idIntervento) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM idf_geo_pt_lotto_intervento");
		sql.append(" WHERE id_intervento = ?");
		
		DBUtil.jdbcTemplate.update(sql.toString(), idIntervento);
	}
}
