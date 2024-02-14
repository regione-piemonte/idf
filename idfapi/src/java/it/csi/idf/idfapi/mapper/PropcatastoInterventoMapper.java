/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.PropcatastoIntervento;

public class PropcatastoInterventoMapper implements RowMapper<PropcatastoIntervento>{

	@Override
	public PropcatastoIntervento mapRow(ResultSet rs, int arg1) throws SQLException {
		PropcatastoIntervento propcatastoIntervento = new PropcatastoIntervento();
		
		propcatastoIntervento.setIdgeoPlPropCatasto(rs.getInt("idgeo_pl_prop_catasto"));
		propcatastoIntervento.setIdIntervento(rs.getInt("id_intervento"));
		propcatastoIntervento.setDataInserimento(
				rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		propcatastoIntervento.setDataAggiornamento(
				rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		propcatastoIntervento.setFkConfigUtente(rs.getInt("fk_config_utente"));
		
		return propcatastoIntervento;
	}

}
