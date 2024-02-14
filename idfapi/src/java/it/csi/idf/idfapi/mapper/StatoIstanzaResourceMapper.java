/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StatoIstanzaResource;

public class StatoIstanzaResourceMapper implements RowMapper<StatoIstanzaResource> {

	@Override
	public StatoIstanzaResource mapRow(ResultSet rs, int arg1) throws SQLException {
		StatoIstanzaResource statoIstanzaResource = new StatoIstanzaResource();
		statoIstanzaResource.setIdStatoIstanza(rs.getInt("id_stato_istanza"));
		statoIstanzaResource.setDescrStatoIstanza(rs.getString("descr_stato_istanza"));
		return statoIstanzaResource;
	}
}
