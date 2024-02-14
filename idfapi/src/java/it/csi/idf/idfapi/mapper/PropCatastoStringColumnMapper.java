/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PlainStringDTO;

public class PropCatastoStringColumnMapper implements RowMapper<PlainStringDTO> {
	
	private String columnName;

	public PropCatastoStringColumnMapper() {}

	public PropCatastoStringColumnMapper(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public PlainStringDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainStringDTO plainTextDTO = new PlainStringDTO();
		plainTextDTO.setValue("".equals(rs.getString(columnName))?"_":rs.getString(columnName));
		return plainTextDTO;
	}
}
