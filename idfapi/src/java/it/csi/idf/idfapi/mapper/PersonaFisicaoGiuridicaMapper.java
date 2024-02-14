/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.GeoPLProvinciaSearch;
import it.csi.idf.idfapi.dto.PersonaFisGiu;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaFisicaoGiuridicaMapper implements RowMapper<PersonaFisGiu>{

	@Override
	public PersonaFisGiu mapRow(ResultSet rs, int arg1) throws SQLException {

		PersonaFisGiu soggetto = new PersonaFisGiu();
		soggetto.setIdSoggetto(rs.getInt("id_soggetto"));
		soggetto.setNome(rs.getString("nome"));
		soggetto.setCognome(rs.getString("cognome"));
		soggetto.setCodiceFiscale(rs.getString("codice_fiscale"));

		soggetto.setPartitaIva(rs.getString("partita_iva"));
		soggetto.setDenominazione(rs.getString("denominazione"));
		soggetto.setIndirizzo(rs.getString("indirizzo"));
		soggetto.setNrTelefonico(rs.getString("nr_telefonico"));
		soggetto.seteMail(rs.getString("e_mail"));
		soggetto.setPec(rs.getString("pec"));
		soggetto.setCivico(rs.getString("civico"));
		soggetto.setCap(rs.getString("cap"));

		ComuneResource comune = new ComuneResource();
		comune.setIdComune(rs.getInt("id_comune"));
		comune.setDenominazioneComune(rs.getString("denominazione_comune"));
		comune.setIstatComune(rs.getString("istat_comune"));
		comune.setIstatProv(rs.getString("istat_prov"));
		soggetto.setComune(comune);


		try {
			soggetto.setNrMartelloForestale(rs.getString("nr_martello_forestale"));
		} catch (SQLException e) {}


		try {
			soggetto.setNrIscrizioneOrdine(rs.getString("n_iscrizione_ordine"));
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

