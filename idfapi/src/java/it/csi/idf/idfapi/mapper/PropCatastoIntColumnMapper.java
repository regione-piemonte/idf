/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PlainIntDTO;

public class PropCatastoIntColumnMapper implements RowMapper<PlainIntDTO> {
	
	private String columnName;

	public PropCatastoIntColumnMapper() {}

	public PropCatastoIntColumnMapper(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public PlainIntDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainIntDTO plainTextDTO = new PlainIntDTO();
		plainTextDTO.setValue(rs.getInt(columnName));
		return plainTextDTO;
	}
}
