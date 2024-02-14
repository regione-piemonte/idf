/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AreaProtetta;

public class AreaProtettaMapper implements RowMapper<AreaProtetta> {

	@Override
	public AreaProtetta mapRow(ResultSet rs, int arg1) throws SQLException {
		AreaProtetta areaProtetta = new AreaProtetta();
		
		areaProtetta.setIdgeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		areaProtetta.setIdAreaProtetta(rs.getInt("id_area_protetta"));
		areaProtetta.setDataInizioValidita(rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		areaProtetta.setNomeAreaProtetta(rs.getString("nome_area_protetta"));
		areaProtetta.setDataFineValidita(rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		areaProtetta.setSupRicadenzaHa(rs.getBigDecimal("sup_ricadenza_ha") == null ? null: rs.getBigDecimal("sup_ricadenza_ha").setScale(0, RoundingMode.FLOOR));
		areaProtetta.setPercRicadenza(rs.getBigDecimal("perc_ricadenza") == null ? null
				: rs.getBigDecimal("perc_ricadenza").setScale(0, RoundingMode.FLOOR));
		
		return areaProtetta;
	}
}
