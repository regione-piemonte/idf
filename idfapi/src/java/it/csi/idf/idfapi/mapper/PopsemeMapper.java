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

import it.csi.idf.idfapi.dto.Popseme;

public class PopsemeMapper implements RowMapper<Popseme>{

	@Override
	public Popseme mapRow(ResultSet rs, int arg1) throws SQLException {
		Popseme popseme = new Popseme();
		
		popseme.setIdgeoPlPfa(rs.getInt("idgeo_pl_pfa"));
		popseme.setIdPopolamentoSeme(rs.getInt("id_popolamento_seme"));
		popseme.setDataInizioValidita(rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		popseme.setDataFineValidita(rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		popseme.setSupRicadenzaHa((rs.getBigDecimal("sup_ricadenza_ha") == null ? null : rs.getBigDecimal("sup_ricadenza_ha").setScale(0, RoundingMode.FLOOR)));
		popseme.setPercRicadenza(rs.getBigDecimal("perc_ricadenza") == null ? null
				: rs.getBigDecimal("perc_ricadenza").setScale(0, RoundingMode.FLOOR));
		popseme.setDenominazione(rs.getString("denominazione"));
		
		return popseme;
	}
}
