/*******************************************************************************
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 ******************************************************************************/
package it.csi.idf.idfbo.integration;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import it.csi.idf.idfbo.dto.ApiManagerDto;
import it.csi.idf.idfbo.dto.MailConfigDto;
import it.csi.idf.idfbo.dto.MailConfigPk;
import it.csi.idf.idfbo.dto.SemaforoDto;
import it.csi.idf.idfbo.dto.TipoMailDto;
import it.csi.idf.idfbo.dto.TipoMailPk;
import it.csi.idf.idfbo.integration.mapper.ApiManagerDaoRowMapper;
import it.csi.idf.idfbo.integration.mapper.MailConfigDaoRowMapper;
import it.csi.idf.idfbo.integration.mapper.SemaforoRowMapper;
import it.csi.idf.idfbo.integration.mapper.TipoMailDaoRowMapper;
import it.csi.idf.idfbo.util.Constants;

@Component
public class ConfigDAO extends BaseDAO {

	protected static final Logger logger = Logger.getLogger(Constants.LOGGING.LOGGER_NAME + ".integration");

	public final String THIS_CLASS = ConfigDAO.class.getSimpleName();

	protected MailConfigDaoRowMapper mailConfigFindByPrimaryKeyRowMapper = new MailConfigDaoRowMapper(null,
			MailConfigDto.class, this);

	protected TipoMailDaoRowMapper tipoMailFindByPrimaryKeyRowMapper = new TipoMailDaoRowMapper(null, TipoMailDto.class,
			this);

	protected SemaforoRowMapper semaforoRowMapper = new SemaforoRowMapper(null, SemaforoDto.class, this);
	
	protected ApiManagerDaoRowMapper apiManagerRowMapper = new ApiManagerDaoRowMapper(null, ApiManagerDto.class, this);

