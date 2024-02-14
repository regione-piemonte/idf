/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;

public class PlainRelascopicaMapper implements RowMapper<PlainRelascopicaSemplice> {

	
	@Override
	public PlainRelascopicaSemplice mapRow(ResultSet rs, int arg1) throws SQLException {
		
		PlainRelascopicaSemplice relSempl = new PlainRelascopicaSemplice();
		
		relSempl.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		relSempl.setFattoreNumerazione(rs.getInt("fattore_numerazione"));
		relSempl.setFkTipoStrutturalePrinc(rs.getInt("fk_tipo_strutturale_princ"));
		relSempl.setNote(rs.getString("note"));
		
		
		return relSempl;
	}

}
