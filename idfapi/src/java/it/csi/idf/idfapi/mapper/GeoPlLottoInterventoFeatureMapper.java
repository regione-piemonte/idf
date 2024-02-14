/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.idf.idfapi.dto.GeecoIvnTagliFeature;
import it.csi.idf.idfapi.dto.GeecoVincoloFeature;

public class GeoPlLottoInterventoFeatureMapper implements RowMapper<GeecoIvnTagliFeature> {

	@Override
	public GeecoIvnTagliFeature mapRow(ResultSet rs, int arg1) throws SQLException {
		GeecoIvnTagliFeature result = new GeecoIvnTagliFeature();
		
		result.setIdgeoPtLottoIntervento(rs.getString("idgeo_pl_lotto_intervento"));
		result.setFkIntervento(rs.getString("fk_intervento"));
		result.setGeometria(rs.getString("geometria"));
		result.setDatainserimento(rs.getDate("data_inserimento"));
		result.setSuperficie(rs.getString("superficie"));
		
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setFeatureLabel(result.getIdgeoPtLottoIntervento());
		geoJsonFeature.setGeoJsonFeature(rs.getString("json_build_object"));
		
		result.setFeature(geoJsonFeature);	
		
		return result;
	}
}
