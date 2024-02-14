/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoIstanzaResource;

public class TipoIstanzaResourceMapper implements RowMapper<TipoIstanzaResource> {

	@Override
	public TipoIstanzaResource mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoIstanzaResource tipoIstanzaResource = new TipoIstanzaResource();
		tipoIstanzaResource.setIdTipoIstanza(rs.getInt("id_tipo_istanza"));
		tipoIstanzaResource.setDescrDettTipoIstanza(rs.getString("descr_dett_tipoistanza"));
		return tipoIstanzaResource;
	}
}
