/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PlainParticelleCatastali;

public class PlainPropCatastoMapper implements RowMapper<PlainParticelleCatastali>{
	
@Override
public PlainParticelleCatastali mapRow(ResultSet rs, int arg1) throws SQLException {
	PlainParticelleCatastali particella = new PlainParticelleCatastali();

	particella.setId(rs.getInt("idgeo_pl_prop_catasto"));
	particella.setSupIntervento(rs.getBigDecimal("area"));
	return particella;
}

}
