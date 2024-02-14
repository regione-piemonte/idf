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
import it.csi.idf.idfapi.dto.PlainParticelleCatastali;
import it.csi.idf.idfapi.util.DBUtil;

public class PlainParticelleCatastaliMapper implements RowMapper<PlainParticelleCatastali> {

	@Override
	public PlainParticelleCatastali mapRow(ResultSet rs, int arg1) throws SQLException {
		PlainParticelleCatastali particella = new PlainParticelleCatastali();
		
		ComuneResource comuneResource= new ComuneResource();
		comuneResource.setDenominazioneComune(rs.getString("denominazione_comune"));
		comuneResource.setIstatComune(rs.getString("istat_comune"));
		comuneResource.setIstatProv(rs.getString("istat_prov"));
		comuneResource.setIdComune(rs.getInt("fk_comune"));
		
		particella.setIdGeoPlPropCatasto(rs.getInt("idgeo_pl_prop_catasto"));
		particella.setId(rs.getInt("idgeo_pl_prop_catasto"));
		particella.setSezione(rs.getString("sezione"));
		particella.setFoglio(DBUtil.getIntegerValueFromResultSet(rs, "foglio"));
		particella.setParticella(rs.getString("particella"));
		particella.setSupCatastale(rs.getBigDecimal("sup_cartografica_ha") == null ? null
				: rs.getBigDecimal("sup_cartografica_ha").setScale(4, RoundingMode.FLOOR));
		particella.setSupCartograficaHa(rs.getBigDecimal("sup_cartografica_ha") == null ? null
				: rs.getBigDecimal("sup_cartografica_ha").setScale(4, RoundingMode.FLOOR));
		particella.setSupIntervento(rs.getBigDecimal("sup_intervento_ha") == null ? null
				: rs.getBigDecimal("sup_intervento_ha").setScale(4, RoundingMode.FLOOR));
		particella.setComune(comuneResource);
		//particella.setGeometry(rs.getObject("geometria"));
		
		/*
		particella.getComune().setDenominazioneComune(rs.getString("denominazione_comune"));
		
		particella.getComune().setIstatComune(rs.getString("istat_comune"));
		
		particella.getComune().setId_comune(rs.getInt("fk_comune"));
		
		particella.getComune().setIstatProv(rs.getString("istat_prov"));
		*/
		
		

		return particella;
	}

}
