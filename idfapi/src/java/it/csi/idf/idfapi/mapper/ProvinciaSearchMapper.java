/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;

public class ProvinciaSearchMapper implements RowMapper<GeoPLProvinciaSearch> {

	@Override
	public GeoPLProvinciaSearch mapRow(ResultSet rs, int arg1) throws SQLException {

		GeoPLProvinciaSearch province = new GeoPLProvinciaSearch();

		province.setIstatProv(rs.getString("istat_prov"));
		province.setSiglaProv(rs.getString("sigla_prov"));
		province.setDenominazioneProv(rs.getString("denominazione_prov"));
		
		return province;
	}

}
