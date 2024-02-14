/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ParametroTrasf;

public class ParametroTrasfMapper implements RowMapper<ParametroTrasf>{

	@Override
	public ParametroTrasf mapRow(ResultSet rs, int arg1) throws SQLException {
		ParametroTrasf parametroTrasf = new ParametroTrasf();
		
		parametroTrasf.setIdParametroTrasf(rs.getInt("id_parametro_trasf"));
		parametroTrasf.setFkTipoParametroTrasf(rs.getInt("fk_tipo_paramero_trasf"));
		parametroTrasf.setDescParametroTrasf(rs.getString("desc_parametro_trasf"));
		parametroTrasf.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		parametroTrasf.setFlgVisible(rs.getByte("flg_visibile"));
		parametroTrasf.setValore(rs.getBigDecimal("valore"));
		
		return parametroTrasf;
	}
}
