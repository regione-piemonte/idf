/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.LabelValore;

public class LabelValoreMapper implements RowMapper<LabelValore>{

	@Override
	public LabelValore mapRow(ResultSet rs, int arg1) throws SQLException {
		LabelValore item = new LabelValore();
		
		item.setLabel(rs.getString("label"));
		item.setValore(rs.getString("valore"));
		return item;
	}

}
