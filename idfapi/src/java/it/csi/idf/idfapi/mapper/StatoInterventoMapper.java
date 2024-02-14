/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StatoIntervento;

public class StatoInterventoMapper implements RowMapper<StatoIntervento>{

	@Override
	public StatoIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		StatoIntervento statoIntervento = new StatoIntervento();
		
		statoIntervento.setIdStatoIntervento(rs.getInt("id_stato_intervento"));
		statoIntervento.setDescrStatoIntervento(rs.getString("descr_stato_intervento"));
		statoIntervento.setCodStatointervento(rs.getString("cod_stato_intervento"));
		
		return statoIntervento;
	}
}
