/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.CnfBoprocLogDAO;
import it.csi.idf.idfapi.dto.BoProcLog;
import it.csi.idf.idfapi.util.AIKeyHolderUtil;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class CnfBoprocLogDAOImpl implements CnfBoprocLogDAO {

	@Override
	public int create(BoProcLog log) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO idf_cnf_boproc_log(");
		sql.append("fk_istanza,fk_ambito_istanza,data_inizio,fk_step_boproc,count_tentativo)");
		sql.append(" VALUES (?, ?, ?, ?, ?)");
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Object>parameters = new ArrayList<>();
		parameters.add(log.getFkIstanza());
		parameters.add(log.getFkAmbitoIstanza());
		parameters.add(now);
		parameters.add(log.getFkStepBoproc());
		parameters.add(log.getCountTentativo());
		
		return AIKeyHolderUtil.updateWithGenKey("id_cnf_boproc_log", sql.toString(), parameters);
	}

	@Override
	public String getLastFkIstanzaByidIstanza(Integer idIstanza) {
		StringBuilder sql = new StringBuilder();
		sql.append("select fk_istanza from idf_cnf_boproc_log  ");
		sql.append("where fk_istanza like ? or fk_istanza = ? ");
		sql.append("order by id_cnf_boproc_log desc ");
		List<String> res = DBUtil.jdbcTemplate.queryForList(sql.toString(), String.class, 
				idIstanza + "_%",idIstanza.toString());
		if(res.size() > 0) {
			return res.get(0);
		}
		return null;
	}

}
