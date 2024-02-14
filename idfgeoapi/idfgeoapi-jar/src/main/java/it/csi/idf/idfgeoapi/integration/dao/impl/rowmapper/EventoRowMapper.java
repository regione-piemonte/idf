/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfgeoapi.dto.EventoForLayerDto;
import it.csi.idf.idfgeoapi.dto.EventoPlForLayerDto;

public class EventoRowMapper implements RowMapper<EventoForLayerDto> {

	@Override
	public EventoForLayerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventoForLayerDto result = new EventoPlForLayerDto();
		
		result.setFkEvento(rs.getInt("id_evento"));
		result.setTipo(rs.getString("tipo"));
		result.setCodiceEvento(rs.getString("progressivo_evento_pfa"));
		result.setDescrizione(rs.getString("descrizione"));
		result.setGeometry(null);
		result.setIdFeature(rs.getLong("id"));
		result.setDenominazionePfa(rs.getString("denominazione"));
		
		
		return result;
	}

}
