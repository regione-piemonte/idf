/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StadioSviluppo;

public class StadioSviluppoMapper implements RowMapper<StadioSviluppo>{
	@Override
	public StadioSviluppo mapRow(ResultSet rs, int arg1) throws SQLException {
		StadioSviluppo stadioSviluppo = new StadioSviluppo();
		stadioSviluppo.setDescrStadioSviluppo(rs.getString("descr_stadio_sviluppo"));
		stadioSviluppo.setCodStadioSviluppo(rs.getString("cod_stadio_sviluppo"));
		stadioSviluppo.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		stadioSviluppo.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return stadioSviluppo;
	}

}
