/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.FinalitaTaglio;

public class FinalitaTaglioMapper implements RowMapper<FinalitaTaglio> {

	@Override
	public FinalitaTaglio mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FinalitaTaglio finTaglio = new FinalitaTaglio();
		
		finTaglio.setIdFinalitaTaglio(rs.getInt("id_finalita_taglio"));
		finTaglio.setDescrFinalitaTaglio(rs.getString("descr_finalita_taglio"));
		finTaglio.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		finTaglio.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return finTaglio;
	}

	

}
