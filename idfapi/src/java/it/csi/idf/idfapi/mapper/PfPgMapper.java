/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PfPg;

public class PfPgMapper implements RowMapper<PfPg>{

	@Override
	public PfPg mapRow(ResultSet rs, int arg1) throws SQLException {
		PfPg pfPg = new PfPg();
		
		pfPg.setIdSoggettoPf(rs.getInt("id_soggetto_pf"));
		pfPg.setIdSoggettoPg(rs.getInt("id_soggetto_pg"));
		pfPg.setDataInizioValidita(
				rs.getDate("data_inizio_validita") == null ? null : rs.getDate("data_inizio_validita").toLocalDate());
		pfPg.setDataFineValidita(
				rs.getDate("data_fine_validita") == null ? null : rs.getDate("data_fine_validita").toLocalDate());
		pfPg.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		pfPg.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		pfPg.setFkConfigUtente(rs.getInt("fk_config_utente"));
		
		return pfPg;
	}
}
