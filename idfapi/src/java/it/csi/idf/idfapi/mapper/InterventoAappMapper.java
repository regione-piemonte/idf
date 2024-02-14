/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.InterventoAapp;

public class InterventoAappMapper implements RowMapper<InterventoAapp>{

	@Override
	public InterventoAapp mapRow(ResultSet rs, int arg1) throws SQLException {
		InterventoAapp interventoAapp = new InterventoAapp();
		
		interventoAapp.setIdIntervento(rs.getInt("id_intervento"));
		interventoAapp.setCodiceAapp(rs.getString("codice_aapp"));
		
		return interventoAapp;
	}

}
