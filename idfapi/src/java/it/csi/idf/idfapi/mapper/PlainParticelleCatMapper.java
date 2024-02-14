/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.PlainParticelleCat;
import it.csi.idf.idfapi.util.DBUtil;

public class PlainParticelleCatMapper implements RowMapper<PlainParticelleCat> {

	@Override
	public PlainParticelleCat mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainParticelleCat particella = new PlainParticelleCat();
		
		ComuneResource comuneResource= new ComuneResource();
		comuneResource.setDenominazioneComune(rs.getString("denominazione_comune"));
		comuneResource.setIstatComune(rs.getString("istat_comune"));
		comuneResource.setIstatProv(rs.getString("istat_prov"));
		comuneResource.setIdComune(rs.getInt("fk_comune"));
		
		particella.setId(rs.getInt("idgeo_pl_prop_catasto"));
		particella.setSezione(rs.getString("sezione"));
		particella.setFoglio(DBUtil.getIntegerValueFromResultSet(rs, "foglio"));
		particella.setParticella(rs.getString("particella"));
		particella.setSupCatastale(rs.getBigDecimal("sup_cartografica_ha") == null ? null
				: rs.getBigDecimal("sup_cartografica_ha").setScale(4, RoundingMode.FLOOR));
		
		particella.setComune(comuneResource);
		particella.setGeometry(rs.getObject("geometria"));
		
		return particella;
	}

}