	public boolean isScheduleDateActive() {
		String THIS_METHOD = "[" + THIS_CLASS + "::isScheduleDateActive]";
		logger.info(THIS_METHOD + " BEGIN.");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append("case ");
			sb.append("when exists (select 1 ");
			sb.append("from ");
			sb.append("(select icpa.parametro_appl_char as ora ");
			sb.append("from idf_cnf_parametro_appl icpa ");
			sb.append("where icpa.id_parametro_appl = 22) da, ");
			sb.append("(select icpa.parametro_appl_char as ora ");
			sb.append("from idf_cnf_parametro_appl icpa ");
			sb.append("where icpa.id_parametro_appl = 23) a ");
			sb.append("where now()::time between  da.ora::time and a.ora::time");
			sb.append(") ");
			sb.append("then TRUE ");
			sb.append("else FALSE ");
			sb.append("end");

			boolean result = queryForBoolean(sb.toString(), new MapSqlParameterSource());
			logger.info(THIS_METHOD + " RESULT???? ." + result);
			return result;
		} finally {
			logger.info(THIS_METHOD + " END.");
		}
	}

	public boolean isAnotherScheduleRunning() {
		String THIS_METHOD = "[" + THIS_CLASS + "::isAnotherScheduleRunning]";
		logger.debug(THIS_METHOD + " BEGIN.");
		try {

			StringBuffer sb = new StringBuffer();
			sb.append("select ");
			sb.append("case ");
			sb.append("when exists (select 1 ");
			sb.append("from idf_cnf_boproc_log l ");
			sb.append("where l.data_fine is null ");
			sb.append("and l.esito != :ESITO_KO");
			sb.append(") ");
			sb.append("then FALSE ");
			sb.append("else TRUE ");
			sb.append("end");

			MapSqlParameterSource map = new MapSqlParameterSource();
			map.addValue("ESITO_KO", Constants.ESITO.KO);

			boolean result = queryForBoolean(sb.toString(), map);
			logger.info(THIS_METHOD + " RESULT???? ." + result);
			return result;
		} finally {
			logger.info(THIS_METHOD + " END.");

		}
	}

	@SuppressWarnings("unchecked")
	public TipoMailDto findTipoMailByPrimaryKey(TipoMailPk pk) {
		String THIS_METHOD = "[" + THIS_CLASS + "::findTipoMailByPrimaryKey]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}
		final StringBuilder sql = new StringBuilder("SELECT ID_TIPO_MAIL,FK_AMBITO_ISTANZA,OGGETTO,TESTO FROM ");
		sql.append("IDF_CNF_TIPO_MAIL WHERE ID_TIPO_MAIL = :ID_TIPO_MAIL ");

		MapSqlParameterSource params = new MapSqlParameterSource();

		// valorizzazione paametro relativo a colonna [ID_TIPO_MAIL]
		params.addValue("ID_TIPO_MAIL", pk.getIdTipoMail(), java.sql.Types.INTEGER);

		List<TipoMailDto> list = null;

		try {
			list = this.namedParameterJdbcTemplate.query(sql.toString(), params, tipoMailFindByPrimaryKeyRowMapper);
		} catch (RuntimeException ex) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", ex);
			throw ex;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	public MailConfigDto findMailConfigByPrimaryKey(MailConfigPk pk) {
		String THIS_METHOD = "[" + THIS_CLASS + "::findMailConfigByPrimaryKey]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}

		final StringBuilder sql = new StringBuilder(
				"SELECT ID_MAIL,HOST,PORTA,USER_ID,PSW,PROTOCOLLO,MITTENTE,DES_TIPO_POSTA FROM IDF_CNF_MAIL WHERE ID_MAIL = :ID_MAIL ");

		MapSqlParameterSource params = new MapSqlParameterSource();

		// valorizzazione paametro relativo a colonna [ID_MAIL]
		params.addValue("ID_MAIL", pk.getIdMail(), java.sql.Types.INTEGER);

		List<MailConfigDto> list = null;

		try {
			list = this.namedParameterJdbcTemplate.query(sql.toString(), params, mailConfigFindByPrimaryKeyRowMapper);
		} catch (RuntimeException ex) {
			logger.error(THIS_METHOD + "Errore nel richiamo del metodo", ex);
			throw ex;
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug(THIS_METHOD + " END.");
			}
		}
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean startSemaforo(String codiceSemaforo) throws Exception {
		String THIS_METHOD = "[" + THIS_CLASS + "::startSemaforo]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}
		final StringBuilder select = new StringBuilder();
		select.append("SELECT * FROM idf_cnf_semaforo WHERE codice = :codiceSemaforo FOR UPDATE NOWAIT");

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("codiceSemaforo", codiceSemaforo, java.sql.Types.VARCHAR);

		try {
			this.namedParameterJdbcTemplate.query(select.toString(), params, semaforoRowMapper);

		} catch (Throwable ex) {
			logger.debug(THIS_METHOD + " locked");
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		final StringBuilder update = new StringBuilder();
		update.append("UPDATE idf_cnf_semaforo SET valore = 1 WHERE codice = :codiceSemaforo AND valore = 0 ");

		int rows = namedParameterJdbcTemplate.update(update.toString(), params);
		logger.debug(THIS_METHOD + " nrows " + rows);
		return rows > 0;

	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void stopSemaforo(String codiceSemaforo) {
		String THIS_METHOD = "[" + THIS_CLASS + "::stopSemaforo]";
		if (logger.isDebugEnabled()) {
			logger.debug(THIS_METHOD + " BEGIN.");
		}
		try {
			final StringBuilder select = new StringBuilder();
			select.append("SELECT * FROM idf_cnf_semaforo WHERE codice = :codiceSemaforo FOR UPDATE NOWAIT");

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("codiceSemaforo", codiceSemaforo, java.sql.Types.VARCHAR);

			try {
				this.namedParameterJdbcTemplate.query(select.toString(), params, semaforoRowMapper);

			} catch (Throwable ex) {
				logger.debug(THIS_METHOD + " locked");
				TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
				return;
			}
			final StringBuilder update = new StringBuilder();
			update.append("UPDATE idf_cnf_semaforo SET valore = 0 WHERE codice = :codiceSemaforo ");

			namedParameterJdbcTemplate.update(update.toString(), params);

		} catch (Exception ex) {
			logger.error(THIS_METHOD, ex);
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ApiManagerDto getInfoApiManagerInternet()  {
		logger.info("["+THIS_CLASS+"::getInfoApiManagerInternet] START");
		StringBuilder sql = new StringBuilder();
//		MapSqlParameterSource paramMap = new MapSqlParameterSource();

		sql.append(
				"SELECT ID_CONFIG_PARAM_APIMGR,CONSUMER_KEY,CONSUMER_SECRET,DATA_INIZIO_VALIDITA,DATA_FINE_VALIDITA ");
		sql.append(" FROM IDF_CNF_PARAM_APIMGR");
		sql.append(" WHERE DATA_FINE_VALIDITA IS NULL AND FLG_API_INTERNET = 1");
		
		List<ApiManagerDto> list = null;
		try {
			
//			list = jdbcTemplate.query(sql.toString(), paramMap, findByPrimaryKeyRowMapper);
			
			list = namedParameterJdbcTemplate.query(sql.toString(), apiManagerRowMapper);
			
			logger.info("Post query valore di LIST "+list);
				
		} catch (RuntimeException ex) {
			logger.error("["+THIS_CLASS+"::getInfoApiManagerInternet] esecuzione query", ex);
			throw ex;
		} finally {
			logger.info("["+THIS_CLASS+"::getInfoApiManagerInternet] END");
		}
		ApiManagerDto result = list.isEmpty() ? null : list.get(0);
		logger.info("Valore di result prima di uscire dal DAO "+result);
		return result;
	}
}
