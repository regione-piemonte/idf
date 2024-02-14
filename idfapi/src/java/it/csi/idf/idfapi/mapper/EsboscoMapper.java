/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Esbosco;

public class EsboscoMapper implements RowMapper<Esbosco>{
	@Override
	public Esbosco mapRow(ResultSet rs, int arg1) throws SQLException {
		Esbosco esbosco = new Esbosco();
	
		esbosco.setDescrEsbosco(rs.getString("descr_esbosco"));
		esbosco.setCodEsbosco(rs.getString("cod_esbosco"));		
		esbosco.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		esbosco.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return esbosco;
	}

}
