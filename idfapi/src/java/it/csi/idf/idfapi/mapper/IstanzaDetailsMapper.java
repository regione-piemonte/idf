/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.IstanzaDetails;

public class IstanzaDetailsMapper implements RowMapper<IstanzaDetails> {

	@Override
	public IstanzaDetails mapRow(ResultSet rs, int arg1) throws SQLException {
		IstanzaDetails istanzaDetails = new IstanzaDetails();

		istanzaDetails.setDescProprietaPrimpa(rs.getString("descr_proprieta"));
		istanzaDetails.setNumeroIstanza(rs.getString("nr_progressivo_interv"));
		
		istanzaDetails.setTipoDiComunicazione("C".equals(rs.getString("flg_conforme_deroga")) ? "Comunicazione semplice" : "Autorizzazione");
		
		
		istanzaDetails.setDataDiCompilazione(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		istanzaDetails.setDataPrevistaPerInizio(rs.getDate("data_presa_in_carico") == null ? null
				: rs.getDate("data_presa_in_carico").toLocalDate());
		

		return istanzaDetails;
	}

}
