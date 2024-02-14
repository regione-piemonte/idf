/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.geeco_java_client.dto.internal.GeoJsonFeature;
import it.csi.idf.idfapi.dto.GeecoPfaInterventoFeature;

public class GeecoPfaInterventoFeatureMapper implements RowMapper<GeecoPfaInterventoFeature> {

	@Override
	public GeecoPfaInterventoFeature mapRow(ResultSet rs, int arg1) throws SQLException {
		GeecoPfaInterventoFeature result = new GeecoPfaInterventoFeature();
		result.setIdIntervento(rs.getString("id_intervento"));
		result.setIdInterventoTipo(rs.getString("id"));
		result.setTipoGeometria(rs.getString("tipo"));
		result.setCodiceIntervento(rs.getString("nr_progressivo_interv"));
		result.setGeometriaIntervento(rs.getString("geometria"));
		result.setIdgeoPlPfa(rs.getString("idgeo_pl_pfa"));
		result.setDenominazionePfa(rs.getString("denominazione"));
		result.setDescrizione(rs.getString("descrizione"));
		
		GeoJsonFeature geoJsonFeature = new GeoJsonFeature();
		geoJsonFeature.setFeatureLabel(result.getIdInterventoTipo());
		geoJsonFeature.setGeoJsonFeature(rs.getString("json_build_object"));
		
		result.setFeature(geoJsonFeature);		
		return result;
	}

}
