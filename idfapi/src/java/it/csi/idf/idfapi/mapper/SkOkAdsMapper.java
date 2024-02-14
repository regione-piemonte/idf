/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.SkOkAds;

public class SkOkAdsMapper implements RowMapper<SkOkAds>{

	@Override
	public SkOkAds mapRow(ResultSet rs, int arg1) throws SQLException {
		SkOkAds skOkAds = new SkOkAds();
		
		skOkAds.setIdgeoPtAds(rs.getInt("idgeo_pt_ads"));
		skOkAds.setFlgSez1(rs.getByte("flg_sez1"));
		skOkAds.setFlgSez2(rs.getByte("flg_sez2"));
		skOkAds.setFlgSez3(rs.getByte("flg_sez3"));
		skOkAds.setFlgSez4(rs.getByte("flg_sez4"));
	

		return skOkAds;
	}
}
