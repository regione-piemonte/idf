/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Intervento;

public class InterventoMapper implements RowMapper<Intervento> {

	@Override
	public Intervento mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Intervento intervento = new Intervento();
		
		intervento.setIdIntervento(rs.getInt("id_intervento"));
		intervento.setSuperficieInteressata(rs.getBigDecimal("superficie_interessata_ha"));
		intervento.setLocalita(rs.getString("localita"));
		intervento.setDescrizioneIntervento(rs.getString("descrizione_intervento"));
		intervento.setFkSoggettoProfess(rs.getInt("fk_soggetto_profess"));
		intervento.setDataInizioIntervento(rs.getDate("data_inizio_intervento") == null ? null : rs.getDate("data_inizio_intervento").toLocalDate());
		intervento.setDataFineIntervento(rs.getDate("data_fine_intervento") == null ? null : rs.getDate("data_fine_intervento").toLocalDate());
		intervento.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		intervento.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		intervento.setFkConfigUtenteCompilatore(rs.getInt("fk_config_utente_compilatore"));
		
		return intervento;
	}

	
}
