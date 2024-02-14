/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.SkOkSelv;

public class SkOkSelvMapper implements RowMapper<SkOkSelv>{

	@Override
	public SkOkSelv mapRow(ResultSet rs, int arg1) throws SQLException {

		SkOkSelv skOkSelv = new SkOkSelv();
		
		skOkSelv.setIdIntervento(rs.getInt("id_intervento"));
		skOkSelv.setFlgSez1(rs.getByte("flg_sez1"));
		skOkSelv.setFlgSez2(rs.getByte("flg_sez2"));
		skOkSelv.setFlgSez3(rs.getByte("flg_sez3"));
		skOkSelv.setFlgSez4(rs.getByte("flg_sez4"));
		skOkSelv.setFlgSez5(rs.getByte("flg_sez5"));
		
		return skOkSelv;
	}

}
