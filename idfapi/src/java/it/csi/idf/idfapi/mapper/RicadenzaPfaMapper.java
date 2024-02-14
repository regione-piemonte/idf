/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.RicadenzaPfa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RicadenzaPfaMapper implements RowMapper<RicadenzaPfa>{
	@Override
	public RicadenzaPfa mapRow(ResultSet rs, int arg1) throws SQLException {
		RicadenzaPfa ricadenza = new RicadenzaPfa();
		ricadenza.setId(rs.getInt("idgeo_pl_pfa"));
		ricadenza.setDenominazione(rs.getString("denominazione"));

		return ricadenza;
	}
}
