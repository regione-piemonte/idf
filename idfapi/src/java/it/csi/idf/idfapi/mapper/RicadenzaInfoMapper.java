/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RicadenzaInfo;

public class RicadenzaInfoMapper implements RowMapper<RicadenzaInfo> {

	@Override
	public RicadenzaInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		RicadenzaInfo ricadenzaInfo = new RicadenzaInfo();
		if(rs.getString("codice_aapp") != null) {
			ricadenzaInfo.setAreeProtette("Aree: " + rs.getString("codice_aapp"));
		}
//		ricadenzaInfo.setReteNatura2000((String[])rs.getArray("rete_natura").getArray());
		ricadenzaInfo.setPopolamentoSeme(rs.getString("rete_natura"));
		ricadenzaInfo.setPopolamentoSeme(rs.getString("popolamento_seme"));
		ricadenzaInfo.setCategoriaForestali(rs.getString("categoria_forestale"));
		
		return ricadenzaInfo;
	}

}
