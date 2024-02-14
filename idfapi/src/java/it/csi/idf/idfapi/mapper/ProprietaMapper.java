/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.Proprieta;

public class ProprietaMapper implements RowMapper<Proprieta>{
	@Override
	public Proprieta mapRow(ResultSet rs, int arg1) throws SQLException {
		Proprieta proprieta = new Proprieta();
		proprieta.setIdProprieta(rs.getInt("id_proprieta"));
		proprieta.setDescrProprieta(rs.getString("descr_proprieta"));
		proprieta.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		proprieta.setFlgVisibile(rs.getByte("flg_visibile"));
		proprieta.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		proprieta.setCodProprieta(rs.getString("cod_proprieta"));
		
		return proprieta;
	}

}
