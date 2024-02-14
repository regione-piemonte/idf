/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.RicadenzaPortaseme;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RicadenzaPortaSemeMapper implements RowMapper<RicadenzaPortaseme>{
	@Override
	public RicadenzaPortaseme mapRow(ResultSet rs, int arg1) throws SQLException {
		RicadenzaPortaseme ricadenza = new RicadenzaPortaseme();
		ricadenza.setId(rs.getInt("idgeo_pt_portaseme"));
		ricadenza.setLocalita(rs.getString("localita"));
		ricadenza.setSpecie(rs.getString("specie"));


		return ricadenza;
	}
}
