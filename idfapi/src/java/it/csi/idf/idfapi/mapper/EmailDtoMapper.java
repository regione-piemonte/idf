/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.util.mail.EmailDTO;

public class EmailDtoMapper implements RowMapper<EmailDTO>{

	@Override
	public EmailDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		EmailDTO email = new EmailDTO();
		email.setHost(rs.getString("host"));
		email.setPort(rs.getString("porta"));
		email.setMittente(rs.getString("mittente"));
		email.setOggetto(rs.getString("oggetto"));
		email.setCorpo(rs.getString("testo"));
		return email;
		
	}

}
