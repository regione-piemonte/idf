/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.idf.idfapi.dto.GeecoPfaEventoFeature;

public class GeecoPfaEventoFeatureMapper implements RowMapper<GeecoPfaEventoFeature> {

	@Override
	public GeecoPfaEventoFeature mapRow(ResultSet rs, int arg1) throws SQLException {
		GeecoPfaEventoFeature result = new GeecoPfaEventoFeature();
		result.setIdEvento(rs.getString("id_evento"));
		result.setIdEventoTipo(rs.getString("id"));
		result.setTipoGeometria(rs.getString("tipo"));
		result.setCodiceEvento(rs.getString("progressivo_evento_pfa"));
		result.setGeometriaEvento(rs.getString("geometria"));
		result.setIdgeoPlPfa(rs.getString("idgeo_pl_pfa"));
		result.setDenominazionePfa(rs.getString("denominazione"));
		result.setDescrizione(rs.getString("descrizione"));
		
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setFeatureLabel(result.getIdEventoTipo());
		geoJsonFeature.setGeoJsonFeature(rs.getString("json_build_object"));
		
		result.setFeature(geoJsonFeature);		
		return result;
	}

}
