/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.DestLegname;

public class DestLegnameMapper implements RowMapper<DestLegname> {

	@Override
	public DestLegname mapRow(ResultSet rs, int arg1) throws SQLException {

		DestLegname legname = new DestLegname();
		legname.setIdDestLegname(rs.getInt("id_dest_legname"));
		legname.setDescrDestLegname(rs.getString("descr_dest_legname"));
		legname.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		legname.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return legname;
	}

	
}
