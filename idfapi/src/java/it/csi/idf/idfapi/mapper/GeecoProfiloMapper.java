/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.GeecoProfilo;

public class GeecoProfiloMapper implements RowMapper<GeecoProfilo>{

	@Override
	public GeecoProfilo mapRow(ResultSet rs, int rowNum) throws SQLException {
		GeecoProfilo result = new GeecoProfilo();
		result.setIdGeecoProfilo(rs.getInt("id_geeco_profilo"));
		result.setDescrGeecoProfilo(rs.getString("descr_geeco_profilo"));
		result.setFkProcedura(rs.getInt("fk_procedura"));
		result.setUrlRitorno(rs.getString("url_ritorno"));
		result.setEnvInfo(rs.getString("env_info"));
		result.setFlgAutenticLetturaScrittura(rs.getString("flg_autentic_lettura_scrittura"));
		result.setUuid(rs.getString("uuid"));
		result.setVersion(rs.getString("versione"));
		return result;
	}
}
