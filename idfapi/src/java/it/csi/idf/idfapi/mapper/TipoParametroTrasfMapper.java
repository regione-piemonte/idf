/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoParametroTrasf;

public class TipoParametroTrasfMapper implements RowMapper<TipoParametroTrasf> {

	@Override
	public TipoParametroTrasf mapRow(ResultSet rs, int arg1) throws SQLException {
		
		TipoParametroTrasf tipoParametroTrasf = new TipoParametroTrasf();
		
		tipoParametroTrasf.setIdTipoParametroTrasf(rs.getInt("id_tipo_paramero_trasf"));
		tipoParametroTrasf.setTipoParemetroTrasf(rs.getString("tipo_paremetro_trasf"));
		tipoParametroTrasf.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		tipoParametroTrasf.setFlgVisible(rs.getByte("flg_visibile"));
		
		return tipoParametroTrasf;
	}
}
