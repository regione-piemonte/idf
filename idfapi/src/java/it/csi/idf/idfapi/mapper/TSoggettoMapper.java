/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TSoggetto;
import it.csi.idf.idfapi.util.DBUtil;


public class TSoggettoMapper implements RowMapper<TSoggetto>{
	
	
	@Override
	public TSoggetto mapRow(ResultSet rs, int arg1) throws SQLException {
		
		TSoggetto soggetto = new TSoggetto();
		
		soggetto.setIdSoggetto(rs.getInt("id_soggetto"));
		soggetto.setFkComune(DBUtil.getIntegerValueFromResultSet(rs, "fk_comune"));
		soggetto.setNome(rs.getString("nome"));
		soggetto.setCognome(rs.getString("cognome"));
		soggetto.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggetto.setPartitaIva(rs.getString("partita_iva"));
		soggetto.setDenominazione(rs.getString("denominazione"));
		soggetto.setIndirizzo(rs.getString("indirizzo"));
		soggetto.setNrTelefonico(rs.getString("nr_telefonico"));
		soggetto.seteMail(rs.getString("e_mail"));
		soggetto.setPec(rs.getString("pec"));
		soggetto.setnIscrizioneOrdine(rs.getString("n_iscrizione_ordine"));
		soggetto.setIstatProvIscrizioneOrdine(rs.getObject("istat_prov_iscrizione_ordine")==null ? null : rs.getString("istat_prov_iscrizione_ordine"));
		
		soggetto.setIstatProvCompetenzaTerr(rs.getObject("istat_prov_competenza_terr")==null ? null : rs.getString("istat_prov_competenza_terr") );
		soggetto.setFlgSettoreRegionale(rs.getByte("flg_settore_regionale"));
		soggetto.setDataInserimento(rs.getString("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		soggetto.setDataAggiornamento(rs.getString("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		soggetto.setFkConfigUtente(DBUtil.getIntegerValueFromResultSet(rs,"fk_config_utente"));
		soggetto.setCivico(rs.getString("civico"));
		soggetto.setCap(rs.getString("cap"));
		soggetto.setFkCategoriaProfessionale(rs.getInt("fk_categoria_professionale"));


		try {
			soggetto.setFlgSportello(rs.getByte("flg_sportello"));
		} catch (SQLException e) {}

		try {
			soggetto.setFlgGestore(rs.getByte("flg_gestore"));
		} catch (SQLException e) {}

		try {
			soggetto.setFlgEntePubblco(rs.getByte("flg_ente_pubblico"));
		} catch (SQLException e) {}

		try {
			soggetto.setNrMartelloForestale(rs.getString("nr_martello_forestale"));
		} catch (SQLException e) {}

		try {
			soggetto.setNrAlboForestale(rs.getString("nr_albo_forestale"));
		} catch (SQLException e) {}


		return soggetto;
	}
}
