/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.impl.dao.EsboscoIntervselvDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class EsboscoIntervselvDAOImpl implements EsboscoIntervselvDAO {
	
	final static Logger logger = Logger.getLogger(EsboscoIntervselvDAOImpl.class);


	@Override
	public int createIntervselvEsbosco(String codEsbosco, Integer idIntervento) {

        StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO idf_r_intervselv_esbosco(");
		query.append("id_intervento, cod_esbosco, data_inserimento) ");
		query.append("VALUES (?, ?, ? )");
		
        List<Object>parameters = new ArrayList<>();
		
		parameters.add(idIntervento);
		parameters.add(codEsbosco);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		
		logger.info("into createInterselvEsbosco");
		
		return DBUtil.jdbcTemplate.update(query.toString(),parameters.toArray());
		
	}

	@Override
	public void deleteIntervselvEsbosco(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_r_intervselv_esbosco WHERE id_intervento = ?";
		
		DBUtil.jdbcTemplate.update(sql,idIntervento);
		
	}

	@Override
	public void updateIntervselvEsbosco(String codEsbosco, Integer idIntervento) {
		
		String sql = "UPDATE idf_r_intervselv_esbosco SET cod_esbosco =?, data_aggiornamento = ?  where id_intervento =?";
		
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(codEsbosco);
		parameters.add(java.sql.Date.valueOf(LocalDate.now()));
		parameters.add(idIntervento);
		
		DBUtil.jdbcTemplate.update(sql, parameters.toArray());
		
	}
	
	
	@Override
	public boolean isInterventoPresente(Integer idIntervento) {
		String sql = "select count(*) from idf_r_intervselv_esbosco where id_intervento = ?";
		int res = DBUtil.jdbcTemplate.queryForInt(sql.toString() ,idIntervento);
		return res > 0;
	}


	@Override
	public List<String> findAllCodesByIntervento(Integer idIntervento) {
		String sql = "select cod_esbosco from idf_r_intervselv_esbosco where id_intervento = ?";
		return DBUtil.jdbcTemplate.queryForList(sql, new Object[] { idIntervento }, String.class);
	}
}
