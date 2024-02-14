/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeecoLayer;

public class GeecoLayerMapper implements RowMapper<GeecoLayer> {

	@Override
	public GeecoLayer mapRow(ResultSet rs, int arg1) throws SQLException {
		GeecoLayer result = new GeecoLayer();
		result.setIdGeecoProfilo(rs.getString("id_geeco_profilo"));
		result.setIdGeecoLayer(rs.getString("id_geeco_layer"));
		result.setDescrGeecoLayer(rs.getString("descr_asr_geeco_layer"));
		result.setFlgTipoAccessoScrittura(rs.getString("flg_tipo_accesso_scrittura"));
		result.setFlgTipoAccessoCanc(rs.getString("flg_tipo_accesso_canc"));
		result.setFlgTipoLayer(rs.getString("flg_tipo_layer"));
		return result;
	}

}
