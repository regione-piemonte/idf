/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;

public class ComuneResourceMapper implements RowMapper<ComuneResource>{

	@Override
	public ComuneResource mapRow(ResultSet rs, int arg1) throws SQLException {
		ComuneResource comuneResource = new ComuneResource();
		comuneResource.setIdComune(rs.getInt("id_comune"));
		comuneResource.setIstatComune(rs.getString("istat_comune"));
		comuneResource.setIstatProv(rs.getString("istat_prov"));
		comuneResource.setDenominazioneComune(rs.getString("denominazione_comune"));
		comuneResource.setCodiceBelfiore(rs.getString("codice_belfiore"));
		
		return comuneResource;
	}
}
