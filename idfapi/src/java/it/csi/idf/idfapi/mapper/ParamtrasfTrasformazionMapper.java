/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.ParamtrasfTrasformazion;

public class ParamtrasfTrasformazionMapper implements RowMapper<ParamtrasfTrasformazion>{

	@Override
	public ParamtrasfTrasformazion mapRow(ResultSet rs, int arg1) throws SQLException {
		ParamtrasfTrasformazion paramtrasfTrasformazion = new ParamtrasfTrasformazion();
		
		paramtrasfTrasformazion.setIdParameroTrasf(rs.getInt("id_parametro_trasf"));
		paramtrasfTrasformazion.setIdIntervento(rs.getInt("id_intervento"));
		paramtrasfTrasformazion.setDataInserimento(rs.getDate("data_inserimento") == null ? null : rs.getDate("data_inserimento").toLocalDate());
		paramtrasfTrasformazion.setDataAggiornamento(rs.getDate("data_aggiornamento") == null ? null : rs.getDate("data_aggiornamento").toLocalDate());
		paramtrasfTrasformazion.setFkConfigUtente(rs.getInt("fk_config_utente"));
		
		return paramtrasfTrasformazion;
	}
}
