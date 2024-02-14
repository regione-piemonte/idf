/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.TagliCatastoXls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagliCatastoXlsMapper implements RowMapper<TagliCatastoXls>{
	
	@Override
	public TagliCatastoXls mapRow(ResultSet rs, int arg1) throws SQLException {
		TagliCatastoXls elem = new TagliCatastoXls();
		elem.setNrIstanzaForestale(rs.getInt("nr_istanza_forestale"));
		elem.setAnno(rs.getString("anno"));
		elem.setComune(rs.getString("denominazione_comune"));
		elem.setSezione(rs.getString("sezione"));
		elem.setFoglio(rs.getInt("foglio"));
		elem.setParticella(rs.getString("particella"));

		return elem;
	}

}
