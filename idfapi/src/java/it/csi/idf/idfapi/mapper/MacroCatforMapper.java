/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.MacroCatfor;

public class MacroCatforMapper implements RowMapper<MacroCatfor>{

	@Override
	public MacroCatfor mapRow(ResultSet rs, int arg1) throws SQLException {
		MacroCatfor macroCatfor= new MacroCatfor();
		
		macroCatfor.setIdParamMacroCatfor(rs.getInt("id_param_macro_catfor"));
		macroCatfor.setDescrCatfor(rs.getString("descr_catfor"));
		macroCatfor.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		macroCatfor.setFlgVisible(rs.getByte("flg_visibile"));
		
		return macroCatfor;
	}

}
