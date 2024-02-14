/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoAccreditamento;

public class TipoAccreditamentoMapper implements RowMapper<TipoAccreditamento> {

	@Override
	public TipoAccreditamento mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoAccreditamento tipoAccreditamento = new TipoAccreditamento();
		tipoAccreditamento.setIdTipoAccreditamento(rs.getInt("id_tipo_accreditamento"));
		tipoAccreditamento.setDescrTipoAccreditamento(rs.getString("descr_tipo_accreditamento"));
		return tipoAccreditamento;
	}

}
