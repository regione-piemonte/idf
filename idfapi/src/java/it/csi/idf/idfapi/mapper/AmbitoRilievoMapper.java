/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.AmbitoRilievo;


public class AmbitoRilievoMapper implements RowMapper<AmbitoRilievo>{
	@Override
	public AmbitoRilievo mapRow(ResultSet rs, int arg1) throws SQLException {
		AmbitoRilievo ambito = new AmbitoRilievo();
		ambito.setIdAmbito(rs.getInt("id_ambito"));
		ambito.setDescrAmbito(rs.getString("descr_ambito"));
		ambito.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		ambito.setFlgVisibile(rs.getByte("flg_visibile"));
		ambito.setFlgSpecificare(rs.getByte("flg_specificare"));
		ambito.setFkPadre(rs.getInt(("fk_padre")));
		
		return ambito;
	}
}
