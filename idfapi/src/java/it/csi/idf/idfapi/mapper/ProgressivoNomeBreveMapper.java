/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ProgressivoNomeBreve;

public class ProgressivoNomeBreveMapper implements RowMapper<ProgressivoNomeBreve> {

	@Override
	public ProgressivoNomeBreve mapRow(ResultSet rs, int arg1) throws SQLException {
		ProgressivoNomeBreve progressivoNomeBreve = new ProgressivoNomeBreve();
		
		progressivoNomeBreve.setProgressivoEvento(rs.getInt("progressivo_evento_pfa"));
		progressivoNomeBreve.setNomeBreve(rs.getString("nome_breve"));
		progressivoNomeBreve.setIdEvento(rs.getInt("id_evento"));
		
		return progressivoNomeBreve;
	}
}
