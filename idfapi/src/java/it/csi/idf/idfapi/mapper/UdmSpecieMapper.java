/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.UdmSpecie;

public class UdmSpecieMapper implements RowMapper<UdmSpecie>{

	@Override
	public UdmSpecie mapRow(ResultSet rs, int arg1) throws SQLException {

		UdmSpecie udm = new UdmSpecie();
		
		udm.setIdUdm(rs.getInt("id_udm"));
		udm.setDescrUdm(rs.getString("descr_udm"));
		udm.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		udm.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return udm;
	}

}
