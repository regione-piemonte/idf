/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ComuneResource;
import it.csi.idf.idfapi.dto.FatSoggetto;

public class FatBaseInfoSoggettoMapper implements RowMapper<FatSoggetto>{

	@Override
	public FatSoggetto mapRow(ResultSet rs, int arg1) throws SQLException {
		
		FatSoggetto soggetto = new FatSoggetto();
		soggetto.setIdSoggetto(rs.getInt("id_soggetto"));
		soggetto.setNome(rs.getString("nome"));
		soggetto.setCognome(rs.getString("cognome"));
		soggetto.setCodiceFiscale(rs.getString("codice_fiscale"));
		
		ComuneResource comune = new ComuneResource();
		comune.setIdComune(rs.getInt("id_comune"));
		comune.setDenominazioneComune(rs.getString("denominazione_comune"));
		comune.setIstatComune(rs.getString("istat_comune"));
		comune.setIstatProv(rs.getString("istat_prov"));
		soggetto.setComune(comune);
		return soggetto;
	}

}

