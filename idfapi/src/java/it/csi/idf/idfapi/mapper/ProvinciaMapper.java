/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPLProvincia;

public class ProvinciaMapper implements RowMapper<GeoPLProvincia> {

	@Override
	public GeoPLProvincia mapRow(ResultSet rs, int arg1) throws SQLException {

		GeoPLProvincia province = new GeoPLProvincia();

		province.setIstatProv(rs.getString("istat_prov"));
		province.setSiglaProv(rs.getString("sigla_prov"));
		province.setDenominazioneProv(rs.getString("denominazione_prov"));
		province.setGeometria(rs.getObject("geometria") == null ? null : rs.getObject("geometria"));
		province.setFkRegione(rs.getInt("fk_regione"));

		return province;
	}

}
