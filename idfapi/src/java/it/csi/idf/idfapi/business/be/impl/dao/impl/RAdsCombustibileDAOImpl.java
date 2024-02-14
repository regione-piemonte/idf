/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.RAdsCombustibileDAO;
import it.csi.idf.idfapi.dto.AdsDatiStazionaliOne;
import it.csi.idf.idfapi.dto.AreaDiSaggio;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class RAdsCombustibileDAOImpl implements RAdsCombustibileDAO {

	String[] adsCombustibileColumns = { "idgeo_pt_ads", "cod_combustibile", "flg_principale", "perc_copertura_lettiera",
			"perc_copertura_erbacea", "perc_copertura_cespugli" };
	String tableName = "idf_r_ads_combustibile";
	String pkColumnName = "idgeo_pt_ads";

	@Override
	public void saveCombustibile(AdsDatiStazionaliOne adsDatiStazionaliOne) {

		if (!adsCombustibileExists(adsDatiStazionaliOne.getIdgeoPtAds())) {
			insertCombustibile(adsDatiStazionaliOne);
		} else {
			updateCombustibile(adsDatiStazionaliOne);
		}

	}

	private void insertCombustibile(AdsDatiStazionaliOne adsDatiStazionaliOne) {
		DBUtil.jdbcTemplate.update(DBUtil.createInsertQueryString(tableName, adsCombustibileColumns),
				getParametersForAllColumns(adsDatiStazionaliOne, false).toArray());
	}

	private void updateCombustibile(AdsDatiStazionaliOne adsDatiStazionaliOne) {
		DBUtil.jdbcTemplate.update(DBUtil.createUpdateQueryString(tableName, adsCombustibileColumns, pkColumnName),
				getParametersForAllColumns(adsDatiStazionaliOne, true).toArray());
	}

	public boolean adsCombustibileExists(Long idgeoPtAds) {
		String countSQL = DBUtil.createCountByPkQuery(tableName, pkColumnName);
		int count = DBUtil.jdbcTemplate.queryForInt(countSQL, idgeoPtAds);
		return count > 0;
	}

	private List<Object> getParametersForAllColumns(AdsDatiStazionaliOne adsDatiStazionaliOne, boolean updateQuery) {

		List<Object> parameters = new ArrayList<>();

		parameters.add(adsDatiStazionaliOne.getIdgeoPtAds());
		parameters.add("0");// mocked primary key
		parameters.add(1);// mocked primary key
		parameters.add(adsDatiStazionaliOne.getLettiera());
		parameters.add(adsDatiStazionaliOne.getCoperturaErbacea());
		parameters.add(adsDatiStazionaliOne.getCoperturaCespugli());

		if (updateQuery) {
			parameters.add(adsDatiStazionaliOne.getIdgeoPtAds());
		}

		return parameters;
	}

	public void deleteCompustibile(Long idgeoPtAds) {
		String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, pkColumnName);
		DBUtil.jdbcTemplate.update(sql, idgeoPtAds);
	}
}
