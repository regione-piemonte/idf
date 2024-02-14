/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.ValidationException;
import it.csi.idf.idfapi.business.be.impl.dao.IntervselEventoDAO;
import it.csi.idf.idfapi.util.DBUtil;

@Component
public class IntervselEventoDAOImpl implements IntervselEventoDAO {
	
	final static Logger logger = Logger.getLogger(IntervselEventoDAOImpl.class);

	@Override
	public int createIntervselEvento(Integer idEvento, Integer idIntervento) {

		String sql = "INSERT INTO idf_r_intervselv_evento(\r\n" + 
				"	id_evento, id_intervento, data_inserimento)\r\n" + 
				"	VALUES (?, ?, ?);";
		
		List<Object>parameters = new ArrayList<>();
		parameters.add(idEvento);
		parameters.add(idIntervento);
		parameters.add(Date.valueOf(LocalDate.now()));
		
		logger.info("into createIntervselvEvento");
		
		return DBUtil.jdbcTemplate.update(sql,parameters.toArray());
	}
	
	@Override
	public int createIntervselEventoNEW(Integer idEvento, Integer idIntervento, Integer idConfigUtente) throws ValidationException {

		String sql = "INSERT INTO idf_r_intervselv_evento(" + 
				"	id_evento, id_intervento, data_inserimento, fk_config_utente)" + 
				"	VALUES (?, ?, ?, ?);";
		
		List<Object>parameters = new ArrayList<>();
		parameters.add(idEvento);
		parameters.add(idIntervento);
		parameters.add(Date.valueOf(LocalDate.now()));
		parameters.add(idConfigUtente);
		
		logger.info("createIntervselvEvento sql: " + sql + " - idEvento: " + idEvento + " - idIntervento: " + idIntervento
				+ " - date: sysdate - idConfigUtente: " + idConfigUtente);
		try {
			return DBUtil.jdbcTemplate.update(sql,parameters.toArray());
		}catch(DataIntegrityViolationException de){
			de.printStackTrace();
			throw new ValidationException(de.getClass() + ":BAD INPUT DATA,CHECK YOUR REQUEST PAYLOAD");
		}
		
	}

	@Override
	public void deleteIntervselEvento(Integer idIntervento) {
		
		String sql = "DELETE FROM idf_r_intervselv_evento WHERE id_intervento = ? ";
		
		DBUtil.jdbcTemplate.update(sql, idIntervento);
		
	}

	@Override
	public void updateIntervselEvento(Integer idEvento, Integer idIntervento) throws DataIntegrityViolationException{
		String sql = "UPDATE idf_r_intervselv_evento SET id_evento = ? WHERE id_intervento = ?";
		
		List<Object>parameters = new ArrayList<>();
		parameters.add(idEvento);
		parameters.add(idIntervento);
		
		try {
		DBUtil.jdbcTemplate.update(sql, parameters.toArray());
		}catch(DataIntegrityViolationException de) {
			throw new DataIntegrityViolationException("ID EVENTO DOES NOT FIT TO THIS PFA OR DOES NOT EXIST AT ALL");
		}
	}

	@Override
	public boolean exitsInterventoEvento(Integer idIntervento) {
		String sql = "SELECT COUNT(*) FROM idf_r_intervselv_evento WHERE id_intervento = ?";

		int res = DBUtil.jdbcTemplate.queryForInt(sql, idIntervento);
		return res > 0;
	}

}
