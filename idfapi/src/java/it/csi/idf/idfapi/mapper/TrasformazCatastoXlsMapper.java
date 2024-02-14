/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TrasformazCatastoXls;

public class TrasformazCatastoXlsMapper implements RowMapper<TrasformazCatastoXls>{
	
	@Override
	public TrasformazCatastoXls mapRow(ResultSet rs, int arg1) throws SQLException {
		TrasformazCatastoXls elem = new TrasformazCatastoXls();
		elem.setIdIstanza(rs.getInt("nr_istanza_forestale"));
		elem.setAnno(rs.getString("anno"));
		elem.setComune(rs.getString("denominazione_comune"));
		elem.setSezione(rs.getString("sezione"));
		elem.setFoglio(rs.getInt("foglio"));
		elem.setParticella(rs.getString("particella"));
		return elem;
	}

}
