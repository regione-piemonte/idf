/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.RelascopicaSempliceDAO;
import it.csi.idf.idfapi.dto.PlainRelascopicaSemplice;
import it.csi.idf.idfapi.mapper.PlainRelascopicaMapper;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class RelascopicaSempliceDAOImpl implements RelascopicaSempliceDAO {

	String[] columnAlberiCAV = { "fattore_numerazione", "fk_tipo_strutturale_princ", "note" };

	String tableName = "idf_t_ads_relascopica";
	String pkColumnName = "idgeo_pt_ads";

	@Override
	public int saveRelascopicaSemplice(PlainRelascopicaSemplice plainRelascopicaSemplice) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_t_ads_relascopica (");
		sql.append(" idgeo_pt_ads, fattore_numerazione, fk_tipo_strutturale_princ, note) ");
		sql.append("VALUES(?,?,?,?)");

		List<Object> parameters = new ArrayList<>();
		parameters.add(plainRelascopicaSemplice.getIdgeoPtAds());
		parameters.add(plainRelascopicaSemplice.getFattoreNumerazione());
		parameters.add(plainRelascopicaSemplice.getFkTipoStrutturalePrinc());
		parameters.add(plainRelascopicaSemplice.getNote());

		return DBUtil.jdbcTemplate.update(sql.toString(), parameters.toArray());
	}

	public int updateRelascopica(PlainRelascopicaSemplice plainRelascopicaSemplice) {
		List<Object> parameters = new ArrayList<>();

		parameters.add(plainRelascopicaSemplice.getFattoreNumerazione());
		parameters.add(plainRelascopicaSemplice.getFkTipoStrutturalePrinc());
		parameters.add(plainRelascopicaSemplice.getNote());
		parameters.add(plainRelascopicaSemplice.getIdgeoPtAds());

		return DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, columnAlberiCAV, pkColumnName),
				parameters.toArray());

	}

	public int deleteByCodiceAdsCAV(Long idgeoPtAds) {
		String SQL = "DELETE FROM idf_r_adsrel_specie where idgeo_pt_ads = ? and data_fine_validita is null and cod_tipo_campione='CAV'";
		return DBUtil.jdbcTemplate.update(SQL, idgeoPtAds);
	}

	public PlainRelascopicaSemplice getPlainRelascopicaSemplice(String codiceAds) {
		String sql = "SELECT * FROM idf_t_ads_relascopica where idgeo_pt_ads = ?";
		Object[] params = { Long.parseLong(codiceAds) };
		return DBUtil.jdbcTemplate.queryForObject(sql, params, new PlainRelascopicaMapper());

	}

	public boolean relascopicaExists(Long codiceAds) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM idf_t_ads_relascopica where idgeo_pt_ads = ?");
		return DBUtil.jdbcTemplate.queryForInt(sql.toString(), codiceAds) > 0;

	}
	
	public void deleteRelascopica(Long idgeoPtAds) {
			String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, pkColumnName);
			DBUtil.jdbcTemplate.update(sql, idgeoPtAds);		
	}

//	@Override
//	public List<PlainAdsrelSpecie> searchAdsRelSpecByCodiceADSForCAV(String codiceADS) {
//		String SQL = "SELECT * FROM idf_r_adsrel_specie where idgeo_pt_ads = ? and data_fine_validita is null and cod_tipo_campione='CAV'";
//		return DBUtil.jdbcTemplate.query(SQL, new AdsrelSpecieMapper(), Long.parseLong(codiceADS));	
//	}

}
