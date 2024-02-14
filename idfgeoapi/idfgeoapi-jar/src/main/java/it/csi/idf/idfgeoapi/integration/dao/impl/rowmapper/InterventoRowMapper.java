/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfgeoapi.dto.InterventoForLayerDto;

public class InterventoRowMapper implements RowMapper<InterventoForLayerDto>{

	@Override
	public InterventoForLayerDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		InterventoForLayerDto result = new InterventoForLayerDto();
		
		result.setFkIntervento(rs.getInt("id_intervento"));
		result.setTipo(rs.getString("tipo"));
		result.setDescrizione(rs.getString("descrizione"));
		result.setCodiceIntervento(rs.getString("nr_progressivo_interv"));
		result.setGeometry(null);
		result.setIdFeature(rs.getLong("id"));
		result.setDenominazionePfa(rs.getString("denominazione"));
		
		return result;
	}

}
