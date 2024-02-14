/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.InterventoCatfor;

public class InterventoCatforMapper implements RowMapper<InterventoCatfor>{

	@Override
	public InterventoCatfor mapRow(ResultSet rs, int arg1) throws SQLException {
		InterventoCatfor interventoCatfor = new InterventoCatfor();

		interventoCatfor.setIdIntervento(rs.getInt("id_intervento"));
		interventoCatfor.setIdCategoriaForestale(rs.getInt("id_categoria_forestale"));
		interventoCatfor.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		interventoCatfor.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		interventoCatfor.setFkConfigUtente(rs.getInt("fk_config_utente"));

		return interventoCatfor;
	}
}
