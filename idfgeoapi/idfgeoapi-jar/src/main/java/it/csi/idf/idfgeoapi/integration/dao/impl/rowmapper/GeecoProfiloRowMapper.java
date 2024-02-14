/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfgeoapi.dto.GeecoProfiloDto;

public class GeecoProfiloRowMapper implements RowMapper<GeecoProfiloDto>{

	@Override
	public GeecoProfiloDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		GeecoProfiloDto result = new GeecoProfiloDto();
		result.setIdGeecoProfilo(rs.getInt("id_geeco_profilo"));
		result.setDescrGeecoProfilo(rs.getString("descr_geeco_profilo"));
		result.setFkProcedura(rs.getInt("fk_procedura"));
		result.setUrlRitorno(rs.getString("url_ritorno"));
		result.setEnvInfo(rs.getString("env_info"));
		result.setFlgAutenticLetturaScrittura(rs.getString("flg_autentic_lettura_scrittura"));
		return result;
	}
	
	

}
