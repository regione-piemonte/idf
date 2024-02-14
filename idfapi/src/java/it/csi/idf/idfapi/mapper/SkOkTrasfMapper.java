/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.SkOkTrasf;

public class SkOkTrasfMapper implements RowMapper<SkOkTrasf>{

	@Override
	public SkOkTrasf mapRow(ResultSet rs, int arg1) throws SQLException {
		SkOkTrasf skOkTrasf = new SkOkTrasf();
		
		skOkTrasf.setIdIntervento(rs.getInt("id_intervento"));
		skOkTrasf.setFlgSez1(rs.getByte("flg_sez1"));
		skOkTrasf.setFlgSez2(rs.getByte("flg_sez2"));
		skOkTrasf.setFlgSez3(rs.getByte("flg_sez3"));
		skOkTrasf.setFlgSez4(rs.getByte("flg_sez4"));
		skOkTrasf.setFlgSez5(rs.getByte("flg_sez5"));
		skOkTrasf.setFlgSez6(rs.getByte("flg_sez6"));

		return skOkTrasf;
	}

}
