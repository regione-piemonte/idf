/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Destinazione;

public class DestinazioneMapper implements RowMapper<Destinazione>{
	@Override
	public Destinazione mapRow(ResultSet rs, int arg1) throws SQLException {
		Destinazione destinazione = new Destinazione();
		destinazione.setIdDestinazione(rs.getInt("id_destinazione"));
		destinazione.setDescrDestinazione(rs.getString("descr_destinazione"));
		destinazione.setCodDestinazione(rs.getString("cod_destinazione"));
		destinazione.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		destinazione.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		destinazione.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return destinazione;
	}
}
