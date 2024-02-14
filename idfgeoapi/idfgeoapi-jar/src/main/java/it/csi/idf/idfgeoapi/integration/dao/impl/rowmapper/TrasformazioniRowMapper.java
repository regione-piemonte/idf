/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.ecogis.util.conversion.GeoJSONGeometryConverter;
import it.csi.idf.idfgeoapi.dto.TrasformazioneForLayerDto;

public class TrasformazioniRowMapper implements RowMapper<TrasformazioneForLayerDto> {

	@Override
	public TrasformazioneForLayerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		TrasformazioneForLayerDto result = new TrasformazioneForLayerDto();
		result.setIdgeoPlIntervTrasf(rs.getInt("idgeo_pl_interv_trasf"));
		result.setFkIntervento(rs.getInt("fk_intervento"));
		try {
			String geometria = rs.getString("geometria");
			
			
			
			
			result.setGeometry(GeoJSONGeometryConverter.getGeometryFromGeoJSON(geometria));
		} catch (IOException e) {
			result.setGeometry(null);
		}
		result.setSuperficie(rs.getString("superficie"));
		return result;
	}

}
