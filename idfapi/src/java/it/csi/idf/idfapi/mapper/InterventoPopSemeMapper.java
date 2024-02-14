/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.InterventoPopSeme;

public class InterventoPopSemeMapper implements RowMapper<InterventoPopSeme>{

	@Override
	public InterventoPopSeme mapRow(ResultSet rs, int arg1) throws SQLException {
		InterventoPopSeme interventoPopSeme = new InterventoPopSeme();
		
		interventoPopSeme.setIdIntervento(rs.getInt("id_intervento"));
		interventoPopSeme.setIdPopolamentoSeme(rs.getInt("id_popolamento_seme"));
		
		return interventoPopSeme;
	}

}
