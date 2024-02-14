/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.UsoViabilita;

public class UsoViabilitaRowMapper implements RowMapper<UsoViabilita> {

	@Override
	public UsoViabilita mapRow(ResultSet rs, int arg1) throws SQLException {

		UsoViabilita usoViabilita = new UsoViabilita();
		
		usoViabilita.setIdUsoViabilita(rs.getInt("id_uso_viabilita"));
		usoViabilita.setDescrUsoViabilita(rs.getString("descr_uso_viabilita"));
		usoViabilita.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		usoViabilita.setFlgVisible(rs.getByte("flg_visibile"));
		
		return usoViabilita;
	}

}
