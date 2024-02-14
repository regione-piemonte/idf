/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import it.csi.idf.idfapi.business.be.exceptions.dao.ApiManagerDaoException;
import it.csi.idf.idfapi.business.be.impl.dao.ApiManagerDao;
import it.csi.idf.idfapi.dto.ApiManagerDto;
import it.csi.idf.idfapi.dto.ApiManagerPk;
import it.csi.idf.idfapi.mapper.ApiManagerDaoRowMapper;
import it.csi.idf.idfapi.util.Constants;
import it.csi.idf.idfapi.util.DBUtil;
import it.csi.util.performance.StopWatch;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Implementazione del DAO ApiManager.
 * Il DAO implementa le seguenti operazioni:
  * - FINDERS:
 *   - findByPrimaryKey (datagen::FindByPK)
  * - UPDATERS:
 
 *    --
 * - DELETERS:
 
 *    --
 *
 * Le query sono realizzate utiulizzando spring-JDBCTemplate.
 * @generated
 */

@Component
public class ApiManagerDaoImpl extends AbstractDAO implements ApiManagerDao {
	protected static final Logger LOG = Logger.getLogger(Constants.COMPONENT_NAME);
	/**
	 * Il DAO utilizza JDBC template per l'implementazione delle query.
	 * @generated
	 */
	protected NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * Imposta il JDBC template utilizato per l'implementazione delle query
	 * @generated
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	

	protected ApiManagerDaoRowMapper findByPrimaryKeyRowMapper = new ApiManagerDaoRowMapper(null, ApiManagerDto.class,
			this);


	
	/**
	 * 
	 * Restituisce il nome della tabella su cui opera il DAO
	 * @return String
	 * @generated
	 */
	public String getTableName() {
		return "IDF_CNF_PARAM_APIMGR";
	}

	/** 
	 * Returns all rows from the IDF_CNF_PARAM_APIMGR table that match the primary key criteria
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public ApiManagerDto findByPrimaryKey(ApiManagerPk pk) throws ApiManagerDaoException {
		LOG.debug("[ApiManagerDaoImpl::findByPrimaryKey] START");
		final StringBuilder sql = new StringBuilder(
				"SELECT ID_CONFIG_PARAM_APIMGR,CONSUMER_KEY,CONSUMER_SECRET,DATA_INIZIO_VALIDITA,DATA_FINE_VALIDITA FROM "
						+ getTableName() + " WHERE ID_CONFIG_PARAM_APIMGR = ? ");

//		MapSqlParameterSource params = new MapSqlParameterSource();

		// valorizzazione paametro relativo a colonna [ID_CONFIG_PARAM_APIMGR]
//		params.addValue("ID_CONFIG_PARAM_APIMGR", pk.getIdConfigParamApimgr(), java.sql.Types.INTEGER);

		List<ApiManagerDto> list = null;

		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
		try {
			stopWatch.start();
			//list = jdbcTemplate.query(sql.toString(), params, findByPrimaryKeyRowMapper);
			list = DBUtil.jdbcTemplate.query(sql.toString(), findByPrimaryKeyRowMapper, pk.getIdConfigParamApimgr());
		} catch (RuntimeException ex) {
			LOG.error("[ApiManagerDaoImpl::findByPrimaryKey] ERROR esecuzione query", ex);
			throw new ApiManagerDaoException("Query failed", ex);
		} finally {
			stopWatch.dumpElapsed("ApiManagerDaoImpl", "findByPrimaryKey", "esecuzione query", sql.toString());
			LOG.debug("[ApiManagerDaoImpl::findByPrimaryKey] END");
		}
		return list.isEmpty() ? null : list.get(0);
	}
	
	/** 
	 * Implementazione del finder parametriValidi
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public ApiManagerDto findParametriValidi() throws ApiManagerDaoException {
		LOG.debug("[ApiManagerDaoImpl::findParametriValidi] START");
		StringBuilder sql = new StringBuilder();
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();

		sql.append(
				"SELECT ID_CONFIG_PARAM_APIMGR,CONSUMER_KEY,CONSUMER_SECRET,DATA_INIZIO_VALIDITA,DATA_FINE_VALIDITA ");
		sql.append(" FROM IDF_CNF_PARAM_APIMGR");
		sql.append(" WHERE DATA_FINE_VALIDITA IS NULL AND FLG_API_INTERNET = 0");
		
		List<ApiManagerDto> list = null;
		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
		try {
			stopWatch.start();
			
//			list = jdbcTemplate.query(sql.toString(), paramMap, findByPrimaryKeyRowMapper);
			
			list = DBUtil.jdbcTemplate.query(sql.toString(), findByPrimaryKeyRowMapper);
			
		} catch (RuntimeException ex) {
			LOG.error("[ApiManagerDaoImpl::findParametriValidi] esecuzione query", ex);
			throw new ApiManagerDaoException("Query failed", ex);
		} finally {
			stopWatch.dumpElapsed("ApiManagerDaoImpl", "findParametriValidi", "esecuzione query", sql.toString());
			LOG.debug("[ApiManagerDaoImpl::findParametriValidi] END");
		}
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public ApiManagerDto findParametriInternetValidi() throws ApiManagerDaoException {
		LOG.debug("[ApiManagerDaoImpl::findParametriValidi] START");
		StringBuilder sql = new StringBuilder();
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();

		sql.append(
				"SELECT ID_CONFIG_PARAM_APIMGR,CONSUMER_KEY,CONSUMER_SECRET,DATA_INIZIO_VALIDITA,DATA_FINE_VALIDITA ");
		sql.append(" FROM IDF_CNF_PARAM_APIMGR");
		sql.append(" WHERE DATA_FINE_VALIDITA IS NULL AND FLG_API_INTERNET = 1");
		
		List<ApiManagerDto> list = null;
		StopWatch stopWatch = new StopWatch(Constants.COMPONENT_NAME);
		try {
			stopWatch.start();
			
//			list = jdbcTemplate.query(sql.toString(), paramMap, findByPrimaryKeyRowMapper);
			
			list = DBUtil.jdbcTemplate.query(sql.toString(), findByPrimaryKeyRowMapper);
			
		} catch (RuntimeException ex) {
			LOG.error("[ApiManagerDaoImpl::findParametriValidi] esecuzione query", ex);
			throw new ApiManagerDaoException("Query failed", ex);
		} finally {
			stopWatch.dumpElapsed("ApiManagerDaoImpl", "findParametriValidi", "esecuzione query", sql.toString());
			LOG.debug("[ApiManagerDaoImpl::findParametriValidi] END");
		}
		return list.isEmpty() ? null : list.get(0);
	}

}
