/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PopolamentoSeme;

public class PopolamentoSemeMapper implements RowMapper<PopolamentoSeme> {

	@Override
	public PopolamentoSeme mapRow(ResultSet rs, int arg1) throws SQLException {
		
		PopolamentoSeme popolamentoSeme = new PopolamentoSeme();
		popolamentoSeme.setIdPopolamentoSeme(rs.getInt("id_popolamento_seme"));
		popolamentoSeme.setCodiceAmministrativo(rs.getString("codice_amministrativo"));
		popolamentoSeme.setDenominazione(rs.getString("denominazione"));
		popolamentoSeme.setSpecieIdonee(rs.getString("specie_idonee"));
		popolamentoSeme.setDataModifica(rs.getTimestamp("data_modifica"));
		popolamentoSeme.setDataFineValidita(rs.getTimestamp("data_fine_validita"));
		//TODO Add Geometria!
		
		return popolamentoSeme;
	}
	
}
