/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Comune;

public class ComuneMapper implements RowMapper<Comune> {

	@Override
	public Comune mapRow(ResultSet rs, int arg1) throws SQLException {

		Comune comune = new Comune();
		comune.setIdComune(rs.getInt("id_comune"));
		comune.setIstatComune(rs.getString("istat_comune"));
		comune.setIstatProv(rs.getString("istat_prov"));
		comune.setDenominazioneComune(rs.getString("denominazione_comune"));
		comune.setFk_area_forestale(rs.getInt("fk_area_forestale"));
		comune.setCodiceBelfiore(rs.getString("codice_belfiore"));
		//TODO: Boza -> add geometria
		comune.setDataInizioValidita(rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		comune.setDataFineValidita(rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		
		
		
		return comune;
	}

}
