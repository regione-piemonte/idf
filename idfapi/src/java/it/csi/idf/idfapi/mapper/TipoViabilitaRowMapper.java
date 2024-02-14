/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.TipoViabilita;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoViabilitaRowMapper implements RowMapper<TipoViabilita> {

	@Override
	public TipoViabilita mapRow(ResultSet rs, int arg1) throws SQLException {

		TipoViabilita viabilita = new TipoViabilita();

		viabilita.setIdTipoViabilita(rs.getInt("id_tipo_viabilita"));
		viabilita.setCodiceTipoViabilita(rs.getString("cod_tipo_viabilita"));
		viabilita.setTipoViabilita(rs.getString("tipo_viabilita"));
		viabilita.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		viabilita.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		viabilita.setFlgVisible(rs.getByte("flg_visibile"));
		
		return viabilita;
	}

}
