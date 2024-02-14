/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.StatoAds;
import it.csi.idf.idfapi.util.DBUtil;

public class StatoAdsMapper implements RowMapper<StatoAds>{

	@Override
	public StatoAds mapRow(ResultSet rs, int arg1) throws SQLException {
		
		StatoAds statoAds = new StatoAds();
		statoAds.setIdStatoAds(DBUtil.getIntegerValueFromResultSet(rs, "id_stato_ads"));
		statoAds.setDescrStatoAds(rs.getString("descr_stato_ads"));
		statoAds.setMtdOrdinamento(rs.getInt("mtd_ordinamento"));
		statoAds.setFlgVisibile(rs.getByte("flg_visibile"));
		
		if (statoAds.getIdStatoAds()==null) {
			statoAds = null;
		}
		return statoAds;
	}

}
