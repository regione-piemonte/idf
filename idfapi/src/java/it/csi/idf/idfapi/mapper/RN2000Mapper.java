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

import it.csi.idf.idfapi.dto.RN2000;

public class RN2000Mapper implements RowMapper<RN2000> {

	@Override
	public RN2000 mapRow(ResultSet rs, int arg1) throws SQLException {
		RN2000 rn2000 = new RN2000();
		
		rn2000.setIdgeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		rn2000.setIdRn2000(rs.getInt("id_rn_2000"));
		rn2000.setDataInizioValidita(rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		rn2000.setNomeRn2000(rs.getString("nome_rn_2000"));
		rn2000.setDataFineValidita(rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		rn2000.setSupRicadenzaHa(rs.getBigDecimal("sup_ricadenza_ha")== null ? null: rs.getBigDecimal("sup_ricadenza_ha").setScale(0, RoundingMode.FLOOR));
		rn2000.setPercRicadenza(rs.getBigDecimal("perc_ricadenza") == null ? null
				: rs.getBigDecimal("perc_ricadenza").setScale(0, RoundingMode.FLOOR));
		
		return rn2000;
	}
}
