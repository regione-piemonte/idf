/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.jdbc.core.RowMapper;

import it.csi.idf.idfapi.dto.RAdsrelSpecie;
import it.csi.idf.idfapi.util.DBUtil;

public class AdsrelSpecieMapper implements RowMapper<RAdsrelSpecie> {
	public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC")); 
	@Override
	public RAdsrelSpecie mapRow(ResultSet rs, int arg1) throws SQLException {

		RAdsrelSpecie rAdsrelSpecie = new RAdsrelSpecie();

		rAdsrelSpecie.setIdSpecie(rs.getLong("id_specie"));
		rAdsrelSpecie.setIdgeoPtAds(rs.getLong("idgeo_pt_ads"));
		rAdsrelSpecie.setDataInizioValidita(rs.getTimestamp("data_inizio_validita"));
		rAdsrelSpecie.setCodTipoCampione(rs.getString("cod_tipo_campione"));
		rAdsrelSpecie.setFlgPolloneSeme(rs.getString("flg_pollone_seme"));
		rAdsrelSpecie.setQualita(rs.getString("qualita"));
		rAdsrelSpecie.setDiametro(DBUtil.getIntegerValueFromResultSet(rs, "diametro"));
		rAdsrelSpecie.setAltezza(DBUtil.getIntegerValueFromResultSet(rs, "altezza"));
		rAdsrelSpecie.setIncremento(DBUtil.getIntegerValueFromResultSet(rs, "incremento"));
		rAdsrelSpecie.setEta(DBUtil.getIntegerValueFromResultSet(rs, "eta"));
		rAdsrelSpecie.setNrAlberiPollone(DBUtil.getIntegerValueFromResultSet(rs, "nr_alberi_pollone"));
		rAdsrelSpecie.setNrAlberiSeme(DBUtil.getIntegerValueFromResultSet(rs, "nr_alberi_seme"));
		rAdsrelSpecie.setDataFineValidita(rs.getDate("data_fine_validita")== null ? null : rs.getDate("data_fine_validita").toLocalDate());		
		rAdsrelSpecie.setNote(rs.getString("note"));
		
	return rAdsrelSpecie;
	
	}
}
