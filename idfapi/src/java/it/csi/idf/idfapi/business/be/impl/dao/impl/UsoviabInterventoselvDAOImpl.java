/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import it.csi.idf.idfapi.business.be.impl.dao.UsoviabInterventoselvDAO;
import it.csi.idf.idfapi.util.DBUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsoviabInterventoselvDAOImpl implements UsoviabInterventoselvDAO {

	final static Logger logger = Logger.getLogger(UsoviabInterventoselvDAOImpl.class);

	@Override
	public int createIntervselUsovib(Integer fkUsoViabilita, Integer idIntervento) {

		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO idf_r_interventoselv_usoviab(");
		query.append("id_intervento, fk_uso_viabilita, data_inserimento) ");
		query.append("VALUES (?, ?, ?)");

		List<Object> parameters = new ArrayList<>();

		parameters.add(idIntervento);
		parameters.add(fkUsoViabilita);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));

		logger.info("into create usoviabInterselv");

		return DBUtil.jdbcTemplate.update(query.toString(), parameters.toArray());
	}

	@Override
	public void deleteIntervselUsovib(Integer idIntervento) {

		String sql = "DELETE FROM idf_r_interventoselv_usoviab WHERE id_intervento = ?";

		DBUtil.jdbcTemplate.update(sql, idIntervento);
	}

	@Override
	public void updateIntervselUsovib(Integer fkUsoViabilita, Integer idIntervento) {

		String sql = "UPDATE idf_r_interventoselv_usoviab SET fk_uso_viabilita = ?, data_aggiornamento = ?  where id_intervento = ?";

		List<Object> parameters = new ArrayList<>();
		
		parameters.add(fkUsoViabilita);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(sql, parameters.toArray());
		

	}
	
	@Override
	public boolean isInterventoPresente(Integer idIntervento) {
		String sql = "select count(*) from idf_r_interventoselv_usoviab where id_intervento = ?";
		int res = DBUtil.jdbcTemplate.queryForInt(sql.toString() ,idIntervento);
		return res > 0;
	}

	@Override
	public List<Integer> getAllByIntervento(Integer idIntervento) {
		String sql = "select fk_uso_viabilita from idf_r_interventoselv_usoviab where id_intervento = ?";
		return DBUtil.jdbcTemplate.queryForList(sql, new Object[] { idIntervento }, Integer.class);
	}
}
