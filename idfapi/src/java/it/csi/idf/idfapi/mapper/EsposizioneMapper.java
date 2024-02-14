/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Esposizione;

public class EsposizioneMapper implements RowMapper<Esposizione>{
	@Override
	public Esposizione mapRow(ResultSet rs, int arg1) throws SQLException {
		Esposizione esposizione = new Esposizione();
		esposizione.setIdEsposizione(rs.getInt("id_esposizione"));
		esposizione.setDescrEsposizione(rs.getString("descr_esposizione"));
		esposizione.setCodEsposizione(rs.getString("cod_esposizione"));
		esposizione.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		esposizione.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return esposizione;
	}

}
