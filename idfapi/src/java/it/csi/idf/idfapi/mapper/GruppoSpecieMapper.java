/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GruppoSpecie;

public class GruppoSpecieMapper implements RowMapper<GruppoSpecie> {

	@Override
	public GruppoSpecie mapRow(ResultSet rs, int arg1) throws SQLException {
		
		GruppoSpecie  gruppo = new GruppoSpecie();
		
		gruppo.setCodGruppo(rs.getString("cod_gruppo"));
		gruppo.setDescrGruppo(rs.getString("descr_gruppo"));
		gruppo.setFlgVisibile(rs.getByte("flg_visibile"));
		gruppo.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		
		return gruppo;
	}
	
}
