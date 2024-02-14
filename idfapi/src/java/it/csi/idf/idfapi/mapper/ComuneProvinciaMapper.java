/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneProvincia;

public class ComuneProvinciaMapper implements RowMapper<ComuneProvincia> {

		@Override
		public ComuneProvincia mapRow(ResultSet rs, int arg1) throws SQLException {
			ComuneProvincia comuneProvincia = new ComuneProvincia();
			if(rs != null) {
				comuneProvincia.setIdComune(rs.getInt("id_comune"));
				comuneProvincia.setIstatComune(rs.getString("istat_comune"));
				comuneProvincia.setDenomComune(rs.getString("denominazione_comune"));
				
				comuneProvincia.setIstatProvincia(rs.getString("istat_prov"));
				comuneProvincia.setDenomProvincia(rs.getString("denominazione_prov"));
			}
			return comuneProvincia;
		}

}
