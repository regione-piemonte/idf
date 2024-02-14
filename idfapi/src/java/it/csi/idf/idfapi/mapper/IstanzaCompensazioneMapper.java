/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IstanzaCompensazione;

public class IstanzaCompensazioneMapper implements RowMapper<IstanzaCompensazione>{

	@Override
	public IstanzaCompensazione mapRow(ResultSet rs, int arg1) throws SQLException {
		IstanzaCompensazione istanzaCompensazione = new IstanzaCompensazione();
		
		istanzaCompensazione.setNumIstanzaCompensazione(rs.getInt("num_istanza_compensazione"));
		istanzaCompensazione.setFkIntervento(rs.getInt("fk_intervento"));
		istanzaCompensazione.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		istanzaCompensazione.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		istanzaCompensazione.setFkConfigUtente(rs.getInt("fk_config_utente"));
		istanzaCompensazione.setDataPresentazione(rs.getDate("data_presentazione") == null ? null : rs.getDate("data_presentazione").toLocalDate());
		istanzaCompensazione.setDataAutorizzazione(rs.getDate("data_autorizzazione") == null ? null : rs.getDate("data_autorizzazione").toLocalDate());
		
		return istanzaCompensazione;
	}

}
