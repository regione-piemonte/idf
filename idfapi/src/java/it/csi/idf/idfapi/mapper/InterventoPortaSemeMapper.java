/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.InterventoPortaSeme;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterventoPortaSemeMapper implements RowMapper<InterventoPortaSeme>{

	@Override
	public InterventoPortaSeme mapRow(ResultSet rs, int arg1) throws SQLException {
		InterventoPortaSeme intervento = new InterventoPortaSeme();
		
		intervento.setIdIntervento(rs.getInt("id_intervento"));
		intervento.setIdPortaSeme(rs.getInt("idgeo_pt_portaseme"));
		intervento.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		intervento.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		intervento.setFkConfigUtente(rs.getInt("fk_config_utente"));


		return intervento;
	}

}
