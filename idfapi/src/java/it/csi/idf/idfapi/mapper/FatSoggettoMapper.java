/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;
import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.FatSoggetto;

public class FatSoggettoMapper implements RowMapper<FatSoggetto>{

	@Override
	public FatSoggetto mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FatSoggetto soggetto = new FatSoggetto();
		soggetto.setIdSoggetto(rs.getInt("id_soggetto"));
		
		ComuneResource comune = new ComuneResource();
		comune.setIdComune(rs.getInt("id_comune"));
		comune.setDenominazioneComune(rs.getString("denominazione_comune"));
		comune.setIstatComune(rs.getString("istat_comune"));
		comune.setIstatProv(rs.getString("istat_prov"));
		soggetto.setComune(comune);
		
		soggetto.setNome(rs.getString("nome"));
		soggetto.setCognome(rs.getString("cognome"));
		soggetto.setCodiceFiscale(rs.getString("codice_fiscale"));
		soggetto.setPartitaIva(rs.getString("partita_iva"));
		soggetto.setDenominazione(rs.getString("denominazione"));
		soggetto.setIndirizzo(rs.getString("indirizzo"));
		soggetto.setNrTelefonico(rs.getString("nr_telefonico"));
		soggetto.seteMail(rs.getString("e_mail"));
		soggetto.setPec(rs.getString("pec"));
		soggetto.setNrIscrizioneOrdine(rs.getString("n_iscrizione_ordine"));
		soggetto.setIstatProvIscrizioneOrdine(rs.getString("istat_prov_iscrizione_ordine"));
		soggetto.setIstatProvCompetenzaTerr(rs.getString("istat_prov_competenza_terr"));
		soggetto.setFlgSettoreRegionale(rs.getByte("flg_settore_regionale"));
		soggetto.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		soggetto.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		soggetto.setFkConfigUtente( ( rs.getString("fk_config_utente")==null ) ? null : rs.getInt("fk_config_utente"));

		soggetto.setCivico(rs.getString("civico"));
		soggetto.setCap(rs.getString("cap"));
		soggetto.setFlgSportello(rs.getByte("flg_sportello"));
		soggetto.setFlgEntePubblco(rs.getByte("flg_ente_pubblico"));
		soggetto.setFlgGestore(rs.getByte("flg_gestore"));


		try {
			soggetto.setNrMartelloForestale(rs.getString("nr_martello_forestale"));
		} catch (SQLException e) {}


		try {
			soggetto.setNrIscrizioneOrdine(rs.getString("n_iscrizione_ordine"));
			soggetto.setnIscrizioneOrdine(rs.getString("n_iscrizione_ordine"));
		} catch (SQLException e) {}

		try {
			GeoPLProvinciaSearch provincia = new GeoPLProvinciaSearch();
			provincia.setIstatProv(rs.getString("istat_prov_iscrizione_ordine"));
			provincia.setSiglaProv(rs.getString("sigla_prov"));
			provincia.setDenominazioneProv(rs.getString("denominazione_prov"));
			soggetto.setProvIscrizioneOrdine(provincia);
		} catch (SQLException e) {}


		return soggetto;
	}

}
