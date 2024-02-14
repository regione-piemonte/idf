/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.business.be.vincolo.pojo.InfoAllegatiVincolo;

public class InfoAllegatiVincoloMapper implements RowMapper<InfoAllegatiVincolo> {

	@Override
	public InfoAllegatiVincolo mapRow(ResultSet rs, int arg1) throws SQLException {
		
		InfoAllegatiVincolo  infoAllegato = new InfoAllegatiVincolo();
		
		infoAllegato.setNomeAllegato(rs.getString("descr_tipo_allegato"));
		infoAllegato.setChecked(rs.getString("fk_tipo_allegato") != null);
		
		return infoAllegato;
	}
	
}

