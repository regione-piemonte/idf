/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfgeoapi.integration.dao.impl;

import java.io.*;
import java.sql.*;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import it.csi.idf.idfgeoapi.util.Constants;
import it.csi.util.performance.StopWatch;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Classe di base per tutti i DAO.
 * Le sottoclassi sono specifiche dei vari DataAccessObject e devono implementare tutti i 
 * metodi specifici
 * - finder
 * - updater
 * - deleter
 * Sono previsti dei metodi generici per:
 * - inserimento
 * - update
 * - cancellazione
 * Possono essere utilizzati per la definizione dei metodi specifici (i metodi sono parametrici
 * per quanto riguarda l'SQL).
 * @generated
 */
public abstract class AbstractDAO extends JdbcDaoSupport{
	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	/**
	 * contiene, se previsto dall'operativita' del DAO, l'incrementer utilizzato
	 * per ottenere il valore della PK numerica dell'elemento che si sta per inserire
	 * @generated
	 */
	protected DataFieldMaxValueIncrementer incrementer;

	/**
	 * Permette di impostare, se previsto, l'oggetto incrementer utilizzato
	 * per ottenere il valore della PK numerica dell'elemento che si sta per inserire
	 * @generated
	 */
	public void setIncrementer(DataFieldMaxValueIncrementer incrementer) {
		this.incrementer = incrementer;
	}

	/**
	 * Restituisce il nome della tabella specifica a cui il DAO fa riferimento.
	 * Deve essere implementato da ciascun DAO in modo da restituire la stringa
	 * che rappresenta il nome della tabella gestita dallo specifico DAO.
	 * @return String
	 * @generated
	 */
	public abstract String getTableName();

	/**
	 * Metodo di generico di inserimento (opera su qualsiasi specifico DAO).
	 * Esempio di codice del metodo specifico:
	 * 	public BandieraPk insert(BandieraDto dto){
	 *	  LOG.debug("[BandieraDaoImpl::insert] START");
	 *	  java.math.BigDecimal newKey = java.math.BigDecimal.valueOf(incrementer
	 *			.nextLongValue());
	
	 *	  final String sql = "INSERT INTO "
	 *			+ getTableName()
	 *			+ " ( 	ID_BANDIERA,DENOMINAZIONE,DATA_INSERIMENTO,DATA_FINE_VALIDITA ) VALUES (  :ID_BANDIERA , :DENOMINAZIONE , :DATA_INSERIMENTO , :DATA_FINE_VALIDITA  )";
	
	 *	  MapSqlParameterSource params = new MapSqlParameterSource();
	
	 *	  params.addValue("ID_BANDIERA", newKey, java.sql.Types.DECIMAL);
	
	 *	  params.addValue("DENOMINAZIONE", dto.getDenominazione(),
	 *			java.sql.Types.VARCHAR);
	
	 *	  params.addValue("DATA_INSERIMENTO", dto.getDataInserimento(),
	 *			java.sql.Types.DATE);
	
	 *	  params.addValue("DATA_FINE_VALIDITA", dto.getDataFineValidita(),
	 *			java.sql.Types.DATE);
	
	 *	  insert(jdbcTemplate, sql.toString(), params);
	
	 *	  dto.setIdBandiera(newKey);
	 *	  LOG.debug("[BandieraDaoImpl::insert] END");
	 *	  return dto.createPk();
	 *  }
	 *
	 * @param jdbcTemplate
	 * @param sql
	 * @param params  
	 */
	public void insert(NamedParameterJdbcTemplate jdbcTemplate, String sql, MapSqlParameterSource params) {
		String actualDaoClassName = this.getClass().getSimpleName();
		LOG.debug("[AbstractDAO::insert] START (" + actualDaoClassName + ")");
		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
		try {
			stopWatch.start();
			jdbcTemplate.update(sql.toString(), params);
		} catch (RuntimeException ex) {
			LOG.error("[AbstractDAO::insert] ERROR (" + actualDaoClassName + ") esecuzione query", ex);
			throw ex;
		} finally {
			stopWatch.dumpElapsed("AbstractDAO", "insert", "esecuzione query", sql);
			LOG.debug("[AbstractDAO::insert] END (" + actualDaoClassName + ")");
		}
	}

	/**
	 * Metodo di generico di aggiornamento dati (opera su qualsiasi specifico DAO).
	 * Esempio di codice della sottoclasse:
	 * public void updateColumnsUpdate(BandieraDto dto)
	 *		throws BandieraDaoException {
	 *	LOG.debug("[BandieraDaoImpl::updateColumnsUpdate] START");
	 *	final String sql = "UPDATE "
	 *			+ getTableName()
	 *			+ " SET DENOMINAZIONE = :DENOMINAZIONE  WHERE ID_BANDIERA = :ID_BANDIERA ";
	 *	if (dto.getIdBandiera() == null) {
	 *		LOG.error("[BandieraDaoImpl::updateColumnsUpdate] ERROR chiave primaria non impostata");
	 *		throw new BandieraDaoException("Chiave primaria non impostata");
	 *	}
	 *  MapSqlParameterSource params = new MapSqlParameterSource();
	 *	params.addValue("DENOMINAZIONE", dto.getDenominazione(),
	 *			java.sql.Types.VARCHAR);
	 *	params.addValue("ID_BANDIERA", dto.getIdBandiera(),
	 *			java.sql.Types.DECIMAL);
	 *	update(jdbcTemplate, sql.toString(), params);
	 *	LOG.debug("[BandieraDaoImpl::updateColumnsUpdate] END");
	 * }
	 * @param jdbcTemplate
	 * @param sql
	 * @param params  
	 */
	public void update(NamedParameterJdbcTemplate jdbcTemplate, String sql, MapSqlParameterSource params) {
		String actualDaoClassName = this.getClass().getSimpleName();
		LOG.debug("[AbstractDAO::update] START (" + actualDaoClassName + ")");
		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
		try {
			stopWatch.start();
			jdbcTemplate.update(sql.toString(), params);
		} catch (RuntimeException ex) {
			LOG.error("[AbstractDAO::update] ERROR (" + actualDaoClassName + ") esecuzione query", ex);
			throw ex;
		} finally {
			stopWatch.dumpElapsed("AbstractDAO", "update", "esecuzione query", sql);
			LOG.debug("[AbstractDAO::update] END (" + actualDaoClassName + ")");
		}
	}

	/**
	 * Metodo di generico di cancellazione (opera su qualsiasi specifico DAO).
	 * Esempio di codice della implementazione specifica:
	 *	public void delete(BandieraPk pk) throws BandieraDaoException {
	 *	LOG.debug("[BandieraDaoImpl::delete] START");
	 *	final String sql = "DELETE FROM " + getTableName()
	 *			+ " WHERE ID_BANDIERA = :ID_BANDIERA ";
	 *	if (pk == null) {
	 *		LOG.error("[BandieraDaoImpl::delete] ERROR chiave primaria non impostata");
	 *		throw new BandieraDaoException("Chiave primaria non impostata");
	 *	}
	 *	MapSqlParameterSource params = new MapSqlParameterSource();
	 *	params.addValue("ID_BANDIERA", pk.getIdBandiera(),
	 *			java.sql.Types.DECIMAL);
	 *	delete(jdbcTemplate, sql.toString(), params);
	 *	LOG.debug("[BandieraDaoImpl::delete] END");
	 * }
	 * @param jdbcTemplate
	 * @param sql
	 * @param params  
	 */
	public void delete(NamedParameterJdbcTemplate jdbcTemplate, String sql, MapSqlParameterSource params) {
		String actualDaoClassName = this.getClass().getSimpleName();
		LOG.debug("[AbstractDAO::delete] START (" + actualDaoClassName + ")");
		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
		try {
			stopWatch.start();
			jdbcTemplate.update(sql.toString(), params);
		} catch (RuntimeException ex) {
			LOG.error("[AbstractDAO::delete] ERROR (" + actualDaoClassName + ") esecuzione query", ex);
			throw ex;
		} finally {
			stopWatch.dumpElapsed("AbstractDAO", "delete", "esecuzione query", sql);
			LOG.debug("[AbstractDAO::delete] END (" + actualDaoClassName + ")");
		}
	}

	// there is no specific code for POSGRESQL

}
