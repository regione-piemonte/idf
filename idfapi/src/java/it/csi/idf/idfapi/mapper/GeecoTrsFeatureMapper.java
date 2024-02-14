/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.idf.idfapi.dto.GeecoTrsFeature;

public class GeecoTrsFeatureMapper implements RowMapper<GeecoTrsFeature> {

	@Override
	public GeecoTrsFeature mapRow(ResultSet rs, int arg1) throws SQLException {
		GeecoTrsFeature result = new GeecoTrsFeature();
		
		result.setIdgeoPlIntervTrasf(rs.getString("idgeo_pl_interv_trasf"));
		result.setFkIntervento(rs.getString("fk_intervento"));
		result.setGeometria(rs.getString("geometria"));
		result.setDataInserimento(rs.getDate("data_inserimento"));
		result.setSuperficie(rs.getString("superficie"));
		
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setFeatureLabel(result.getIdgeoPlIntervTrasf());
		geoJsonFeature.setGeoJsonFeature(rs.getString("json_build_object"));
		
		result.setFeature(geoJsonFeature);	
		
		return result;
	}
	
	
	
}
