/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Assetto;

public class AssettoMapper implements RowMapper<Assetto>{

	@Override
	public Assetto mapRow(ResultSet rs, int arg1) throws SQLException {
		Assetto assetto = new Assetto();
		assetto.setIdAssetto(rs.getInt("id_assetto"));
		assetto.setCodAssetto(rs.getString("cod_assetto"));
		assetto.setDescAssetto(rs.getString("descr_assetto"));
		assetto.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		assetto.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return assetto;
	}

}
