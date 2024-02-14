/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoStrutturale;

public class TipoStrutturaleMapper implements RowMapper<TipoStrutturale>{
	
	@Override
	public TipoStrutturale mapRow(ResultSet rs, int arg1) throws SQLException {
		TipoStrutturale tipoStrutturale = new TipoStrutturale();
		tipoStrutturale.setIdTipoStrutturale(rs.getInt("id_tipo_strutturale"));
		tipoStrutturale.setDescrTipoStrutturale(rs.getString("descr_tipo_strutturale"));
		tipoStrutturale.setCodTipoStrutturale(rs.getString("cod_tipo_strutturale"));
		tipoStrutturale.setFkConfigIpla(rs.getInt("fk_config_ipla"));
		tipoStrutturale.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		tipoStrutturale.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return tipoStrutturale;
	}

}
