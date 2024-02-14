/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Delega;

public class DelegaMapper implements RowMapper<Delega> {

	@Override
	public Delega mapRow(ResultSet rs, int arg1) throws SQLException {
		Delega delega = new Delega();
		
		delega.setCfDelegante(rs.getString("cf_delegante"));
		delega.setIdConfigUtente(rs.getInt("id_config_utente"));
		delega.setDataFine(rs.getDate("data_fine") == null ? null : rs.getDate("data_fine").toLocalDate());
		delega.setDataInizio(rs.getDate("data_inizio") == null ? null : rs.getDate("data_inizio").toLocalDate());
		delega.setUidIndex(rs.getString("uid_index_delega"));
		delega.setFileName(rs.getString("nome_file_delega"));
		return delega;
	}
}
