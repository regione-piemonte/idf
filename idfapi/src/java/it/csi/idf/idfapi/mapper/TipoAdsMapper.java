/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.TipoAds;

public class TipoAdsMapper implements RowMapper<TipoAds>{

	@Override
	public TipoAds mapRow(ResultSet rs, int arg1) throws SQLException {
		
		TipoAds tipoAds = new TipoAds();
		tipoAds.setIdTipoAds(rs.getInt("id_tipo_ads"));
		tipoAds.setCodTipoAds(rs.getString("cod_tipo_ads"));
		tipoAds.setDescrTipoAds(rs.getString("descr_tipo_ads"));
		tipoAds.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		tipoAds.setFlgVisibile(rs.getByte("flg_visibile"));
		
		return tipoAds;
	}

}
