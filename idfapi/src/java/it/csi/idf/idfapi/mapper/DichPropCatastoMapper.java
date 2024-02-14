/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DichPropCatasto;

public class DichPropCatastoMapper implements RowMapper<DichPropCatasto>{

	@Override
	public DichPropCatasto mapRow(ResultSet rs, int arg1) throws SQLException {
		DichPropCatasto catasto = new DichPropCatasto();
		
		//catasto.setIdGeoPlPropCatasto(DBUtil.getIntegerValueFromResultSet(rs, "idgeo_pl_prop_catasto"));
		catasto.setSezione(rs.getString("sezione"));
		catasto.setFoglio(rs.getInt("foglio"));
		catasto.setParticella(rs.getString("particella"));
		catasto.setSupCatastaleMq(rs.getBigDecimal("sup_catastale_ha"));
		catasto.setSupCartograficaHa(rs.getBigDecimal("sup_cartografica_ha"));
		catasto.setDenominazioneComune(rs.getString("denominazione_comune"));
		catasto.setDenominazioneProvincia(rs.getString("denominazione_prov"));
		catasto.setSupIntervento(rs.getBigDecimal("sup_intervento_ha")== null ? null :rs.getBigDecimal("sup_intervento_ha").setScale(4, RoundingMode.FLOOR));
		
		return catasto;
	}
}
