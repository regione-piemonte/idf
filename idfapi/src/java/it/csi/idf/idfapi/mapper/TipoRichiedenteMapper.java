/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.TipoRichiedente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoRichiedenteMapper implements RowMapper<TipoRichiedente> {

	@Override
	public TipoRichiedente mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoRichiedente dto = new TipoRichiedente();
		dto.setIdTipoRichiedente(rs.getInt("id_tipo_richiedente"));
		dto.setDescrTipoRichiedente(rs.getString("descr_tipo_richiedente"));
		dto.setOrdinamento(rs.getInt("mtd_ordinamento"));
		return dto;
	}

}
