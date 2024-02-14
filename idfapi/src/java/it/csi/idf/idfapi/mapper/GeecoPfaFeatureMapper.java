/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.idf.idfapi.dto.GeecoPfaFeature;

public class GeecoPfaFeatureMapper implements RowMapper<GeecoPfaFeature> {

	@Override
	public GeecoPfaFeature mapRow(ResultSet rs, int rowNum) throws SQLException {
		GeecoPfaFeature result = new GeecoPfaFeature();
		result.setIdgeoPlPfa(rs.getString("idgeo_pl_pfa"));
		result.setDenominazionePfa(rs.getString("denominazione"));
		result.setDenominazioneComunePfa(rs.getString("denominazione_comune"));
		result.setGeometriaPfa(rs.getString("geometria"));
		
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setFeatureLabel(result.getIdgeoPlPfa());
		geoJsonFeature.setGeoJsonFeature(rs.getString("json_build_object"));
		
		result.setFeature(geoJsonFeature);
		
		return result;
	}

}
