/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ParametroAppl;

public class ParametroApplMapper implements RowMapper<ParametroAppl> {

	@Override
	public ParametroAppl mapRow(ResultSet rs, int arg1) throws SQLException {
		
		ParametroAppl parametroAppl = new ParametroAppl();
		
		parametroAppl.setIdParametroAppl(rs.getInt("id_parametro_appl"));
		parametroAppl.setIfkTipoParamAppl(rs.getInt("fk_tipo_param_appl"));
		parametroAppl.setParametroApplNum(rs.getInt("parametro_appl_num"));
		parametroAppl.setParametroApplChar(rs.getString("parametro_appl_char"));
		
		return parametroAppl;
	}
	
}
