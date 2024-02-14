/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DoubleMapper implements RowMapper<Double> {

	@Override
	public Double mapRow(ResultSet rs, int arg1) throws SQLException {
		return rs.getDouble(1);
	}

}
