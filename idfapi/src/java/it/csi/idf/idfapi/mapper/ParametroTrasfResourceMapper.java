/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ParametroTrasfResource;

public class ParametroTrasfResourceMapper implements RowMapper<ParametroTrasfResource> {

	@Override
	public ParametroTrasfResource mapRow(ResultSet rs, int arg1) throws SQLException {
		ParametroTrasfResource parametroTrasf = new ParametroTrasfResource();
		
		parametroTrasf.setIdParametroTrasf(rs.getInt("id_parametro_trasf"));
		parametroTrasf.setFkTipoParametroTrasf(rs.getInt("fk_tipo_paramero_trasf"));
		parametroTrasf.setDescParametroTrasf(rs.getString("desc_parametro_trasf"));
		
		return parametroTrasf;
	}
}
