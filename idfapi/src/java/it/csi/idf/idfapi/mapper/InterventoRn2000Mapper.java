/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.InterventoRn2000;

public class InterventoRn2000Mapper implements RowMapper<InterventoRn2000>{

	@Override
	public InterventoRn2000 mapRow(ResultSet rs, int arg1) throws SQLException {
		InterventoRn2000 interventoRn2000 = new InterventoRn2000();
		
		interventoRn2000.setIdIntervento(rs.getInt("id_intervento"));
		interventoRn2000.setCodiceRn2000(rs.getString("codice_rn_2000"));
		
		return interventoRn2000;
	}
}
