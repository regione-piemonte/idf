/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfapi.business.be.impl.dao.util;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import it.csi.idf.idfapi.util.Constants;

/**
 * Implementazione di NamedParameterJdbcTemplate con l'aggiunta del log 
 * dell'SQL che viene eseguito.
 * @generated
 */
public class NamedParameterJdbcTemplateWithQueryDebug
		extends
			NamedParameterJdbcTemplate {

	/**
	 * @generated
	 */
	protected static final Logger LOG = Logger
			.getLogger(Constants.COMPONENT_NAME);

	/**
	 * carattere che nella query prefigge i placeholder
	 * @generated
	 */
	private static String PLACEHOLDER = ":";

	/**
	 * caratteri che delimitano la fine del parametro
	 * @generated
	 */
	private static String[] terminatoriParametro = new String[]{" ", "=", ")",
			"|", "(", ">", "<", "+", "-", "*", "/"};

	/**
	 * Costruttore
	 * @param dataSource
	 * @generated
	 */
	public NamedParameterJdbcTemplateWithQueryDebug(DataSource dataSource) {
		super(dataSource);
	}

	/** 
	 * costruttore
	 * @param classicJdbcTemplate
	 * @generated
	 */
	public NamedParameterJdbcTemplateWithQueryDebug(
			JdbcOperations classicJdbcTemplate) {
		super(classicJdbcTemplate);
	}

	/**
	 * aggiunge la logica di log della query alla logica della superclasse
	 * @param sql la query da eseguire
	 * @param paramSource i parametri da utilizzare nella query
	 * @throws DAOException
	 * @generated
	 */
	@Override
	public int update(String sql, SqlParameterSource paramSource)
			throws DataAccessException {
		try {
			logSql(sql, paramSource);
		} catch (Exception e) {
			LOG.error(
					"Si e' verificato un errore nella chiamata al dao supplier: ",
					e);
		}
		return super.update(sql, paramSource);
	}

	/**
	 * aggiunge la logica di log della query alla logica della superclasse
	 * @param sql la query da eseguire
	 * @param paramSource i parametri da utilizzare nella query
	 * @rowMapper il mapper da utilizzare per ricostruire il risultato
	 * @return la lista di risultati
	 * @throws DAOException
	 * @generated
	 */
	@Override
	public List query(String sql, SqlParameterSource paramSource,
			RowMapper rowMapper) throws DataAccessException {
		try {
			logSql(sql, paramSource);
		} catch (Exception e) {
			LOG.error(
					"Si e' verificato un errore nella chiamata al dao supplier: ",
					e);
		}
		return super.query(sql, paramSource, rowMapper);
	}

	/**
	 * stampa su log (a livello DEBUG) la query con i valori dei parametri impostati
	 * al posto dei placeholder
	 * 
	 * @param sql query con i placeholder 
	 * @param paramSource  mappa contenente i parametri e loro valori
	 * @generated
	 */
	private void logSql(String sql, SqlParameterSource paramSource) {
		if (LOG.isDebugEnabled()) {
			sql = buildSqlWithParameters(sql, paramSource);
		}
		LOG.debug(sql);
	}

	/**
	 * 
	 * Restituisce una query con i valori impostati al posto dei placeholder (:nomeparametro)
	 * 
	 * @param sql query con i placeholder 
	 * @param paramSource  mappa contenente i parametri e loro valori
	 * @return
	 * @generated
	 */
	private String buildSqlWithParameters(String sql,
			SqlParameterSource paramSource) {
		String[] parametriQuery = sql.split(PLACEHOLDER);
		for (String parametro : parametriQuery) {
			parametro = getParametroUtile(parametro);
			if (paramSource.hasValue(parametro)) {
				sql = sql.replaceAll(PLACEHOLDER + parametro,
						getValue(paramSource, parametro));
			}
		}
		return sql;
	}

	/**
	 * Cerca all'interno di una parte di query la minore porzione di stringa utile per essere considerata
	 * come parametro
	 *  
	 * @param parametro  pezzo di query ottenuta da una istruzione split con il carattere ':' come separatore 
	 * @return la minore parola utile utilizzabile come parametro della query
	 * @generated
	 */
	private String getParametroUtile(String parametro) {
		int posizioneTerminatoreAttuale = 0;
		for (int i = 0; i < terminatoriParametro.length; i++) {
			posizioneTerminatoreAttuale = parametro
					.indexOf(terminatoriParametro[i]);
			if (posizioneTerminatoreAttuale > 0) {
				parametro = parametro.substring(0, posizioneTerminatoreAttuale);
			}
		}
		return parametro;
	}

	/**
	 * restituisce una rappresentazione in formato stringa di un parametro con placeholder
	 * nella query prelevando il valore dalla Map
	 * @param paramSource mappa contenente i parametri e ralativi valori
	 * @param parametro nome del parametro nella query
	 * @return
	 * @generated
	 */
	private String getValue(SqlParameterSource paramSource, String parametro) {
		String resultValue = "null";
		Object paramValue = paramSource.getValue(parametro);

		if (paramValue != null) {
			if (paramValue instanceof String) {
				return "'" + ((String) paramValue).replaceAll("'", "''") + "'";
			} else if (paramValue instanceof Number) {
				resultValue = paramValue.toString();
			} else if (paramValue instanceof Date) {
				resultValue = parseDate((Date) paramValue);
			}
		}
		return resultValue;
	}

	/**
	 * Converte una data in un formato stringa utilizzabile nelle query per Oracle 
	 * @param date 
	 * @return  stringa rappresentante la data nel formato to_date(,DD/MM/YYYY HH24:MI:SS')
	 * @generated
	 */
	private static String parseDate(java.util.Date date) {
		StringBuilder retValue = new StringBuilder("");
		StringBuilder retvalueBuff = new StringBuilder("");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);

		retvalueBuff.append(Integer.toString(cal
				.get(java.util.Calendar.DAY_OF_MONTH)));
		retvalueBuff.append("/");

		retvalueBuff
				.append(Integer.toString(cal.get(java.util.Calendar.MONTH) + 1));
		retvalueBuff.append("/");

		retvalueBuff.append(Integer.toString(cal.get(java.util.Calendar.YEAR)));
		retvalueBuff.append("-");

		retvalueBuff.append(Integer.toString(cal
				.get(java.util.Calendar.HOUR_OF_DAY)));
		retvalueBuff.append(":");

		retvalueBuff
				.append(Integer.toString(cal.get(java.util.Calendar.MINUTE)));
		retvalueBuff.append(":");

		retvalueBuff
				.append(Integer.toString(cal.get(java.util.Calendar.SECOND)));

		retValue.append("to_date('");
		retValue.append(retvalueBuff);
		retValue.append("','DD/MM/YYYY HH24:MI:SS')");

		return retValue.toString();
	}

}
