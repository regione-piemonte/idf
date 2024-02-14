/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PartforInterventoDTO;

public class PartforIntervMapper implements RowMapper<PartforInterventoDTO> {

	@Override
	public PartforInterventoDTO mapRow(ResultSet rs, int arg1) throws SQLException {

		PartforInterventoDTO partFor = new PartforInterventoDTO();
		partFor.setIdIntervento(rs.getInt("id_intervento"));
		partFor.setIdGeoPlParticellaForestale(rs.getInt("idgeo_pl_particella_forest"));
		partFor.setDataInizioValidita(rs.getDate("data_inizio_validita").toLocalDate());
		
		return partFor;
	}

}
