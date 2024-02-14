/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.idf.idfapi.dto.GeecoAdsFeature;


public class GeecoAdsFeatureMapper implements RowMapper<GeecoAdsFeature>{

	@Override
	public GeecoAdsFeature mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GeecoAdsFeature result = new GeecoAdsFeature();
		result.setIdgeoPtAds(rs.getString("idgeo_pt_ads"));
		result.setCodiceAds(rs.getString("codice_ads"));
		result.setGeometria(rs.getString("geometria"));
		
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setFeatureLabel(result.getIdgeoPtAds());
		geoJsonFeature.setGeoJsonFeature(rs.getString("json_build_object"));
		
		result.setFeature(geoJsonFeature);		
		return result;
	}

}
