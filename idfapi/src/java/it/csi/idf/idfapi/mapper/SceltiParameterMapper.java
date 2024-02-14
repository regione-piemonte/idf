/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.SceltiParameter;

public class SceltiParameterMapper implements RowMapper<SceltiParameter>{

	@Override
	public SceltiParameter mapRow(ResultSet rs, int arg1) throws SQLException {
		SceltiParameter sceltiParameter = new SceltiParameter();
		
		sceltiParameter.setNome(rs.getString("tipo_paremetro_trasf"));
		sceltiParameter.setValore(rs.getBigDecimal("valore"));
		
		return sceltiParameter;
	}

}
