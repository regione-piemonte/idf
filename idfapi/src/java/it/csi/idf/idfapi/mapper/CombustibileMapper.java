/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Combustibile;
import it.csi.idf.idfapi.util.DBUtil;

public class CombustibileMapper implements RowMapper<Combustibile>{

	@Override
	public Combustibile mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Combustibile combustibile = new Combustibile();
		
		combustibile.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		combustibile.setCodCombustibile(rs.getString("cod_combustibile"));
		combustibile.setFlgPrincipale(rs.getByte("flg_principale"));
		combustibile.setPercCoperturaLettiera(DBUtil.getIntegerValueFromResultSet(rs,"perc_copertura_lettiera"));
		combustibile.setPercCoperturaErbacea(DBUtil.getIntegerValueFromResultSet(rs,"perc_copertura_erbacea"));
		combustibile.setPercCoperturaCespugli(DBUtil.getIntegerValueFromResultSet(rs,"perc_copertura_cespugli"));
		
		return combustibile;
		
	}
 

}
